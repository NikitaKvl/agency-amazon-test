package com.example.agencyamazontest.controller;

import com.example.agencyamazontest.dto.request.UserRequestDto;
import com.example.agencyamazontest.dto.response.UserResponseDto;
import com.example.agencyamazontest.entity.User;
import com.example.agencyamazontest.security.JwtTokenProvider;
import com.example.agencyamazontest.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = authenticationService.register(userRequestDto);
        return userResponseDto != null ? ResponseEntity.ok("User " + userResponseDto.getUsername()
                + " successfully registered.")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserRequestDto userRequestDto)
            throws AuthenticationException {
        User user = authenticationService
                .login(userRequestDto.getUsername(), userRequestDto.getPassword());
        return jwtTokenProvider.loginUser(user);
    }
}
