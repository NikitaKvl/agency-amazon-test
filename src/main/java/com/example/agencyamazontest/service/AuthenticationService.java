package com.example.agencyamazontest.service;

import com.example.agencyamazontest.dto.request.UserRequestDto;
import com.example.agencyamazontest.dto.response.UserResponseDto;
import com.example.agencyamazontest.entity.User;
import org.apache.tomcat.websocket.AuthenticationException;

public interface AuthenticationService {
    UserResponseDto register(UserRequestDto userRequestDto);

    User login(String username, String password) throws AuthenticationException;
}
