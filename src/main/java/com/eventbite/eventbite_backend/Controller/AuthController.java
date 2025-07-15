package com.eventbite.eventbite_backend.Controller;

import com.eventbite.eventbite_backend.DTO.Auth.UserLoginRequestDTO;
import com.eventbite.eventbite_backend.DTO.Auth.UserSignupRequestDTO;
import com.eventbite.eventbite_backend.DTO.User.UserPrivateProfileResponseDTO;
import com.eventbite.eventbite_backend.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    //login
    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequestDTO request) throws Exception{
        return authService.varifyUser(request);
    }


    //signup
    @PostMapping("/signup")
    public UserPrivateProfileResponseDTO signup(@RequestBody UserSignupRequestDTO request) throws Exception {
        UserPrivateProfileResponseDTO response = null;
        try {
            response = authService.registerUser(request);
        } catch (Exception e) {
            System.out.println("Exception at user signup: " + e.getMessage());
        }
        return response;
    }
}
