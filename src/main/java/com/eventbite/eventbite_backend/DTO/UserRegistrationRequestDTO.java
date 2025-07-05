package com.eventbite.eventbite_backend.DTO;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRegistrationRequestDTO {
    private User user;
    private Event event;
    private LocalDateTime registrationDate;
}
