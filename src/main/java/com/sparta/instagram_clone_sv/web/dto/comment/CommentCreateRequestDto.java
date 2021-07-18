package com.sparta.instagram_clone_sv.web.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequestDto {
    private String content;

    public CommentCreateRequestDto(String content) {
        this.content = content;
    }
}
