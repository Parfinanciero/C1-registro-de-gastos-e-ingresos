package com.msvc.ai_scanner.services.exceptions;

public class CantCreateBillException extends RuntimeException {
    public CantCreateBillException(String message) {
        super(message);
    }
}
