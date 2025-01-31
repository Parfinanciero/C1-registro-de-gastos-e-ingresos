package com.msvc.ai_scanner.services.exceptions;

public class BillNotFoundException extends RuntimeException{
    public BillNotFoundException(String message){
        super(message);
    }
}
