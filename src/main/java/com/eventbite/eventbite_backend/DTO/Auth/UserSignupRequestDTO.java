package com.eventbite.eventbite_backend.DTO.Auth;

import lombok.Data;

@Data
public class UserSignupRequestDTO{
    private String username;
    private String email;
    private String password;
}
