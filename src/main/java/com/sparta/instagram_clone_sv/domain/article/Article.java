package com.sparta.instagram_clone_sv.domain.article;

import com.sparta.instagram_clone_sv.domain.Timestamped;
import com.sparta.instagram_clone_sv.domain.comment.Comment;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.domain.liked.Liked;
import com.sparta.instagram_clone_sv.web.dto.article.ArticleCreateRequestDto;
import com.sparta.instagram_clone_sv.web.dto.article.ArticleUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@DynamicUpdate
@Entity
public class Article extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "article",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false) // fetchtype eager로 해야지 디버그 가능해용 아닌가 아님말고...
    private final List<Liked> likedList = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final List<Comment> commentList = new ArrayList<>();

    @Builder
    public Article(String content, String imageUrl, User user) {
        this.content = content;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public Article(ArticleCreateRequestDto articleCreateRequestDto, User user){
        this.content = articleCreateRequestDto.getContent();
        this.imageUrl = articleCreateRequestDto.getImageUrl();
        this.user = user;
    }

    public void update(ArticleUpdateRequestDto articleUpdateRequestDto){
        this.content = articleUpdateRequestDto.getContent();
    }
}
