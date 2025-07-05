package com.eventbite.eventbite_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "registration")
public class Registration {

    @Id
    private long id;

    private String guestName;

    private String guestEmail;

    @Column(nullable = false)
    private String registrationType;

    @Column(nullable = false)
    private String privacy;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
