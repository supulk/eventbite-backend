package com.eventbite.eventbite_backend.Mapper;

import com.eventbite.eventbite_backend.DTO.GuestRegistrationRequestDTO;
import com.eventbite.eventbite_backend.DTO.GuestRegistrationResponseDTO;
import com.eventbite.eventbite_backend.Entity.GuestRegistration;

public class GuestRegistrationMapper {
    public static GuestRegistration toEntity(GuestRegistrationRequestDTO request){
        GuestRegistration guestRegistration = new GuestRegistration();
        guestRegistration.setEmail(request.getEmail());
        guestRegistration.setName(request.getName());
        guestRegistration.setEvent(request.getEvent());
        guestRegistration.setRegistrationDate(request.getRegistrationDate());

        return guestRegistration;
    }

    public static GuestRegistrationResponseDTO toResponse(GuestRegistration entity){
        return new GuestRegistrationResponseDTO(
                entity.getId(),
                entity.getEmail(),
                entity.getName(),
                entity.getEvent(),
                entity.getRegistrationDate()
        );
    }

    public static void UpdateGuestRegistrationEntity(GuestRegistrationRequestDTO request, GuestRegistration existing){
        if (request.getEmail() != null){
            existing.setEmail(request.getEmail());
        }
        if (request.getName() != null){
            existing.setName(request.getName());
        }
        if (request.getEvent() != null){
            existing.setEvent(request.getEvent());
        }
        if (request.getRegistrationDate() != null){
            existing.setRegistrationDate(request.getRegistrationDate());
        }
    }
}
