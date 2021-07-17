package com.sparta.instagram_clone_sv.model;

import com.sparta.instagram_clone_sv.dto.ArticleLikeRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ArticleLike extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    ///// ManyToMany 부분 수정 필요(불확실한 코드입니다) /////
    @ManyToMany
    @JoinColumn(nullable = false)
    private User username;
    ///// ManyToMany 부분 수정 필요(불확실한 코드입니다) /////
    @ManyToMany
    @JoinColumn(nullable = false)
    private Article articleId;

    ///// 수정 필요(불확실한 코드입니다) /////
    public ArticleLike(ArticleLikeRequestDto articleLikeRequestDto, Article article, User user){
        this.username = articleLikeRequestDto.getUsername();
        this.articleId = articleLikeRequestDto.getArticleId();
    }
}
