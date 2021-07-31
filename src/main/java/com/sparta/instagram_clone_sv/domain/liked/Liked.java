package com.sparta.instagram_clone_sv.domain.liked;

import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.domain.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@DynamicUpdate
@Entity
public class Liked extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Article article;

    @Builder
    public Liked(User user, Article article) {
        this.user = user;
        this.article = article;
    }

    public void disconnectArticle(){
        this.article = null;
    }

}
