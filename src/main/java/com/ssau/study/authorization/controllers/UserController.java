package com.ssau.study.authorization.controllers;

import com.ssau.study.authorization.dto.UserPojo;
import com.ssau.study.authorization.entities.UserEntity;
import com.ssau.study.authorization.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/services/controller/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/hello")
    public String getHello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Hello " + authentication.getPrincipal().toString();
    }

    @GetMapping()
    public UserPojo loadUserByEmail(@RequestParam(value = "email", required = false) String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping()
    public ResponseEntity<String> saveUser(@RequestBody UserEntity account) {
        return new ResponseEntity<>(userService.saveUser(account).getEmail(), HttpStatus.OK);
    }


    @DeleteMapping("/id")
    public ResponseEntity<String> deleteUser(@RequestParam("id") Long id) {
        return null;
    }

}
