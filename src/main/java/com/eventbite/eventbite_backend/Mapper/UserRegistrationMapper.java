package com.eventbite.eventbite_backend.Mapper;

import com.eventbite.eventbite_backend.DTO.UserRegistrationRequestDTO;
import com.eventbite.eventbite_backend.DTO.UserRegistrationResponseDTO;
import com.eventbite.eventbite_backend.Entity.UserRegistration;

public class UserRegistrationMapper {
    public static UserRegistration toEntity(UserRegistrationRequestDTO request){
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setUser(request.getUser());
        userRegistration.setEvent(request.getEvent());
        userRegistration.setRegistrationDate(request.getRegistrationDate());

        return userRegistration;
    }

    public static UserRegistrationResponseDTO toResponse(UserRegistration entity){
        return new UserRegistrationResponseDTO(
                entity.getId(),
                entity.getUser(),
                entity.getEvent(),
                entity.getRegistrationDate()
        );
    }

    public static void UpdateRegistrationEntity(UserRegistrationRequestDTO request, UserRegistration existing){
        if (request.getUser() != null){
            existing.setUser(request.getUser());
        }
        if (request.getEvent() != null){
            existing.setEvent(request.getEvent());
        }
        if (request.getRegistrationDate() != null){
            existing.setRegistrationDate(request.getRegistrationDate());
        }


    }
}
