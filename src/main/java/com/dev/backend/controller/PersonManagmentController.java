package com.dev.backend.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.model.Person;
import com.dev.backend.security.JwtUtil;
import com.dev.backend.service.PersonManagmentService;

@RestController
@RequestMapping("/api/person-managment")
public class PersonManagmentController {

  @Autowired
  private PersonManagmentService personManagmentService;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  AuthenticationManager authenticationManager;

  @PostMapping("/recover-password")
  public String recoverPassword(@RequestBody Person person) {
    return personManagmentService.requestRecoveryCode(person.getEmail());
  }

  @PostMapping("/change-password")
  public String changePassword(@RequestBody Person person) {
    return personManagmentService.changePassword(person);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Person person) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(person.getEmail(),
            person.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    Person authenticated = (Person) authentication.getPrincipal();
    String token = jwtUtil.generateUsernameToken(authenticated);
    HashMap<String, Object> map = new HashMap<>();
    map.put("token", token);
    map.put("permissions", authenticated.getAuthorities());
    return ResponseEntity.ok(map);
  }
}
