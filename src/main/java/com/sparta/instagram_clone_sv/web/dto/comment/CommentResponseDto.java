package com.sparta.instagram_clone_sv.web.dto.comment;

import com.sparta.instagram_clone_sv.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String commentAuthor;
    private final String commentAuthorProfileImageUrl;
    private final Long articleId;

//    @Builder
//    public CommentResponseDto(LocalDateTime createdAt, LocalDateTime modifiedAt, String commentAuthor, String commentAuthorProfileImageUrl) {
//        this.createdAt = createdAt;
//        this.modifiedAt = modifiedAt;
//        this.commentAuthor = commentAuthor;
//        this.commentAuthorProfileImageUrl = commentAuthorProfileImageUrl;
//    }

    public CommentResponseDto(Comment comment){
        this.articleId = comment.getArticle().getId();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.commentAuthor = comment.getUser().getNickname();
        this.commentAuthorProfileImageUrl = comment.getUser().getProfileImageUrl();
    }
}
