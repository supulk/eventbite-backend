package com.eventbite.eventbite_backend.DTO;

import com.eventbite.eventbite_backend.Entity.Registration;
import com.eventbite.eventbite_backend.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class EventResponseDTO {
    private int id;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String location;
    private String publicId;
    private LocalDateTime dateCreated;
    private User organizer;
    private List<Registration> registrations = new ArrayList<>();
}
