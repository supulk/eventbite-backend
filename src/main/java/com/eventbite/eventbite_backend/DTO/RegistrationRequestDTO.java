package com.eventbite.eventbite_backend.DTO;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistrationRequestDTO {
    private String guestName;
    private String guestEmail;
    private String registrationType;
    private String privacy;
    private LocalDateTime registrationDate;
    private Event event;
    private User user;
}
