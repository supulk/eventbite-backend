package com.eventbite.eventbite_backend.DTO.Event;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

@Component
public class EventMapper {

    @Autowired
    UserRepo userRepo;


    public Event toEntity(EventRequestDTO request){
        Event event = new Event();

        event.setTitle(request.getTitle());
        if (request.getDescription() != null){
            event.setDescription(request.getDescription());
        }

        long epochMillis = request.getEventDate();
        LocalDateTime dateTime = Instant.ofEpochMilli(epochMillis)
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime();

        event.setEventDate(dateTime);
        event.setLocation(request.getLocation());
        event.setPrivacy(request.getPrivacy());
        event.setDateCreated(request.getDateCreated());
        event.setOrganizer(userRepo.findByPublicUserId(request.getUserId()));
        event.setPublicId(request.getPublicId());
        if (request.getUserRegistrations() != null){
            event.setUserRegistrations(request.getUserRegistrations());
        }else {
            event.setUserRegistrations(new ArrayList<>());
        }

        if (request.getGuestRegistrations() != null){
            event.setGuestRegistrations(request.getGuestRegistrations());
        }else {
            event.setGuestRegistrations(new ArrayList<>());
        }

        return event;
    }



    public PublicEventResponseDTO toPublicResponse(Event entity){
        return new PublicEventResponseDTO(
                entity.getTitle(),
                entity.getDescription(),
                entity.getEventDate(),
                entity.getLocation(),
                entity.getPublicId(),
                entity.getPrivacy(),
                entity.getDateCreated(),
                entity.getOrganizer().getUsername(),
                entity.getOrganizer().getPublicUserId()
        );
    }

    public PrivateEventResponseDTO toPrivateResponse(Event entity){
        return new PrivateEventResponseDTO(
                entity.getTitle(),
                entity.getDescription(),
                entity.getEventDate(),
                entity.getLocation(),
                entity.getPublicId(),
                entity.getPrivacy(),
                entity.getDateCreated(),
                entity.getOrganizer().getPublicUserId(),
                entity.getUserRegistrations(),
                entity.getGuestRegistrations()
        );
    }



    public void UpdateEventEntity(EventRequestDTO request, Event existing){
        if (request.getTitle() != null){
            existing.setTitle(request.getTitle());
        }
        if (request.getDescription() != null){
            existing.setDescription(request.getDescription());
        }
        if (request.getEventDate() != 0){
            long epochMillis = request.getEventDate();
            LocalDateTime dateTime = Instant.ofEpochMilli(epochMillis)
                    .atZone(ZoneOffset.UTC)
                    .toLocalDateTime();

            existing.setEventDate(dateTime);
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
        if (request.getUserId() != null){
            existing.setOrganizer(userRepo.findByPublicUserId(request.getUserId()));
        }
        if (request.getUserRegistrations() != null){
            existing.setUserRegistrations(request.getUserRegistrations());
        }
    }


}
