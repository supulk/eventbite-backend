package com.eventbite.eventbite_backend.Repo;

import com.eventbite.eventbite_backend.Entity.Event;
import com.eventbite.eventbite_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {

    Event searchByPublicId(String publicId);

    Boolean existsByPublicId(String publicId);

    List<Event> getAllByOrganizer(User organizer);
}
