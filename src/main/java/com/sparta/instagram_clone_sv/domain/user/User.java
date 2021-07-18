package com.sparta.instagram_clone_sv.domain.user;

import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.comment.Comment;
import com.sparta.instagram_clone_sv.domain.liked.Liked;
import com.sparta.instagram_clone_sv.domain.Timestamped;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@DynamicUpdate
@Entity
public class User extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String profileImageUrl;

    @OneToMany(mappedBy = "user")
    private final List<Liked> likedList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Article> articleList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Comment> commentList = new ArrayList<>();

    @Builder
    public User(boolean enabled, String username, String email, String nickname, String password) {
        this.enabled = true;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.password = password;

        //Here should Default profile imageUrl
        this.profileImageUrl = null;
    }

    public void deActivate(){
        if(this.enabled){

            this.enabled = false;

            for(Article article:articleList){
                article.deActivate();
            }

            for(Liked liked:likedList){
                liked.deActivate();
            }

            for(Comment comment:commentList){
                comment.deActivate();
            }
        }
    }
}