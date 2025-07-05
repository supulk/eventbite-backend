package com.eventbite.eventbite_backend.Repo;

import com.eventbite.eventbite_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository <User, Long> {
    User findByUsername(String username);
}
