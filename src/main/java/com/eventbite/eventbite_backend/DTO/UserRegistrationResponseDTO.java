package com.eventbite.eventbite_backend.DTO;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserRegistrationResponseDTO {
    private long id;
    private User user;
    private Event event;
    private LocalDateTime registrationDate;
}
