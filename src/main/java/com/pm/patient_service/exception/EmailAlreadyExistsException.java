package com.pm.patient_service.exception;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(
            String message, @NotBlank(message = "email is required")
             @Email(message = "email should be valid") String email) {
        super(message);
    }
}
