package me.truongta.restcontroller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import me.truongta.dao.RoleRepository;
import me.truongta.dao.UserRepository;
import me.truongta.dto.UserRequest;
import me.truongta.entity.Role;
import me.truongta.entity.Users;
import me.truongta.exception.NotFoundSomething;
import me.truongta.exception.UserAlreadyExists;
import me.truongta.exception.UsersException;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UsersController {

    @Autowired
    UserRepository udao;
    @Autowired
    RoleRepository rdao;
    @Autowired
    ServletContext app;

    @GetMapping
    public ResponseEntity<Page<Users>> getAllUsers(@RequestParam Optional<Integer> pageNumber,
            @RequestParam Optional<Integer> size) {

        System.out.println(rdao.findById("ADMIN").get());
        Page<Users> page = udao.findAll(PageRequest.of(pageNumber.orElse(0), size.orElse(10)));
        return new ResponseEntity<Page<Users>>(page, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserRequest> getUsers(@PathVariable String username) {
        Users users = udao.findById(username).orElseThrow(() -> new UsersException());
        UserRequest userRequest = new  UserRequest();
        userRequest.loadByEntity(users);
        return new ResponseEntity<UserRequest>(userRequest, HttpStatus.OK);
    }

    
    @PostMapping("/{username}/upload")
    public ResponseEntity<String> saveFile(@RequestParam MultipartFile file,@PathVariable("username") String username) {
       File img= this.getFile(file, "/upload");
        Users users = udao.findById(username).orElseThrow(() -> new UsersException());
        users.setImage(file.getOriginalFilename());
        udao.save(users);
       
        return new ResponseEntity<String>(img.getName(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Users> postUsers(@RequestBody UserRequest dto) {

        if (udao.findById(dto.getUsername()).isPresent()) {
            throw new UserAlreadyExists();
        } else {
            /*
             * Không thể mapper json dạng phân cấp từ client sang Users.class Sử dụng
             * UserRequets để nhận và map sang Users.class
             */
            Role r = rdao.findById(dto.getRole())
                    .orElseThrow(() -> new NotFoundSomething("Role \"" + dto.getRole() + "\" not exist"));
            Users user = new Users();
            user.loadByDTO(dto);
            user.setRole(r);
            udao.save(user);

            return new ResponseEntity<Users>(user, HttpStatus.OK);
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<Users> putUsers(@PathVariable String username, @RequestBody UserRequest users) {
        Users u = udao.findById(username).map(user -> {
            // user.setPassword(users.getPassword());
            // user.setFullname(users.getFullname());
            // user.setActive(users.getActive());
            // user.setAddress(users.getAddress());
            // user.setBirthday(users.getBirthday());
            // user.setImage(users.getImage());
            // user.setRole(users.getRole());
            Role r = rdao.findById(users.getRole())
            .orElseThrow(() -> new NotFoundSomething("Role \"" + users.getRole() + "\" not exist"));
            user.loadByDTO(users);
            user.setRole(r);
            return udao.save(user);
        }).orElseThrow(() -> new UsersException());

        return new ResponseEntity<Users>(u, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        if (!udao.findById(username).isPresent()) {
            throw new UsersException();
        }
        udao.deleteById(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public File getFile(MultipartFile file, String path) {
      
        File dir = new File(app.getRealPath(path));
        if(!dir.exists()) dir.mkdirs();
        try {
            File saveFile = new File(dir, file.getOriginalFilename());
            file.transferTo(saveFile);
            return saveFile;
        } catch (Exception e) {
           throw new RuntimeException();
        }
     }

     public void save(MultipartFile image, String path) throws IOException {
         Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
         Path staticPath = Paths.get("static");
         Path imagePath = Paths.get("images");
         if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
             Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
         }
         Path file = CURRENT_FOLDER.resolve(staticPath)
                 .resolve(imagePath).resolve(image.getOriginalFilename());
         try (OutputStream os = Files.newOutputStream(file)) {
             os.write(image.getBytes());
         }
         System.out.println(imagePath.toAbsolutePath());
     }

     
}