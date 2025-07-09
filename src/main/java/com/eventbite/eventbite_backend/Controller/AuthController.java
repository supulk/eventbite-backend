package com.eventbite.eventbite_backend.Controller;

import com.eventbite.eventbite_backend.DTO.Auth.UserSignupRequestDTO;
import com.eventbite.eventbite_backend.DTO.UserResponseDTO;
import com.eventbite.eventbite_backend.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    //login


    //signup
    @PostMapping("/signup")
    public UserResponseDTO signup(@RequestBody UserSignupRequestDTO request) throws Exception {
        UserResponseDTO response = null;
        try {
            response = authService.registerUser(request);
        } catch (Exception e) {
            System.out.println("Exception at user signup " + e.getMessage());
            System.out.println(request.getUsername());
            System.out.println(request.getPassword());
            System.out.println(request.getEmail());
        }
        return response;
    }
}
