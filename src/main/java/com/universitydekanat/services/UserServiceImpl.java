package com.universitydekanat.services;


import com.universitydekanat.dao.UserRepository;
import com.universitydekanat.dao.UserRoleRepository;
import com.universitydekanat.domain.User;
import com.universitydekanat.domain.UserRole;
import com.universitydekanat.domain.UserRoles;
import com.universitydekanat.dtos.UserDto;
import com.universitydekanat.dtos.UserMapper;
import com.universitydekanat.dtos.UserRoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDto signup(UserDto userDto) {
        UserRole userRole;
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null) {
            if (userDto.isAdmin()) {
                userRole = roleRepository.findByName(UserRoles.EMPLOYEE.name());
            } else {
                userRole = roleRepository.findByName(UserRoles.STUDENT.name());
            }
            user = new User();
            user.setEmail(userDto.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            return UserMapper.toUserDto(userRepository.save(user));
        }
        throw new RuntimeException("User duplicate email");
    }

    public UserDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            UserDto userDto = new UserDto();
            userDto.setPassword(user.get().getPassword());
            userDto.setEmail(user.get().getEmail());
            userDto.setFirstName(user.get().getFirstName());
            userDto.setLastName(user.get().getLastName());
            userDto.setRoles(user.get().getRoles().stream().map(role -> new UserRoleDto().setRole(role.getName())).collect(Collectors.toSet()));


            return userDto;
        }
            throw new RuntimeException("User with email :" + email + " not found");
    }

    @Override
    public UserDto updateProfile(UserDto userDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setFirstName(userDto.getFirstName());
            userModel.setLastName(userDto.getLastName());
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw new RuntimeException("User not found");
    }

}
