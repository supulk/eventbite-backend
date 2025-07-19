package com.eventbite.eventbite_backend.Exception;

//when input data already in the database
public class DuplicateInputException extends Exception {
    public DuplicateInputException(String message) {
        super(message);
    }
}
