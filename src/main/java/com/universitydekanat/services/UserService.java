package com.universitydekanat.services;


import com.universitydekanat.dtos.UserDto;

public interface UserService {

    UserDto signup(UserDto userDto);

    UserDto findUserByEmail(String email);

    UserDto updateProfile(UserDto userDto);

    UserDto changePassword(UserDto userDto, String newPassword);
}
