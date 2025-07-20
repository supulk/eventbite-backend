package com.eventbite.eventbite_backend.Controller;

import com.eventbite.eventbite_backend.DTO.APIResponse.ApiResponse;
import com.eventbite.eventbite_backend.DTO.Auth.UserLoginRequestDTO;
import com.eventbite.eventbite_backend.DTO.Event.PrivateEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Event.PublicEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.User.*;
import com.eventbite.eventbite_backend.Exception.DuplicateInputException;
import com.eventbite.eventbite_backend.Exception.EventNotfoundException;
import com.eventbite.eventbite_backend.Exception.RegistrationsNotFoundException;
import com.eventbite.eventbite_backend.Exception.UserNotFoundException;
import com.eventbite.eventbite_backend.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<?>> getProfile(@Valid @RequestParam String id, @RequestHeader("Authorization") String authHeader) throws UserNotFoundException {
        Object dto = service.getProfile(id, authHeader);
        ApiResponse<Object> response = new ApiResponse<> (true, "Profile found", dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/settings")
    public ResponseEntity<ApiResponse<UserSettingsDTO>> getSettings(@Valid @RequestHeader("Authorization") String authHeader) throws UserNotFoundException {
        UserSettingsDTO dto = service.getSettings(authHeader);
        ApiResponse<UserSettingsDTO> response = new ApiResponse<> (true, "User Settings", dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/settings/update")
    public ResponseEntity<ApiResponse<UserSettingsDTO>> updateProfile(@Valid @RequestBody UserSettingsDTO request, @RequestHeader("Authorization") String authHeader) throws UserNotFoundException {
        UserSettingsDTO dto = service.updateSettings(request, authHeader);
        ApiResponse<UserSettingsDTO> response = new ApiResponse<> (true, "User Settings Updated", dto);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/settings/deleteuser")
    public ResponseEntity<ApiResponse<String>> deleteProfile(@Valid @RequestBody UserPasswordVariificationDTO request, @RequestHeader("Authorization") String authHeader) throws UserNotFoundException {
        String result = service.deleteProfile(request, authHeader);
        ApiResponse<String> response = new ApiResponse<> (true, "", result);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse<String>> changePassword(@Valid @RequestBody UserPasswordChangeRequestDTO request, @RequestHeader("Authorization") String authHeader) throws UserNotFoundException {
        String result = service.changePassword(request, authHeader);
        ApiResponse<String> response = new ApiResponse<> (true, "", result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/events")
    public ResponseEntity<ApiResponse<List<PrivateEventResponseDTO>>> getAllEvents(@Valid @RequestHeader("Authorization") String authHeader) throws UserNotFoundException, EventNotfoundException {
        List<PrivateEventResponseDTO> eventList = service.getAllEventsByUser(authHeader);
        ApiResponse<List<PrivateEventResponseDTO>> response = new ApiResponse<> (true, "", eventList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/registrations")
    public ResponseEntity<ApiResponse<List<PublicEventResponseDTO>>>  getRegistrations(@Valid @RequestHeader("Authorization") String authHeader) throws UserNotFoundException, RegistrationsNotFoundException {
        List<PublicEventResponseDTO> registrationsList = service.getAllRegistrationsByUser(authHeader);
        ApiResponse<List<PublicEventResponseDTO>> response = new ApiResponse<List<PublicEventResponseDTO>> (true, "", registrationsList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestParam String eventId, @RequestHeader("Authorization") String authHeader) throws DuplicateInputException {
        String dto = service.registerToEvent(eventId, authHeader);
        ApiResponse<String> response = new ApiResponse<> (true, "", dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<ApiResponse<String>> unregister(@Valid @RequestParam String eventId, @RequestHeader("Authorization") String authHeader) throws RegistrationsNotFoundException {
        String result = service.unregisterEvent(eventId, authHeader);
        ApiResponse<String> response = new ApiResponse<> (true, "", result);
        return ResponseEntity.ok(response);
    }
}
