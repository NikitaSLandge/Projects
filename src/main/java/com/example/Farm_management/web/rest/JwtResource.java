package com.example.Farm_management.web.rest;

import com.example.Farm_management.domain.LoginRequest;
import com.example.Farm_management.repository.UserRepository;
import com.example.Farm_management.Service.Impl.UserServiceImpl;
import com.example.Farm_management.security.GenerateToken;
import com.example.Farm_management.security.JwtResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class JwtResource {

    private final GenerateToken generateToken;
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final UserServiceImpl userServiceimpl;

    public JwtResource(GenerateToken generateToken, AuthenticationManager authenticationManager, UserRepository userRepository, UserServiceImpl userServiceimpl) {
        this.generateToken = generateToken;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userServiceimpl = userServiceimpl;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = generateToken.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "bearer " + token);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

}


