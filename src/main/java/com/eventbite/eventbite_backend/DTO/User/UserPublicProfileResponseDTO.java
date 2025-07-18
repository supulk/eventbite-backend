package com.eventbite.eventbite_backend.DTO.User;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.UserRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserPublicProfileResponseDTO {
    private String username;
    private String publicId;
    private String dateCreated;
}
