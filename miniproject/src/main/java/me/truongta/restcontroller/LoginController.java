package me.truongta.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.truongta.dao.UserRepository;
import me.truongta.entity.Users;
import me.truongta.exception.NotFoundSomething;
import me.truongta.security.JwtService;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    UserRepository udao;
    @Autowired
    AuthenticationManager authentication;
    @Autowired
    JwtService jwtService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username,@RequestParam String password ) {
        Authentication auth = authentication.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtService.generateJwt(username);
        return new ResponseEntity<String>(jwt, HttpStatus.OK);
    }

    @GetMapping("/dangxuat")
    public void dangxuat() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @GetMapping("/profile")
    public ResponseEntity<Users> abc() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users users = udao.findById(username).orElseThrow(()->new NotFoundSomething("Not found username"));
        return new ResponseEntity<Users>(users, HttpStatus.OK);
    }
    
    

    



}