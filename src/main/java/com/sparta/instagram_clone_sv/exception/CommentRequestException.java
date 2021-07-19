package com.sparta.instagram_clone_sv.exception;


public class CommentRequestException extends IllegalArgumentException{

    public CommentRequestException(String message){
        super(message);
    }
}
