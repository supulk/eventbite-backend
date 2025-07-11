package com.eventbite.eventbite_backend.Controller;

import com.eventbite.eventbite_backend.DTO.APIResponse.ApiResponse;
import com.eventbite.eventbite_backend.DTO.Event.EventRequestDTO;
import com.eventbite.eventbite_backend.DTO.Event.PrivateEventResponseDTO;
import com.eventbite.eventbite_backend.DTO.Event.PublicEventResponseDTO;
import com.eventbite.eventbite_backend.Exception.EventNotfoundException;
import com.eventbite.eventbite_backend.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService service;

    //Search an event using public id
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PublicEventResponseDTO>> searchEvent(@RequestBody String id) throws EventNotfoundException {
        PublicEventResponseDTO dto = service.searchEventByPublicId(id);
        ApiResponse<PublicEventResponseDTO> response = new ApiResponse<> (true, "Event found", dto);
        return ResponseEntity.ok(response);
    }

    //add new event (auth needed)
    @PostMapping("/add")
    public String addEvent(EventRequestDTO request){
        PrivateEventResponseDTO dto = service.addEvent(request);
        return "Event";
    }

    //update event (auth needed)
    @PutMapping("/update")
    public String updateEvent(){
        return "";
    }

    //delete event (auth needed)
    @DeleteMapping("/delete")
    public String deleteEvent(){
        return "";
    }

}
