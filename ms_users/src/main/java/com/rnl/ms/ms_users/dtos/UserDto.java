package com.rnl.ms.ms_users.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDto {

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank    
    private String password; 
}
