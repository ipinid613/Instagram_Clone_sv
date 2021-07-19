package com.sparta.instagram_clone_sv.domain.user;

import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.comment.Comment;
import com.sparta.instagram_clone_sv.domain.follow.Follow;
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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

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

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private final List<Liked> likedList = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private final List<Article> articleList = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private final List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "follower",cascade = CascadeType.ALL)
    private final List<Follow> followerList = new ArrayList<>(); //내가 팔로우를 하는 유저들의 리스트

    @OneToMany(mappedBy = "followee",cascade = CascadeType.ALL)
    private final List<Follow> followeeList = new ArrayList<>(); //나를 팔로우 하는 유저들의 리스트

    @Builder
    public User(String username, String email, String nickname, String password) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.password = password;

        //Here should Default profile imageUrl
        this.profileImageUrl = null;
    }
}