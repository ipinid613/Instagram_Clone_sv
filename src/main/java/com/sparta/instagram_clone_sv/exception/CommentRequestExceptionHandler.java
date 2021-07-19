package com.sparta.instagram_clone_sv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentRequestExceptionHandler {

    @ExceptionHandler(value = {CommentRequestException.class})
    public ResponseEntity<Object> handleCommentRequestException(CommentRequestException ex){
        CommentException commentException = new CommentException(ex.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(commentException,HttpStatus.BAD_REQUEST);
    }
}
