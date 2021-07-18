package com.sparta.instagram_clone_sv.web.dto.article;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleUpdateRequestDto {
    private String content;

    public ArticleUpdateRequestDto(String content) {
        this.content = content;
    }
}
