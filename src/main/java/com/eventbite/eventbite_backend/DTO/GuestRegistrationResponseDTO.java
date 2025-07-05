package com.eventbite.eventbite_backend.DTO;

import com.eventbite.eventbite_backend.Entity.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GuestRegistrationResponseDTO {
    private long id;
    private String email;
    private String name;
    private Event event;
    private LocalDateTime registrationDate;
}
