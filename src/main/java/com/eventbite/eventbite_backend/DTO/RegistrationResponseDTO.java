package com.eventbite.eventbite_backend.DTO;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RegistrationResponseDTO {
    private long id;
    private String guestName;
    private String guestEmail;
    private String registrationType;
    private String privacy;
    private LocalDateTime registrationDate;
    private Event event;
    private User user;
}
