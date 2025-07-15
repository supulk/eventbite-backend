package com.eventbite.eventbite_backend.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserSettingsDTO {
    List<String> settings;
}
