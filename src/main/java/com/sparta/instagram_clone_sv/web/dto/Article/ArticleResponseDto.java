package com.sparta.instagram_clone_sv.web.dto.Article;

import com.sparta.instagram_clone_sv.web.dto.comment.CommentResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ArticleResponseDto {

    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String imageUrl;
    private final String content;
    private final String author;
    private final String authorProfileImageUrl;
    private final Long articleId;

    private final List<CommentResponseDto> comments;

    @Builder
    public ArticleResponseDto(LocalDateTime createdAt, LocalDateTime modifiedAt, String imageUrl, String content, String author, String authorProfileImageUrl, Long articleId, List<CommentResponseDto> comments) {
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.imageUrl = imageUrl;
        this.content = content;
        this.author = author;
        this.authorProfileImageUrl = authorProfileImageUrl;
        this.articleId = articleId;
        this.comments = comments;
    }
}
