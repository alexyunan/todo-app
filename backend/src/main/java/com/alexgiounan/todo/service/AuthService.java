package com.alexgiounan.todo.service;

import com.alexgiounan.todo.dto.LoginDto;
import com.alexgiounan.todo.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
