package com.universitydekanat.dtos;


import com.universitydekanat.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;


@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())

                .setRoles(new HashSet<UserRoleDto>(user
                        .getRoles()
                        .stream()
                        .map(role -> new ModelMapper().map(role, UserRoleDto.class))
                        .collect(Collectors.toSet())));
    }

}
