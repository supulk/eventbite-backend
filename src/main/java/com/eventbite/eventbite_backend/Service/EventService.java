package com.eventbite.eventbite_backend.Service;

import com.eventbite.eventbite_backend.DTO.Event.EventRequestDTO;
import com.eventbite.eventbite_backend.DTO.Event.PrivateEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Event.PublicEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Guest.GuestMapper;
import com.eventbite.eventbite_backend.DTO.Guest.PublicGuestResponseDTO;
import com.eventbite.eventbite_backend.DTO.User.UserMapper;
import com.eventbite.eventbite_backend.DTO.User.UserPublicProfileResponseDTO;
import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.GuestRegistration;
import com.eventbite.eventbite_backend.Entity.User;
import com.eventbite.eventbite_backend.Entity.UserRegistration;
import com.eventbite.eventbite_backend.Exception.EventNotfoundException;
import com.eventbite.eventbite_backend.DTO.Event.EventMapper;
import com.eventbite.eventbite_backend.Exception.UnauthoriedRequest;
import com.eventbite.eventbite_backend.Repo.EventRepo;
import com.eventbite.eventbite_backend.Repo.GuestRegistrationRepo;
import com.eventbite.eventbite_backend.Repo.UserRegistrationRepo;
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
    JWTService jwtService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    EventRepo repo;
    @Autowired
    EventMapper eventMapper;
    @Autowired
    UserRegistrationRepo userRegistrationRepo;
    @Autowired
    GuestRegistrationRepo guestRegistrationRepo;

    GuestMapper guestMapper;

    UserMapper userMapper;

    //search by id
    public Object searchEventByPublicId(String id, String authHeader) throws EventNotfoundException {
        Event event = repo.searchByPublicId(id);
        String email = getEmailFromHeader(authHeader);
        if(event == null){
            throw new EventNotfoundException("Event not found");
        }
        if(email == null){
            throw new UnauthoriedRequest("email not found");
        }
        if (event.getOrganizer().getEmail().equals(email)){
            return eventMapper.toPrivateResponse(event);
        }else {
            return eventMapper.toPublicResponse(event);
        }
    }


    public List<UserPublicProfileResponseDTO> getAllUserRegistrations(String eventId, String header) throws EventNotfoundException {
        Event event = repo.searchByPublicId(eventId);
        if(event == null){
            throw new EventNotfoundException("Event not found");
        }
        if (event.getOrganizer().getEmail().equals(getEmailFromHeader(header))){
            List<UserRegistration> guestEntityList = userRegistrationRepo.findByEvent(event);
            List<UserPublicProfileResponseDTO> responseDTOs = new ArrayList<>();
            guestEntityList.forEach(entity ->
                    userMapper.entityToPublicProfile(entity.getUser())
            );
            return responseDTOs;
        }else {
            throw new UnauthoriedRequest("authHeaderNoMatch");
        }
    }


    public List<PublicGuestResponseDTO> getAllGuestRegistrations(String eventId, String header) throws EventNotfoundException,UnauthoriedRequest {
        Event event = repo.searchByPublicId(eventId);
        if(event == null){
            throw new EventNotfoundException("Event not found");
        }
        if (event.getOrganizer().getEmail().equals(getEmailFromHeader(header))){
            List<GuestRegistration> guestEntityList = guestRegistrationRepo.findByEvent(event);
            List<PublicGuestResponseDTO> responseDTOs = new ArrayList<>();
            guestEntityList.forEach(entity ->
                        responseDTOs.add(guestMapper.entityToPublicResponse(entity))
                    );
            return responseDTOs;
        }else {
            throw new UnauthoriedRequest("authHeaderNoMatch");
        }
    }




    //add event
    public PrivateEventResponseDTO addEvent(EventRequestDTO request, String authHeader){
        String email = getEmailFromHeader(authHeader);

        Event event = eventMapper.toEntity(request);
        event.setPublicId(generatePublicId());
        event.setOrganizer(userRepo.findByEmail(email));
        event.setDateCreated(LocalDateTime.now());
        return eventMapper.toPrivateResponse(repo.save(event));
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
        eventMapper.UpdateEventEntity(request, event);
        Event updated = repo.save(event);
        return eventMapper.toPrivateResponse(updated);
    }

    @Transactional
    public PrivateEventResponseDTO deleteEvent(String request, String authHeader) throws EventNotfoundException {
        Event event = repo.searchByPublicId(request);

        String email = getEmailFromHeader(authHeader);
        if (!email.equals(event.getOrganizer().getEmail())){
            throw new UnauthoriedRequest("You dont have access for this");
        }
        if(event == null){
            throw new EventNotfoundException("Event Id not found in the database");
        }

        PrivateEventResponseDTO e = eventMapper.toPrivateResponse(event);
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
