package com.eventbite.eventbite_backend.Mapper;

import com.eventbite.eventbite_backend.DTO.RegistrationRequestDTO;
import com.eventbite.eventbite_backend.DTO.RegistrationResponseDTO;
import com.eventbite.eventbite_backend.Entity.Registration;

public class RegistrationMapper {
    public static Registration toEntity(RegistrationRequestDTO request){
        Registration registration = new Registration();
        if (request.getGuestName() != null){
            registration.setGuestName(request.getGuestName());
        }
        if (request.getGuestEmail() != null){
            registration.setGuestEmail(request.getGuestEmail());
        }
        registration.setRegistrationType(request.getRegistrationType());
        registration.setPrivacy(registration.getPrivacy());
        registration.setRegistrationDate(request.getRegistrationDate());
        registration.setEvent(request.getEvent());
        if (request.getUser() != null){
            registration.setUser(request.getUser());
        }

        return registration;
    }

    public static RegistrationResponseDTO toResponse(Registration entity){
        return new RegistrationResponseDTO(
                entity.getId(),
                entity.getGuestName(),
                entity.getGuestEmail(),
                entity.getRegistrationType(),
                entity.getPrivacy(),
                entity.getRegistrationDate(),
                entity.getEvent(),
                entity.getUser()
        );
    }

    public static void UpdateRegistrationEntity(RegistrationRequestDTO request, Registration existing){
        if (request.getGuestName() != null){
            existing.setGuestName(request.getGuestName());
        }
        if (request.getGuestEmail() != null){
            existing.setGuestEmail(request.getGuestEmail());
        }
        if (request.getRegistrationType() != null){
            existing.setRegistrationType(request.getRegistrationType());
        }
        if (request.getPrivacy() != null){
            existing.setPrivacy(request.getPrivacy());
        }
        if (request.getRegistrationDate() != null){
            existing.setRegistrationDate(request.getRegistrationDate());
        }
        if (request.getEvent() != null){
            existing.setEvent(request.getEvent());
        }
        if (request.getUser() != null){
            existing.setUser(request.getUser());
        }
    }
}
