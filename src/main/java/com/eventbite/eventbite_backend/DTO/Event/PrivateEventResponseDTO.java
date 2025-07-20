package com.eventbite.eventbite_backend.DTO.Event;

import com.eventbite.eventbite_backend.DTO.Registration.RegistrationsDTO;
import com.eventbite.eventbite_backend.Entity.GuestRegistration;
import com.eventbite.eventbite_backend.Entity.User;
import com.eventbite.eventbite_backend.Entity.UserRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivateEventResponseDTO {
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String location;
    private String publicId;
    private String privacy;
    private LocalDateTime dateCreated;
    private String userId;
    private List<RegistrationsDTO> Registrations;
}
