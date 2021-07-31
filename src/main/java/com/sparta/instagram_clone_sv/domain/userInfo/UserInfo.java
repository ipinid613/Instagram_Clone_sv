package com.sparta.instagram_clone_sv.domain.userInfo;

import com.sparta.instagram_clone_sv.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@DynamicUpdate
@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "userInfo", fetch = FetchType.EAGER)
    private User user;

    @Column(nullable = false)
    private Long articleCount;

    @Column(nullable = false)
    private Long followerCount; // who follow me?

    @Column(nullable = false)
    private Long followCount; // me follow who?

    public UserInfo(Long articleCount, Long followerCount, Long followCount) {
        this.articleCount = articleCount;
        this.followerCount = followerCount;
        this.followCount = followCount;
    }

    public static UserInfoBuilder builder() {
        return new UserInfoBuilder();
    }

    public static class UserInfoBuilder {
        private Long articleCount = 0L;
        private Long followerCount = 0L;
        private Long followCount = 0L;

        UserInfoBuilder() {
        }

        public UserInfoBuilder articleCount(Long articleCount) {
            this.articleCount = articleCount;
            return this;
        }

        public UserInfoBuilder followerCount(Long followerCount) {
            this.followerCount = followerCount;
            return this;
        }

        public UserInfoBuilder followCount(Long followCount) {
            this.followCount = followCount;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(articleCount, followerCount, followCount);
        }

        public String toString() {
            return "UserInfo.UserInfoBuilder(articleCount=" + this.articleCount + ", followerCount=" + this.followerCount + ", followCount=" + this.followCount + ")";
        }
    }


    public void articleCountPlus(){
        this.articleCount += 1L ;
    }

    public void followerCountPlus(){
        this.followerCount += 1L;
    }

    public void followCountPlus(){
        this.followCount += 1L;
    }

    public void articleCountMinus(){
        this.articleCount -= 1L;
    }

    public void followerCountMinus(){
        this.followerCount -= 1L;
    }

    public void followCountMinus(){
        this.followCount -= 1L;
    }
}
