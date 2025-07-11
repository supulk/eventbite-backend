package com.eventbite.eventbite_backend.Repo;

import com.eventbite.eventbite_backend.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {

    Event searchEventByPublicId(String publicId);
}
