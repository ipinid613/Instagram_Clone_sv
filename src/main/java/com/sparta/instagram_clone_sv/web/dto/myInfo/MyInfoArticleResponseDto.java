package com.sparta.instagram_clone_sv.web.dto.myInfo;

import com.sparta.instagram_clone_sv.domain.article.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyInfoArticleResponseDto {

    private Long articleId;
    private String articleImageUrl;

    @Builder
    public MyInfoArticleResponseDto(Long articleId, String articleImageUrl) {
        this.articleId = articleId;
        this.articleImageUrl = articleImageUrl;
    }

    public static MyInfoArticleResponseDto of(Article entity){
        return new MyInfoArticleResponseDto(entity.getId(),entity.getImageUrl());
    }
}
