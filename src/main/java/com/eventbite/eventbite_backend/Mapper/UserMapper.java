package com.eventbite.eventbite_backend.Mapper;

import com.eventbite.eventbite_backend.DTO.UserRequestDTO;
import com.eventbite.eventbite_backend.DTO.UserResponseDTO;
import com.eventbite.eventbite_backend.Entity.User;

public class UserMapper {
    public static User toEntity(UserRequestDTO request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setDateCreated(request.getDateCreated());
        user.setOrganizedEvents(request.getOrganizedEvents());
        user.setRegistrations(request.getRegistrations());

        return user;
    }

    public static UserResponseDTO toResponse(User user){

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getDateCreated(),
                user.getOrganizedEvents(),
                user.getRegistrations()
        );
    }

    public static void UpdateUserEntity(UserRequestDTO request, User existing){
        if (request.getUsername() != null){
            existing.setUsername(request.getUsername());
        }
        if (request.getEmail() != null){
            existing.setEmail(request.getEmail());
        }
        if (request.getPassword() != null){
            existing.setPassword(request.getPassword());
        }
        if (request.getDateCreated() != null){
            existing.setDateCreated(request.getDateCreated());
        }
        if (request.getOrganizedEvents() != null){
            existing.setOrganizedEvents(request.getOrganizedEvents());
        }
        if (request.getRegistrations() != null){
            existing.setRegistrations(request.getRegistrations());
        }

    }
}
