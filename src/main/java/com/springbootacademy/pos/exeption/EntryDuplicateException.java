package com.springbootacademy.pos.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class EntryDuplicateException extends RuntimeException  {
    public EntryDuplicateException(String message){
        super(message);
    }
}
