package com.eventbite.eventbite_backend.Service;

import com.eventbite.eventbite_backend.DTO.Event.PublicEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Guest.GuestRegisterationRequestDTO;
import com.eventbite.eventbite_backend.DTO.Guest.GuestRegistrationsInquryDTO;
import com.eventbite.eventbite_backend.DTO.Guest.GuestRegistrationsResponseDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    public List<PublicEventResponseDTO> getGuestRegustrations(@Valid GuestRegistrationsInquryDTO inquiry) {
        return null;
    }

    public GuestRegistrationsResponseDTO register(@Valid GuestRegisterationRequestDTO request) {
        return null;
    }

    public String unRegister(@Valid GuestRegisterationRequestDTO request) {
        return null;
    }
}
