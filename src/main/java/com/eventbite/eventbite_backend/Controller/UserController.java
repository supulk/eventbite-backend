package com.eventbite.eventbite_backend.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public String getProfile(){
        return "This is your profile";
    }

    @PutMapping("/profile")
    public String updateProfile(){
        return "profile updated";
    }

    @DeleteMapping("/profile")
    public String deleteProfile(){
        return "profile deleted";
    }

    @PutMapping("/change-password")
    public String changePassword(){
        return "";
    }
}
