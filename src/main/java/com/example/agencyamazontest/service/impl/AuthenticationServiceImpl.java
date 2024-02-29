package com.example.agencyamazontest.service.impl;

import com.example.agencyamazontest.dto.request.UserRequestDto;
import com.example.agencyamazontest.dto.response.UserResponseDto;
import com.example.agencyamazontest.entity.User;
import com.example.agencyamazontest.mapper.UserMapper;
import com.example.agencyamazontest.service.AuthenticationService;
import com.example.agencyamazontest.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final static UserMapper userMapper = UserMapper.INSTANCE;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRequestDto userRequestDto) {
        if (!StringUtils.hasText(userRequestDto.getUsername()) || !StringUtils.hasText(userRequestDto.getPassword())) {
            throw new IllegalArgumentException("Username and password should be not empty");
        }
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return userMapper.mapToUserResponseDto(userService.save(user));
    }

    @Override
    public User login(String username, String password) throws AuthenticationException {
        Optional<User> optionalUser = userService.findByUsername(username);
        if (optionalUser.isEmpty()
                || !passwordEncoder.matches(password, optionalUser.get().getPassword())) {
            throw new AuthenticationException("Password or email is not valid");
        }
        return optionalUser.get();
    }
}
