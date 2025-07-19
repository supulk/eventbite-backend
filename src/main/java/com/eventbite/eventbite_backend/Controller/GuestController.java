package com.eventbite.eventbite_backend.Controller;

import com.eventbite.eventbite_backend.DTO.APIResponse.ApiResponse;
import com.eventbite.eventbite_backend.DTO.Event.PublicEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Guest.GuestRegistrationRequestDTO;
import com.eventbite.eventbite_backend.DTO.Guest.GuestRegistrationsInqiryDTO;
import com.eventbite.eventbite_backend.Exception.DuplicateInputException;
import com.eventbite.eventbite_backend.Exception.EventNotfoundException;
import com.eventbite.eventbite_backend.Exception.RegistrationsNotFoundException;
import com.eventbite.eventbite_backend.Service.GuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController{
    @Autowired
    GuestService service;

    //Get all registrations done by a guest
    @GetMapping("/registrations")
    public ResponseEntity<ApiResponse<List<PublicEventResponseDTO>>> getGuestRegistrations(@Valid @RequestBody GuestRegistrationsInqiryDTO request) throws EventNotfoundException {
        List<PublicEventResponseDTO> list = service.getGuestRegistrations(request);
        ApiResponse<List<PublicEventResponseDTO>> response = new ApiResponse<> (true, "Events Found", list);
        return ResponseEntity.ok(response);
    }

    //guest register to a new event
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerEvent(@Valid @RequestBody GuestRegistrationRequestDTO request) throws DuplicateInputException, EventNotfoundException {
        String result = service.register(request);
        ApiResponse<String> response = new ApiResponse<> (true, "Registered", null);
        return ResponseEntity.ok(response);
    }

    //guest unregister from an event
    @DeleteMapping("/unregister")
    public ResponseEntity<ApiResponse<String>> unRegisterEvent(@Valid @RequestBody GuestRegistrationRequestDTO request) throws RegistrationsNotFoundException, EventNotfoundException {
        String result = service.unRegister(request);
        ApiResponse<String> response = new ApiResponse<> (true, "UnRegistered", null);
        return ResponseEntity.ok(response);
    }

}