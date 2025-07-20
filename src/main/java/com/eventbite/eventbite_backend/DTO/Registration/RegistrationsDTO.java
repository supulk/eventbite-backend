package com.eventbite.eventbite_backend.DTO.Registration;

import lombok.Data;

@Data
public class RegistrationsDTO {
    String user;
    String userId; //nullable for guests
    String eventId;
    String registeredDate;
    String Status;
}