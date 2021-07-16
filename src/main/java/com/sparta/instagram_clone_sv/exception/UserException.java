package com.sparta.instagram_clone_sv.exception;

import org.springframework.http.HttpStatus;

public class UserException {
    private final String message;
    private final HttpStatus httpStatus;

    public UserException(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage(){
        return this.message;
    }

    public HttpStatus httpStatus(){
        return this.httpStatus;
    }
}
