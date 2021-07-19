package com.sparta.instagram_clone_sv.web.dto.article;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ArticleCreateRequestDto {
    private String content;

    @NotBlank(message = "사진 업로드는 필수입니다.")
    private String imageUrl;

    public ArticleCreateRequestDto(String content, String imageUrl) {
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
