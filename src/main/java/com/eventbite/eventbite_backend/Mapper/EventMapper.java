package com.eventbite.eventbite_backend.Mapper;

import com.eventbite.eventbite_backend.DTO.EventRequestDTO;
import com.eventbite.eventbite_backend.DTO.EventResponseDTO;
import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.Registration;

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
        event.setDateCreated(request.getDateCreated());
        event.setOrganizer(request.getOrganizer());
        if (request.getRegistrations() != null){
            event.setRegistrations(request.getRegistrations());
        }else {
            event.setRegistrations(new ArrayList<>());
        }


        return event;
    }

    public static EventResponseDTO toResponse(Event entity){
        return new EventResponseDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getEventDate(),
                entity.getLocation(),
                entity.getPublicId(),
                entity.getDateCreated(),
                entity.getOrganizer(),
                entity.getRegistrations()
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
        if (request.getDateCreated() != null){
            existing.setDateCreated(request.getDateCreated());
        }
        if (request.getOrganizer() != null){
            existing.setOrganizer(request.getOrganizer());
        }
        if (request.getRegistrations() != null){
            existing.setRegistrations(request.getRegistrations());
        }
    }
}
