package com.example.agencyamazontest.service;

import com.example.agencyamazontest.entity.User;
import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findByUsername(String username);
}
