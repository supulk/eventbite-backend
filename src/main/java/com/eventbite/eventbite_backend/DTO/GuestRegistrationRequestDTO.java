package com.eventbite.eventbite_backend.DTO;

import com.eventbite.eventbite_backend.Entity.Event;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GuestRegistrationRequestDTO {
    private String email;
    private String name;
    private Event event;
    private LocalDateTime registrationDate;
}
