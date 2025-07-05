package com.eventbite.eventbite_backend.DTO;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.Registration;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
    private LocalDateTime dateCreated;
    private List<Event> OrganizedEvents = new ArrayList<>();
    private List<Registration> registrations = new ArrayList<>();
}
