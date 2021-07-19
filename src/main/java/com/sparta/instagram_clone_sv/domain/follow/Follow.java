package com.sparta.instagram_clone_sv.domain.follow;

import com.sparta.instagram_clone_sv.domain.Timestamped;
import com.sparta.instagram_clone_sv.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@DynamicUpdate
@Entity
public class Follow extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn
    private User follower; // 팔로우를 하는 사람

    @Column
    @ManyToOne
    private User followee; // 팔로우를 받는 사람
}
