package com.msvc.ai_scanner.services.exceptions;

public class UserDontHaveRegistersException extends RuntimeException {
    public UserDontHaveRegistersException(String message) {
        super(message);
    }
}
