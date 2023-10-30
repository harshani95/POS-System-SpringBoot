package com.springbootacademy.pos.adviser;

import com.springbootacademy.pos.exeption.NotFoundException;
import com.springbootacademy.pos.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFountException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
              new StandardResponse(404,"ERROR", e.getMessage()), HttpStatus.NOT_FOUND
        );
    }
}
