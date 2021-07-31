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
//댓글 수정, 삭제 API는 있으나, 클라이언트 사이드에서 사용하지 않는 api이므로 실제 서버로 요청 안 옴.
// 따라서 @DynamicUpdate 사용 XXXX
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;

    @Builder
    public Comment(String content, Article article, User user) {
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

