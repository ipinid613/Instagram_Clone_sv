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

//FetchType.EAGER | LAZY = 즉시로딩, 지연로딩에 관한 부분.
//JPA 기본 페치 전략 : xToOne -> EAGER, xToMany -> LAZY

@Getter // getId, getContent 등을 자동으로 핸들링
@NoArgsConstructor // 기본생성자 핸들링
@DynamicUpdate // update 요청 시, 전체 컬럼에 대한 쿼리를 생성하는 것 대신 변경 요청하는 부분에 대한 쿼리만 생성함
@Entity
public class Article extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "article",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private final List<Liked> likedList = new ArrayList<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<Comment> commentList = new ArrayList<>();


    public Article(ArticleCreateRequestDto articleCreateRequestDto, User user){
        this.content = articleCreateRequestDto.getContent();
        this.imageUrl = articleCreateRequestDto.getImageUrl();
        this.user = user;
    }

    public void update(ArticleUpdateRequestDto articleUpdateRequestDto){
        this.content = articleUpdateRequestDto.getContent();
    }

//    public Boolean removeLiked(Liked liked){
//        if(liked!=null){
//            if(liked.getArticle()!=null){
//                if(liked.getArticle().equals(this)){
//                    this.likedList.remove(liked);
//                    liked.disconnectArticle();
//                    return true;
//                }
//            }
//        }
//        return false;
//
////        if(liked.getArticle().equals(this)){
////            this.likedList.remove(liked);
////            liked.
////        }
//
//    }
}
