package com.eventbite.eventbite_backend.Service;

import com.eventbite.eventbite_backend.DTO.Auth.UserPrinciple;
import com.eventbite.eventbite_backend.Entity.User;
import com.eventbite.eventbite_backend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repo.findByEmail(email);

        if (user == null){
            System.out.println("User not Found");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrinciple(user);
    }
}
