package com.example.SpringSecurityJWT.controller;

import com.example.SpringSecurityJWT.model.ERole;
import com.example.SpringSecurityJWT.model.RoleEntity;
import com.example.SpringSecurityJWT.model.UserEntity;
import com.example.SpringSecurityJWT.repository.UserRepository;
import com.example.SpringSecurityJWT.request.CreateUserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api")
public class PrincipalController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public PrincipalController(UserRepository userRepository, PasswordEncoder password) {
        this.userRepository = userRepository;
        passwordEncoder = password;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello world not secure";
    }

    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "hello world secured";
    }

    @PostMapping
    public String aa(){
        return "hola";

    }
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        Set<RoleEntity> roles = createUserDTO.getRoles().stream().
                map(role -> RoleEntity.builder().name(ERole.valueOf(role)).build()).
                collect(Collectors.toSet());
        UserEntity userEntity = UserEntity.builder().
                username(createUserDTO.getUsername()).
                password(passwordEncoder.encode(createUserDTO.getPassword())).
                email(createUserDTO.getEmail()).
                roles(roles).
                build();
        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);

    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        userRepository.deleteById(Integer.valueOf(id));
        return "se ha borrado el user con id".concat(id);

    }
}
