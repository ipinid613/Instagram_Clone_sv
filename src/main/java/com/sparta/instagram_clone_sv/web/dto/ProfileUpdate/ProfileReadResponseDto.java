package com.sparta.instagram_clone_sv.web.dto.ProfileUpdate;

import com.sparta.instagram_clone_sv.domain.user.User;
import lombok.Getter;

@Getter
public class ProfileReadResponseDto {
    private final String username;
    private final String email;
    private final String nickname;
    private final String profileImageUrl;

    public ProfileReadResponseDto(String email, String nickname, String username, String profileImageUrl){
        this.email = email;
        this.nickname = nickname;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
    }

    public ProfileReadResponseDto(User user){
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.profileImageUrl = user.getProfileImageUrl();
    }

}
