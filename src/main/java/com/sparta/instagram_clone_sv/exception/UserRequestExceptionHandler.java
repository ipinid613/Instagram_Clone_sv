package com.sparta.instagram_clone_sv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserRequestExceptionHandler {

    @ExceptionHandler(value = {UserRequestException.class})
    public ResponseEntity<Object> handleUserRequestException(UserRequestException ex){
        UserException userException = new UserException(ex.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(userException,HttpStatus.BAD_REQUEST);
    }
}
