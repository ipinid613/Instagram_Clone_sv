package com.sparta.instagram_clone_sv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArticleRequestExceptionHandler {

    @ExceptionHandler(value = {ArticleRequestException.class})
    public ResponseEntity<Object> handleArticleRequestException(ArticleRequestException ex){
        // ArticleRequestException를 파라미터로 받아옴. ->ArticleContoller에서 지정한 메시지(IllegalArgumentException) 포함
        ArticleException articleException = new ArticleException(ex.getMessage(), HttpStatus.BAD_REQUEST);
        // ArticleRequestException의 Message와 HttpStatus.BAD_REQUEST(400에러)를 담아 ArticleException 생성
        return new ResponseEntity<>(articleException,HttpStatus.BAD_REQUEST);
        //그 결과를 클라이언트에 내려줌(Response 해줌)
    }
}