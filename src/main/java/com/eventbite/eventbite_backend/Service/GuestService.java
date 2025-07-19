package com.eventbite.eventbite_backend.Service;

import com.eventbite.eventbite_backend.DTO.Event.EventMapper;
import com.eventbite.eventbite_backend.DTO.Event.PublicEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Guest.GuestMapper;
import com.eventbite.eventbite_backend.DTO.Guest.GuestRegistrationRequestDTO;
import com.eventbite.eventbite_backend.DTO.Guest.GuestRegistrationsInqiryDTO;
import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.GuestRegistration;
import com.eventbite.eventbite_backend.Exception.DuplicateInputException;
import com.eventbite.eventbite_backend.Exception.EventNotfoundException;
import com.eventbite.eventbite_backend.Exception.RegistrationsNotFoundException;
import com.eventbite.eventbite_backend.Repo.EventRepo;
import com.eventbite.eventbite_backend.Repo.GuestRegistrationRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService {
    @Autowired
    GuestRegistrationRepo guestRepo;
    @Autowired
    EventMapper eventMapper;
    @Autowired
    EventRepo eventRepo;
    @Autowired
    GuestMapper guestMapper;

    public List<PublicEventResponseDTO> getGuestRegistrations(@Valid GuestRegistrationsInqiryDTO inquiry) throws EventNotfoundException {
        List<GuestRegistration> entityList = guestRepo.findByEmail(inquiry.getEmail());
        if (entityList.isEmpty()){throw new EventNotfoundException("User does not have any events");}
        List<PublicEventResponseDTO> returnList = new ArrayList<>();
        entityList.forEach(item ->
                returnList.add(eventMapper.toPublicResponse(item.getEvent())));
        return returnList;
    }

    @Transactional
    public String register(@Valid GuestRegistrationRequestDTO request) throws EventNotfoundException, DuplicateInputException {
        Event event = eventRepo.searchByPublicId(request.getEventId());
        if (event==null){throw new EventNotfoundException("Event not found");}

        GuestRegistration ifExisting = guestRepo.findByEmailAndEvent(request.getEmail(), event);
        if (ifExisting != null){throw new DuplicateInputException("Entry Already exists");}

        GuestRegistration newItem = guestMapper.toEntity(request, event);
        guestRepo.save(newItem);
        return "Registered";
    }

    @Transactional
    public String unRegister(@Valid GuestRegistrationRequestDTO request) throws EventNotfoundException, RegistrationsNotFoundException {
        Event event = eventRepo.searchByPublicId(request.getEventId());
        if (event==null){throw new EventNotfoundException("Event not found");}

        GuestRegistration ifExisting = guestRepo.findByEmailAndEvent(request.getEmail(), event);
        if (ifExisting == null){throw new RegistrationsNotFoundException("Entry not Found");}

        guestRepo.delete(ifExisting);
        return "Unregistered";
    }
}
