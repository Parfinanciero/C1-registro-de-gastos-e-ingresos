package com.msvc.ai_scanner.controllers.handlersExceptions;


import com.msvc.ai_scanner.model.entities.ErrorTemplate;
import com.msvc.ai_scanner.services.exceptions.BillNotFoundException;
import com.msvc.ai_scanner.services.exceptions.CantCreateBillException;
import com.msvc.ai_scanner.services.exceptions.UserDontHaveRegistersException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class BillExceptionsController {

    @ExceptionHandler(BillNotFoundException.class)
    public ResponseEntity<ErrorTemplate> handleBillNotFoundException(Exception ex){
        return ResponseEntity.status(404).body(ErrorTemplate.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(CantCreateBillException.class)
    public ResponseEntity<ErrorTemplate> handleCantCreateBillException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorTemplate.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(UserDontHaveRegistersException.class)
    public ResponseEntity<ErrorTemplate> handleUserDontHaveRegisters(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorTemplate.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build());
    }


}
