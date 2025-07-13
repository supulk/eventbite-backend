package com.eventbite.eventbite_backend.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public String getProfile(){
        return "This is your profile";
    }

    @GetMapping("/settings")
    public String getSettings(){
        return "settings";
    }

    @PutMapping("/settings/updateinfo")
    public String updateProfile(){
        return "profile updated";
    }

    @PutMapping("/settings/updatepref")
    public String updatePreferences(){
        return "Settings updated";
    }

    @DeleteMapping("/settings/deleteuser")
    public String deleteProfile(){
        return "User deleted";
    }

    @PutMapping("/change-password")
    public String changePassword(){
        return "";
    }

    @GetMapping("/events")
    public String getEvents(){
        return "";
    }

    @GetMapping("/registrations")
    public void getRegistrations(){}

    @PutMapping("/register")
    public void register(){}

    @DeleteMapping("/unregister")
    public void unregister(){}
}
