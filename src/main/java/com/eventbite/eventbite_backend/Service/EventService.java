package com.eventbite.eventbite_backend.Service;

import com.eventbite.eventbite_backend.DTO.Event.EventRequestDTO;
import com.eventbite.eventbite_backend.DTO.Event.PrivateEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Event.PublicEventResponseDTO;
import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.User;
import com.eventbite.eventbite_backend.Exception.EventNotfoundException;
import com.eventbite.eventbite_backend.DTO.Event.EventMapper;
import com.eventbite.eventbite_backend.Exception.UnauthoriedRequest;
import com.eventbite.eventbite_backend.Repo.EventRepo;
import com.eventbite.eventbite_backend.Repo.UserRepo;
import jakarta.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EventService {
    @Autowired
    EventMapper EventMapper;
    @Autowired
    JWTService jwtService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    EventRepo repo;
    @Autowired
    private EventMapper eventMapper;

    //search by id
    public Object searchEventByPublicId(String id, String authHeader) throws EventNotfoundException {
        Event event = repo.searchByPublicId(id);
        String email = getEmailFromHeader(authHeader);
        if(event == null){
            throw new EventNotfoundException("Event not found");
        }
        if (event.getOrganizer().getEmail().equals(email)){
            return EventMapper.toPrivateResponse(event);
        }else {
            return EventMapper.toPublicResponse(event);
        }
    }


    public PrivateEventResponseDTO getFullEvent(String id, String authHeader) throws EventNotfoundException {
        String email = getEmailFromHeader(authHeader);
        Event event = repo.searchByPublicId(id);
        if(event == null){
            throw new EventNotfoundException("Event not found");
        }
        if (!email.equals(event.getOrganizer().getEmail())){
            throw new UnauthoriedRequest("You dont have access for this");
        }
        return EventMapper.toPrivateResponse(event);
    }


    public List<PrivateEventResponseDTO> getAllByUser(String id, String authHeader) throws EventNotfoundException {
        String email = getEmailFromHeader(authHeader);
        User user = userRepo.findByEmail(email);
        List<Event> eventList = repo.getAllByOrganizer(user);
        if (eventList==null){throw new EventNotfoundException("No events found");}
        List<PrivateEventResponseDTO> dtoList = new ArrayList<>();
        eventList.forEach(event ->
            dtoList.add(eventMapper.toPrivateResponse(event))
        );
        return dtoList;
    }


    //add event
    public PrivateEventResponseDTO addEvent(EventRequestDTO request, String authHeader){
        String email = getEmailFromHeader(authHeader);

        Event event = EventMapper.toEntity(request);
        event.setPublicId(generatePublicId());
        event.setOrganizer(userRepo.findByEmail(email));
        event.setDateCreated(LocalDateTime.now());
        return EventMapper.toPrivateResponse(repo.save(event));
    }


    @Transactional
    public PrivateEventResponseDTO updateEvent(EventRequestDTO request, String authHeader)throws Exception{
        Event event = repo.searchByPublicId(request.getPublicId());

        String email = getEmailFromHeader(authHeader);
        if (!email.equals(event.getOrganizer().getEmail())){
            throw new UnauthoriedRequest("You dont have access for this");
        }

        if(event == null){
            throw new EventNotfoundException("Event Id not found in the database");
        }
        EventMapper.UpdateEventEntity(request, event);
        Event updated = repo.save(event);
        return EventMapper.toPrivateResponse(updated);
    }

    public PrivateEventResponseDTO deleteEvent(EventRequestDTO request, String authHeader) throws EventNotfoundException {
        Event event = repo.searchByPublicId(request.getPublicId());

        String email = getEmailFromHeader(authHeader);
        if (!email.equals(event.getOrganizer().getEmail())){
            throw new UnauthoriedRequest("You dont have access for this");
        }
        if(event == null){
            throw new EventNotfoundException("Event Id not found in the database");
        }

        PrivateEventResponseDTO e = EventMapper.toPrivateResponse(event);
        repo.delete(event);
        return e;
    }


    public String generatePublicId(){
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(10);
        }while(repo.existsByPublicId(id));
        return id;
    }

    public String getEmailFromHeader(String authHeader){
        String token;
        String email = "";
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            email = jwtService.extractUserName(token);
        }
        return email;
    }

}
