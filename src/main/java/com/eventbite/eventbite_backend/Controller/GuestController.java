package com.eventbite.eventbite_backend.Controller;

import com.eventbite.eventbite_backend.DTO.APIResponse.ApiResponse;
import com.eventbite.eventbite_backend.DTO.Event.PublicEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Guest.GuestRegisterationRequestDTO;
import com.eventbite.eventbite_backend.DTO.Guest.GuestRegistrationsInquryDTO;
import com.eventbite.eventbite_backend.DTO.Guest.GuestRegistrationsResponseDTO;
import com.eventbite.eventbite_backend.Service.GuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {
    @Autowired
    GuestService service;

    //Get all registrations done by a guest
    @GetMapping("/registrations")
    public ResponseEntity<ApiResponse<List<PublicEventResponseDTO>>> getGuestRegistrations(@Valid @RequestBody GuestRegistrationsInquryDTO request){
        List<PublicEventResponseDTO> list = service.getGuestRegustrations(request);
    }

    //guest register to a new event
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerEvent(@Valid @RequestBody GuestRegisterationRequestDTO request){
        GuestRegistrationsResponseDTO dto = service.register(request);
    }

    //guest unregister from an event
    @DeleteMapping("/unregister")
    public ResponseEntity<ApiResponse<String>> unRegisterEvent(@Valid @RequestBody GuestRegisterationRequestDTO request){
        String result = service.unRegister(request);
    }

    //get all registrations for an event
}