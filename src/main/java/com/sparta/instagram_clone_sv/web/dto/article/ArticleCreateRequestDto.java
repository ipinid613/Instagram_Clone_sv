package com.sparta.instagram_clone_sv.web.dto.article;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleCreateRequestDto {
    private String content;
    private String imageUrl;

    public ArticleCreateRequestDto(String content, String imageUrl) {
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
