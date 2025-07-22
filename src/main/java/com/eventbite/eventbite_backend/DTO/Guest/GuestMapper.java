package com.eventbite.eventbite_backend.DTO.Guest;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.GuestRegistration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

public class GuestMapper {
    public GuestRegistration toEntity(GuestRegistrationRequestDTO request, Event event){
        GuestRegistration entity = new GuestRegistration();
        entity.setEmail(request.getEmail());
        entity.setName(request.getName());
        entity.setEvent(event);
        entity.setRegistrationDate(LocalDateTime.now());
        return entity;
    }


    public PublicGuestResponseDTO entityToPublicResponse(GuestRegistration registration){
        PublicGuestResponseDTO dto = new PublicGuestResponseDTO();
        dto.setName(registration.getName());
        dto.setRegisteredDate(registration.getRegistrationDate());
        return dto;
    }
}
