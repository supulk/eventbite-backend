package com.eventbite.eventbite_backend.DTO.Event;

import com.eventbite.eventbite_backend.Entity.GuestRegistration;
import com.eventbite.eventbite_backend.Entity.UserRegistration;
import com.eventbite.eventbite_backend.Entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class EventRequestDTO {
    private String title;
    private String description;
    private long eventDate;
    private String location;
    private String publicId;
    private String privacy;
    private LocalDateTime dateCreated;
    private String userId;
    private List<UserRegistration> userRegistrations;
    private List<GuestRegistration> guestRegistrations;
}
