package com.example.agencyamazontest.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.example.agencyamazontest.entity.User;
import com.example.agencyamazontest.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Can't find user by username " + username);
        }
        User user = userOptional.get();
        UserBuilder builder = withUsername(username);
        builder.password(user.getPassword());
        return builder.build();
    }
}
