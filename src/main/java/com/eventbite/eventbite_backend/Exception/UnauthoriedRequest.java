package com.eventbite.eventbite_backend.Exception;

public class UnauthoriedRequest extends RuntimeException {
    public UnauthoriedRequest(String message) {
        super(message);
    }
}
