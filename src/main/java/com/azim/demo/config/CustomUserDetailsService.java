package com.azim.demo.config;

import com.azim.demo.Entity.Employee;
import com.azim.demo.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Convert the list of roles into SimpleGrantedAuthority list
        List<SimpleGrantedAuthority> authorities = employee.getAllRole().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim())) // Add "ROLE_" prefix as required by Spring Security
                .collect(Collectors.toList());

        // Return a User object which is a Spring Security implementation
        return new User(employee.getEmail(), employee.getPassword(), authorities);
    }
}
