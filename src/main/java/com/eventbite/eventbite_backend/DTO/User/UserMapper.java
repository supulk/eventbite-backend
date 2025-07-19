package com.eventbite.eventbite_backend.DTO.User;

import com.eventbite.eventbite_backend.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserPublicProfileResponseDTO entityToPublicProfile(User user){
        return new UserPublicProfileResponseDTO(
                user.getUsername(),
                user.getPublicUserId(),
                user.getEmail()
        );
    }

    public UserPrivateProfileResponseDTO entityToPrivateProfile(User user){
        return new UserPrivateProfileResponseDTO(
                user.getUsername(),
                user.getEmail(),
                user.getPublicUserId(),
                user.getDateCreated().toString()
        );
    }
}
