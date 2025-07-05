package com.eventbite.eventbite_backend.Repo;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.UserRegistration;
import com.eventbite.eventbite_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRegistrationRepo extends JpaRepository<UserRegistration, Long> {

    List<UserRegistration> findByEvent(Event event);

    List<UserRegistration> findByUser(User user);

}
