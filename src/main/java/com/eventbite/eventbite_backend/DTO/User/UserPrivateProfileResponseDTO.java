package com.eventbite.eventbite_backend.DTO.User;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.UserRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrivateProfileResponseDTO {
    private String username;
    private String email;
    private String publicId;
    private String dateCreated;
}
