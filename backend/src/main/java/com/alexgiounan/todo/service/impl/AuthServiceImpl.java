package com.alexgiounan.todo.service.impl;

import com.alexgiounan.todo.dto.LoginDto;
import com.alexgiounan.todo.dto.RegisterDto;
import com.alexgiounan.todo.entity.Role;
import com.alexgiounan.todo.entity.User;
import com.alexgiounan.todo.exception.ApiException;
import com.alexgiounan.todo.repository.RoleRepository;
import com.alexgiounan.todo.repository.UserRepository;
import com.alexgiounan.todo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterDto registerDto) {

        // check if username exists
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "username already exists.");
        }

        // check if email exists
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "email already exists.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role roleUser = roleRepository.findByName("ROLE_USER");
        roles.add(roleUser);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered successfully";
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged-in successfully";
    }
}
