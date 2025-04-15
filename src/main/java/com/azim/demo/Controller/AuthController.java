package com.azim.demo.Controller;


import com.azim.demo.Entity.Employee;
import com.azim.demo.Repository.EmployeeRepo;
import com.azim.demo.config.AuthRequest;
import com.azim.demo.config.JwtUtil;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    // Login method to authenticate and generate a JWT token
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        try {
            // Print for debugging
            System.out.println("Email: " + authRequest.getEmail());
            System.out.println("Password: " + authRequest.getPassword());

            // Authenticate using AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            // If authentication is successful, generate the JWT token
            if (authentication.isAuthenticated()) {
                String token = jwtUtil.generateToken(authRequest.getEmail());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }

//     Sample user creation for testing purposes (inserting one user in the DB)
//    @PostConstruct
//    public void init() {
//        Employee e = new Employee();
//        e.setEmail("azwwwwwim@example.com");
//        e.setPassword(passwordEncoder.encode("newwwwww_password"));
//        employeeRepo.save(e);
//    }
}
