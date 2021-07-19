package com.sparta.instagram_clone_sv.exception;

public class ArticleRequestException extends IllegalArgumentException{

    public ArticleRequestException(String message){
        super(message); // super를 사용했기 때문에 이는 IllegalArgumentException의 message에 해당함.
        // 즉, IllegalArgumentException의 내용(우리가 메시지 지정)을 출력한다고 보면 됨.
    }
}