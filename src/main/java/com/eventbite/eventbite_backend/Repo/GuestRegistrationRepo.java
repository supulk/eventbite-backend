package com.eventbite.eventbite_backend.Repo;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.GuestRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRegistrationRepo extends JpaRepository<GuestRegistration, Long> {

    List<GuestRegistration> findByEvent(Event event);

    List<GuestRegistration> findByEmail(String email);
}
