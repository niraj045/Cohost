package org.coHost.co_Host.controller;

import java.security.Principal;

import org.coHost.co_Host.config.JwtUtil;
import org.coHost.co_Host.dto.UserDto;
import org.coHost.co_Host.model.User;
import org.coHost.co_Host.repository.UserRepository;
import org.coHost.co_Host.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cohost.dto.AuthResponse;
import com.cohost.dto.LoginRequest;
import com.cohost.dto.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            User newUser = new User();
            newUser.setUsername(registerRequest.getEmail());
            newUser.setPassword(registerRequest.getPassword());
            newUser.setFirstName(registerRequest.getFirstName());
            newUser.setLastName(registerRequest.getLastName());
            
            userService.createUser(newUser);
            return ResponseEntity.ok("User registered successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUsername(loginRequest.getUsername());
        String jwt = jwtUtil.generateToken(loginRequest.getUsername());

        return ResponseEntity.ok(new AuthResponse(jwt, new UserDto(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName())));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName());
        return ResponseEntity.ok(userDto);
    }
}