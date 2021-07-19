package com.sparta.instagram_clone_sv.web.dto.article;

import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.comment.Comment;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private final Long likeCount;

    private final List<CommentResponseDto> comments = new ArrayList<>();

//    @Builder
//    public ArticleResponseDto(LocalDateTime createdAt, LocalDateTime modifiedAt, String imageUrl, String content, String author, String authorProfileImageUrl, Long articleId, List<CommentResponseDto> comments) {
//        this.createdAt = createdAt;
//        this.modifiedAt = modifiedAt;
//        this.imageUrl = imageUrl;
//        this.content = content;
//        this.author = author;
//        this.authorProfileImageUrl = authorProfileImageUrl;
//        this.articleId = articleId;
//        this.comments = comments;
//    }

    public ArticleResponseDto(Article article){
        this.createdAt = article.getCreatedAt();
        this.modifiedAt = article.getModifiedAt();
        this.imageUrl = article.getImageUrl();
        this.content = article.getContent();
        this.author = article.getUser().getNickname();
        this.authorProfileImageUrl = article.getUser().getProfileImageUrl();
        this.articleId = article.getId();
        this.likeCount = (long)article.getLikedList().size();

        for(Comment comment:article.getCommentList()){
            this.comments.add(new CommentResponseDto(comment));
        }
    }
}
