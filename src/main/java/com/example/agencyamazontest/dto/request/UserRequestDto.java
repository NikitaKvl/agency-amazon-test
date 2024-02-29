package com.example.agencyamazontest.dto.request;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {
    @NonNull
    private String username;
    private String password;
}
