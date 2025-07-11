package com.eventbite.eventbite_backend.Service;

import com.eventbite.eventbite_backend.DTO.Auth.UserLoginRequestDTO;
import com.eventbite.eventbite_backend.DTO.Auth.UserSignupRequestDTO;
import com.eventbite.eventbite_backend.DTO.UserResponseDTO;
import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.User;
import com.eventbite.eventbite_backend.Entity.UserRegistration;
import com.eventbite.eventbite_backend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class AuthService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTService jwtService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);




    public UserResponseDTO registerUser(UserSignupRequestDTO request) throws Exception {
        if (repo.findByEmail(request.getEmail()) != null){
            throw new Exception("User Already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setDateCreated(LocalDateTime.now());
        user.setOrganizedEvents(new ArrayList<Event>());
        user.setUserRegistrations(new ArrayList<UserRegistration>());

        User savedUser = repo.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getDateCreated(),
                savedUser.getOrganizedEvents(),
                savedUser.getUserRegistrations()
        );
    }




    public String varifyUser(UserLoginRequestDTO request) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        if (authentication.isAuthenticated()){
            return jwtService.generateToken(request.getEmail());
        }else {return "Invalid";}
    }
}
