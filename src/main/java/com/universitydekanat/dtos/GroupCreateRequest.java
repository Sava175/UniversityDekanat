package com.universitydekanat.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class GroupCreateRequest {

    @NotBlank
    @Size(min = 5)
    private String title;
}
