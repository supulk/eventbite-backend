package com.eventbite.eventbite_backend.Controller;

import com.eventbite.eventbite_backend.DTO.APIResponse.ApiResponse;
import com.eventbite.eventbite_backend.DTO.Event.EventRequestDTO;
import com.eventbite.eventbite_backend.DTO.Event.PrivateEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Event.PublicEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Guest.PublicGuestResponseDTO;
import com.eventbite.eventbite_backend.DTO.User.UserPublicProfileResponseDTO;
import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.GuestRegistration;
import com.eventbite.eventbite_backend.Exception.EventNotfoundException;
import com.eventbite.eventbite_backend.Exception.UnauthoriedRequest;
import com.eventbite.eventbite_backend.Repo.GuestRegistrationRepo;
import com.eventbite.eventbite_backend.Repo.UserRegistrationRepo;
import com.eventbite.eventbite_backend.Service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService service;

    //Search an event using public id
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<?>> searchEvent(@Valid @RequestParam String id, @RequestHeader("Authorization") String authHeader) throws EventNotfoundException {
        Object dto = service.searchEventByPublicId(id, authHeader);
        ApiResponse<Object> response = new ApiResponse<> (true, "Event found", dto);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get-all-user-registrations")
    public ResponseEntity<ApiResponse<List<UserPublicProfileResponseDTO>>> getAllUserRegistrations(@Valid @RequestParam String EventId, @RequestHeader("Authorization") String authHeader) throws EventNotfoundException,UnauthoriedRequest {
        List<UserPublicProfileResponseDTO> returnList = service.getAllUserRegistrations(EventId, authHeader);
        ApiResponse<List<UserPublicProfileResponseDTO>> response = new ApiResponse<>(true, "Users Found", returnList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-guest-registrations")
    public ResponseEntity<ApiResponse<List<PublicGuestResponseDTO>>> getAllGuestRegistrations(@Valid @RequestParam String EventId, @RequestHeader("Authorization") String authHeader) throws EventNotfoundException,UnauthoriedRequest {
        List<PublicGuestResponseDTO> returnList = service.getAllGuestRegistrations(EventId, authHeader);
        ApiResponse<List<PublicGuestResponseDTO>> response = new ApiResponse<>(true, "Guests Found", returnList);
        return ResponseEntity.ok(response);
    }

    //add new event (auth needed)
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<PrivateEventResponseDTO>> addEvent(@Valid @RequestBody EventRequestDTO request, @RequestHeader("Authorization") String authHeader){
        PrivateEventResponseDTO dto = service.addEvent(request, authHeader);
        ApiResponse<PrivateEventResponseDTO> response = new ApiResponse<>(true, "Event Added", dto);
        return ResponseEntity.ok(response);
    }

    //update event (auth needed)
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<PrivateEventResponseDTO>> updateEvent(@Valid @RequestBody EventRequestDTO request, @RequestHeader("Authorization") String authHeader) throws Exception {
        PrivateEventResponseDTO dto = service.updateEvent(request, authHeader);
        ApiResponse<PrivateEventResponseDTO> response = new ApiResponse<>(true, "Event Updated", dto);
        return ResponseEntity.ok(response);
    }

    //delete event (auth needed)
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<PrivateEventResponseDTO>> deleteEvent(@Valid @RequestParam String EventId, @RequestHeader("Authorization") String authHeader) throws EventNotfoundException {
        PrivateEventResponseDTO dto = service.deleteEvent(EventId, authHeader);
        ApiResponse<PrivateEventResponseDTO> response = new ApiResponse<>(true, "Event Deleted", dto);
        return ResponseEntity.ok(response);
    }
}
