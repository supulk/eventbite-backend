package com.eventbite.eventbite_backend.Mapper;

import com.eventbite.eventbite_backend.DTO.Event.EventRequestDTO;
import com.eventbite.eventbite_backend.DTO.Event.PublicEventResponseDTO;
import com.eventbite.eventbite_backend.Entity.Event;

import java.util.ArrayList;

public class EventMapper {
    public static Event toEntity(EventRequestDTO request){
        Event event = new Event();

        event.setTitle(request.getTitle());
        if (request.getDescription() != null){
            event.setDescription(request.getDescription());
        }
        event.setEventDate(request.getEventDate());
        event.setLocation(request.getLocation());
        event.setPublicId(request.getPublicId());
        event.setPrivacy(request.getPrivacy());
        event.setDateCreated(request.getDateCreated());
        event.setOrganizer(request.getOrganizer());
        if (request.getUserRegistrations() != null){
            event.setUserRegistrations(request.getUserRegistrations());
        }else {
            event.setUserRegistrations(new ArrayList<>());
        }
        return event;
    }

    public static PublicEventResponseDTO toPublicResponse(Event entity){
        return new PublicEventResponseDTO(
                entity.getTitle(),
                entity.getDescription(),
                entity.getEventDate(),
                entity.getLocation(),
                entity.getPublicId(),
                entity.getPrivacy(),
                entity.getDateCreated(),
                entity.getOrganizer().getUsername()
        );
    }

    public static void UpdateEventEntity(EventRequestDTO request, Event existing){
        if (request.getTitle() != null){
            existing.setTitle(request.getTitle());
        }
        if (request.getDescription() != null){
            existing.setDescription(request.getDescription());
        }
        if (request.getEventDate() != null){
            existing.setEventDate(request.getEventDate());
        }
        if (request.getLocation() != null){
            existing.setLocation(request.getLocation());
        }
        if (request.getPublicId() != null){
            existing.setPublicId(request.getPublicId());
        }
        if (request.getPrivacy() != null){
            existing.setPrivacy(request.getPrivacy());
        }
        if (request.getDateCreated() != null){
            existing.setDateCreated(request.getDateCreated());
        }
        if (request.getOrganizer() != null){
            existing.setOrganizer(request.getOrganizer());
        }
        if (request.getUserRegistrations() != null){
            existing.setUserRegistrations(request.getUserRegistrations());
        }
    }
}
