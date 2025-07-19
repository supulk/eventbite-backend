package com.eventbite.eventbite_backend.Service;

import com.eventbite.eventbite_backend.DTO.Auth.UserLoginRequestDTO;
import com.eventbite.eventbite_backend.DTO.Event.EventMapper;
import com.eventbite.eventbite_backend.DTO.Event.PrivateEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Event.PublicEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.User.*;
import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.User;
import com.eventbite.eventbite_backend.Entity.UserRegistration;
import com.eventbite.eventbite_backend.Exception.*;
import com.eventbite.eventbite_backend.Repo.EventRepo;
import com.eventbite.eventbite_backend.Repo.UserRegistrationRepo;
import com.eventbite.eventbite_backend.Repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo repo;
    @Autowired
    UserMapper userMapper;
    @Autowired
    EventService eventService;
    @Autowired
    EventRepo eventRepo;
    @Autowired
    EventMapper eventMapper;
    @Autowired
    UserRegistrationRepo userRegistrationRepo;
    @Autowired
    private AuthenticationManager authManager;

    public Object getProfile(@Valid String id, String authHeader) throws UserNotFoundException {
        if (id.equals("null")){
            User user = repo.findByEmail(eventService.getEmailFromHeader(authHeader));
            return userMapper.entityToPrivateProfile(user);
        }else {
            User user = repo.findByPublicUserId(id);
            if (user==null){throw new UserNotFoundException("User not found");}
            return userMapper.entityToPublicProfile(user);
        }
    }

    public UserSettingsDTO getSettings(@Valid String authHeader) throws UserNotFoundException {
        User user = repo.findByEmail(eventService.getEmailFromHeader(authHeader));
        if (user==null){throw new UserNotFoundException("User not found");}
        return new UserSettingsDTO(user.getSettings());
    }

    @Transactional
    public UserSettingsDTO updateSettings(@Valid UserSettingsDTO request, String authHeader) throws UserNotFoundException {
        User user = repo.findByEmail(eventService.getEmailFromHeader(authHeader));
        if (user==null){throw new UserNotFoundException("User not found");}
        user.setSettings(request.getSettings());
        return new UserSettingsDTO(user.getSettings());
    }

    @Transactional
    public String deleteProfile(@Valid UserPasswordVariificationDTO request, String authHeader) throws UserNotFoundException {
        User user = repo.findByEmail(eventService.getEmailFromHeader(authHeader));
        if (user==null){throw new UserNotFoundException("User not found");}

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), request.getPassword()));
        if (authentication.isAuthenticated()){
            repo.delete(user);
            return "Deleted";
        }else {
            throw new UnauthoriedRequest("Invalid credentials");
        }
    }

    @Transactional
    public String changePassword(@Valid UserPasswordChangeRequestDTO request, String authHeader) throws UserNotFoundException {
        User user = repo.findByEmail(eventService.getEmailFromHeader(authHeader));
        if (user==null){throw new UserNotFoundException("User not found");}

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), request.getOldPassword()));
        if (authentication.isAuthenticated()){
            user.setPassword(request.getNewPassword());
            return "Password Updated";
        }else {
            throw new UnauthoriedRequest("Invalid credentials");
        }
    }


    public List<PrivateEventResponseDTO> getAllEventsByUser(@Valid String authHeader) throws UserNotFoundException, EventNotfoundException {
        User user = repo.findByEmail(eventService.getEmailFromHeader(authHeader));
        if (user==null){throw new UserNotFoundException("User not found");}

        List<Event> entityList = eventRepo.getAllByOrganizer(user);
        if (entityList==null){throw new EventNotfoundException("No events found");}
        List<PrivateEventResponseDTO> dtoList = new ArrayList<>();
        entityList.forEach(event ->
                dtoList.add(eventMapper.toPrivateResponse(event))
        );
        return dtoList;
    }

    public List<PublicEventResponseDTO> getAllRegistrationsByUser(@Valid String authHeader) throws UserNotFoundException, RegistrationsNotFoundException {
        User user = repo.findByEmail(eventService.getEmailFromHeader(authHeader));
        if (user==null){throw new UserNotFoundException("User not found");}

        List<UserRegistration> entityList = userRegistrationRepo.findByUser(user);
        if (entityList==null){throw new RegistrationsNotFoundException("No registrations found");}

        List<PublicEventResponseDTO> dtoList = new ArrayList<>();

        entityList.forEach(item ->
                dtoList.add(eventMapper.toPublicResponse(item.getEvent()))
                );

        return dtoList;
    }

    @Transactional
    public String registerToEvent(@Valid String eventId, String authHeader) throws DuplicateInputException {
        UserRegistration existing = userRegistrationRepo.findByUserAndEvent(repo.findByEmail(eventService.getEmailFromHeader(authHeader)), eventRepo.searchByPublicId(eventId));
        if (existing != null){
            throw new DuplicateInputException("Entry Already exists");
        }

        UserRegistration registration = new UserRegistration();
        registration.setUser(repo.findByEmail(eventService.getEmailFromHeader(authHeader)));
        registration.setEvent(eventRepo.searchByPublicId(eventId));
        registration.setRegistrationDate(LocalDateTime.now());
        userRegistrationRepo.save(registration);
        return "Registered";
    }

    @Transactional
    public String unregisterEvent(@Valid String eventId, String authHeader) throws RegistrationsNotFoundException {
        UserRegistration existing = userRegistrationRepo.findByUserAndEvent(repo.findByEmail(eventService.getEmailFromHeader(authHeader)), eventRepo.searchByPublicId(eventId));
        if (existing == null){
            throw new RegistrationsNotFoundException("Entry Not Found");
        }
        userRegistrationRepo.delete(existing);
        return "UnRegistered";
    }
}
