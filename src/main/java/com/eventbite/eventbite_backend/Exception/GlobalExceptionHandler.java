package com.eventbite.eventbite_backend.Exception;

import com.eventbite.eventbite_backend.DTO.APIResponse.ApiResponse;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventNotfoundException.class)
    public ResponseEntity<ApiResponse<String>> handleEventNotFoundException(EventNotfoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    //happens when DTO validation fails (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArguementNotValidException(MethodArgumentNotValidException e){
        ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    //Happens when Entity validation fails on persist
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleConstraintViolationException(ConstraintViolationException e){
        ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    //Database-level constraint fails
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    //Hibernate detects missing required property
    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ApiResponse<String>> handlePropertyValueException(PropertyValueException e){
        ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    //Unauthorized Request
    @ExceptionHandler(UnauthoriedRequest.class)
    public ResponseEntity<ApiResponse<String>> handleUnauthoriedRequestException(UnauthoriedRequest e){
        ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), "unauthorized");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException e){
        ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RegistrationsNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleRegistrationsNotFoundException(RegistrationsNotFoundException e){
        ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateInputException.class)
    public ResponseEntity<ApiResponse<String>> handleDuplicateInputException(DuplicateInputException e){
        ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), "duplicate");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}
