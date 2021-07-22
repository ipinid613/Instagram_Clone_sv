package com.sparta.instagram_clone_sv.web.dto.comment;

import com.sparta.instagram_clone_sv.domain.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String username;
    private final String commentAuthor;
    private final String commentAuthorProfileImageUrl;
    private final Long articleId;
    private final Long commentId;
    private final String content;

//    @Builder
//    public CommentResponseDto(LocalDateTime createdAt, LocalDateTime modifiedAt, String commentAuthor, String commentAuthorProfileImageUrl) {
//        this.createdAt = createdAt;
//        this.modifiedAt = modifiedAt;
//        this.commentAuthor = commentAuthor;
//        this.commentAuthorProfileImageUrl = commentAuthorProfileImageUrl;
//    }

    public CommentResponseDto(Comment comment){
        this.articleId = comment.getArticle().getId();
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.username = comment.getUser().getUsername();
        this.commentAuthor = comment.getUser().getNickname();
        this.commentAuthorProfileImageUrl = comment.getUser().getProfileImageUrl();
    }

    public CommentResponseDto(Long articleId, Long commentId, String content,
                              LocalDateTime createdAt, LocalDateTime modifiedAt,
                              String username, String commentAuthor, String commentAuthorProfileImageUrl){
        this.articleId = articleId;
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.username = username;
        this.commentAuthor = commentAuthor;
        this.commentAuthorProfileImageUrl = commentAuthorProfileImageUrl;
    }
}
