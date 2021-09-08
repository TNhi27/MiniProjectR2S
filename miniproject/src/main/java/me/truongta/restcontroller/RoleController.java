package me.truongta.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.truongta.dao.RoleRepository;
import me.truongta.dto.RoleRequest;
import me.truongta.entity.Role;
import me.truongta.exception.NotFoundSomething;

@RestController
@RequestMapping("/api/v1/role")
@CrossOrigin
public class RoleController {
    @Autowired
    RoleRepository rdao;

    @GetMapping
    public ResponseEntity<List<Role>> getAll() {
        return new ResponseEntity<List<Role>>(rdao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getOne(@PathVariable String id) {
        return new ResponseEntity<Role>(
                rdao.findById(id).orElseThrow(() -> new NotFoundSomething("ROLE WITH ID NOT FOUND")), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Role> postRole(@RequestBody RoleRequest role) {
        if (rdao.findById(role.getId()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Role r = new Role();
        r.loadByDTO(role);
        return new ResponseEntity<Role>(rdao.save(r), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> putRole(@PathVariable String id, @RequestBody RoleRequest role) {
        Role rl = rdao.findById(id).orElseThrow(()->new NotFoundSomething("NOT_FOUND ROLE"));
        rl.setRole(role.getRole());
        return new ResponseEntity<Role>(rdao.save(rl), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        if (!rdao.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rdao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}