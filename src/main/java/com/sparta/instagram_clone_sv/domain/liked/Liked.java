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

    @Column(nullable = false)
    private boolean enabled;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Article article;

    @Builder
    public Liked(User user, Article article) {
        this.enabled = true;
        this.user = user;
        this.article = article;
    }

    public void deActivate(){
        if(this.enabled){
            this.enabled = false;
        }
    }

}
