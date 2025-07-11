package com.eventbite.eventbite_backend.DTO.Event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PublicEventResponseDTO {
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String location;
    private String publicId;
    private String privacy;
    private LocalDateTime dateCreated;
    private String organizerName;
}
