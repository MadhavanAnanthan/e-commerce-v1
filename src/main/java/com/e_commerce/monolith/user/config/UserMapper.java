package com.e_commerce.monolith.user.config;

import com.e_commerce.monolith.user.dto.SignupRequestDTO;
import com.e_commerce.monolith.user.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    Users signupRequestToEntity(SignupRequestDTO dto);
}
