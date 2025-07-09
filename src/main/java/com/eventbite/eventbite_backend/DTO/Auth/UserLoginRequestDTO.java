package com.eventbite.eventbite_backend.DTO.Auth;

import lombok.Data;

@Data
public class UserLoginRequestDTO {
    private String email;
    private String password;
}
