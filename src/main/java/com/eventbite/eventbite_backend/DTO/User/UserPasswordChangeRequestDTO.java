package com.eventbite.eventbite_backend.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserPasswordChangeRequestDTO {
    private String oldPassword;
    private String newPassword;
}
