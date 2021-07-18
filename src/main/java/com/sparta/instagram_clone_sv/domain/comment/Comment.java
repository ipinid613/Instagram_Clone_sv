package com.sparta.instagram_clone_sv.domain.comment;

import com.sparta.instagram_clone_sv.domain.Timestamped;
import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentCreateRequestDto;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Builder
    public Comment(Boolean enabled, String content, Article article, User user) {
        this.content = content;
        this.article = article;
        this.user = user;
    }

    public Comment(Article article, CommentCreateRequestDto commentCreateRequestDto, User user){
        this.content = commentCreateRequestDto.getContent();
        this.article = article;
        this.user = user;
    }

    public void update(CommentUpdateRequestDto commentUpdateRequestDto){
        this.content = commentUpdateRequestDto.getContent();
    }
}

