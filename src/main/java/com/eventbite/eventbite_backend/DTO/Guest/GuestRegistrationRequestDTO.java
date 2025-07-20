package com.eventbite.eventbite_backend.DTO.Guest;

import lombok.Data;

@Data
public class GuestRegistrationRequestDTO {
    private String email;
    private String name;
    private String eventId;
}
