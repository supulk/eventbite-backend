package com.eventbite.eventbite_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "guest_registration")
public class GuestRegistration {
    @Id
    @Column(nullable = false)
    private String email;

    private String name;

    @Column(name = "registered_at")
    private LocalDateTime registeredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
