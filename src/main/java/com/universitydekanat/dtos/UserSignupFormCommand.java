package com.universitydekanat.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class UserSignupFormCommand {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 5)
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String isAdmin;

}
