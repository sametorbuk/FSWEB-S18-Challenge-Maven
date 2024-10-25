package com.workintech.fswebs18challengemaven.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CardException.class)
    public ResponseEntity<CardErrorResponse> notFoundWithThisData(CardException exception){
        CardErrorResponse response = new CardErrorResponse(exception.getStatus(), System.currentTimeMillis(), exception.getMessage());
        return new ResponseEntity(response , exception.getStatus());
    }


     @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CardErrorResponse> runtimeExceptionHandler(RuntimeException exception){
        CardErrorResponse response = new CardErrorResponse(HttpStatus.BAD_REQUEST , System.currentTimeMillis(), exception.getMessage());
        return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
     }


}
