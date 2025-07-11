package com.eventbite.eventbite_backend.Service;

import com.eventbite.eventbite_backend.DTO.Event.EventRequestDTO;
import com.eventbite.eventbite_backend.DTO.Event.PrivateEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Event.PublicEventResponseDTO;
import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Exception.EventNotfoundException;
import com.eventbite.eventbite_backend.Mapper.EventMapper;
import com.eventbite.eventbite_backend.Repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    EventRepo repo;

    //search by id
    public PublicEventResponseDTO searchEventByPublicId(String id) throws EventNotfoundException {
        Event event = repo.searchEventByPublicId(id);
        if(event == null){
            throw new EventNotfoundException("Event not found");
        }

        return EventMapper.toPublicResponse(event);
    }

    //add event
    public PrivateEventResponseDTO addEvent(EventRequestDTO request){
        return new PrivateEventResponseDTO();
    }
}
