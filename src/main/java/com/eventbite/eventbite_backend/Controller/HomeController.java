package com.eventbite.eventbite_backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    //Provide homepage details specific to user (auth needed)
    @GetMapping
    public String getUserHome(){
        return "user Home";
    }
}
