package com.example.agencyamazontest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = {"com.example.agencyamazontest"})
public class AgencyAmazonTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgencyAmazonTestApplication.class, args);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
