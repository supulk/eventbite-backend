package com.eventbite.eventbite_backend.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {
    //Search an event using public id
    @GetMapping("/search")
    public String searchEvent(){
        return "Event";
    }

    //add new event (auth needed)

    //update event (auth needed)

    //delete event (auth needed)

    //get everyone registered for the event
}
