package com.sparta.instagram_clone_sv.exception;

public class UserRequestException extends IllegalArgumentException{

    public UserRequestException(String message){
        super(message);
    }
}