package com.msvc.ai_scanner.controllers.handlersExceptions;


import com.msvc.ai_scanner.model.entities.ErrorTemplate;
import com.msvc.ai_scanner.services.exceptions.BillNotFoundException;
import com.msvc.ai_scanner.services.exceptions.CantCreateBillException;
import com.msvc.ai_scanner.services.exceptions.UserDontHaveRegistersException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

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

    @ExceptionHandler({HttpClientErrorException.BadRequest.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorTemplate> handleBadRequest(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorTemplate.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorTemplate> handleDataException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorTemplate.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .reason(ex.getMessage())
                .message("we are having a problem with the data base, try the request leater :)")
                .build());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorTemplate> handleMethodNotSupported(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorTemplate.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .reason(ex.getMessage())
                .message("you´re using a request method not supported, please verify the route that you are using ")
                .build());
    }

}
