package com.example.agencyamazontest.mapper;

import com.example.agencyamazontest.dto.response.UserResponseDto;
import com.example.agencyamazontest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDto mapToUserResponseDto(User user);
}
