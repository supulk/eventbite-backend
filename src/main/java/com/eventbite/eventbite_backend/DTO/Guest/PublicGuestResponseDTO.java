package com.eventbite.eventbite_backend.DTO.Guest;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PublicGuestResponseDTO {
    String name;
    LocalDateTime registeredDate;
}
