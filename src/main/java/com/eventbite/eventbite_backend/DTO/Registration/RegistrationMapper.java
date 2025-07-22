package com.eventbite.eventbite_backend.DTO.Registration;

import com.eventbite.eventbite_backend.Entity.GuestRegistration;
import com.eventbite.eventbite_backend.Entity.UserRegistration;
import org.springframework.stereotype.Component;

public class RegistrationMapper {
    public RegistrationsDTO userEntityToDto(UserRegistration entity){
        RegistrationsDTO returnDto = new RegistrationsDTO();
        returnDto.setUser(entity.getUser().getUsername());
        returnDto.setUserId(entity.getUser().getPublicUserId());
        returnDto.setEventId(entity.getEvent().getPublicId());
        returnDto.setStatus("member");
        return returnDto;
    }

    public RegistrationsDTO guestEntityToDto(GuestRegistration entity){
        RegistrationsDTO returnDto = new RegistrationsDTO();
        returnDto.setUser(entity.getName());
        returnDto.setUserId(null);
        returnDto.setEventId(entity.getEvent().getPublicId());
        returnDto.setStatus("guest");
        return returnDto;
    }
}
