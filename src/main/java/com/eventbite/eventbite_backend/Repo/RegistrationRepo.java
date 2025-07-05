package com.eventbite.eventbite_backend.Repo;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.Registration;
import com.eventbite.eventbite_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepo extends JpaRepository<Registration, Long> {

    List<Registration> findByEvent(Event event);

    List<Registration> findByUserIsNotNull(User user);


}
