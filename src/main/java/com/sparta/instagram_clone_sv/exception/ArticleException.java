package com.sparta.instagram_clone_sv.exception;

import org.springframework.http.HttpStatus;

public class ArticleException {

    private final String message;
    private final HttpStatus httpStatus;

    public ArticleException(String message, HttpStatus httpStatus){
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