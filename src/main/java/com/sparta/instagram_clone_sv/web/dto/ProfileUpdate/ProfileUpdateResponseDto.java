package com.sparta.instagram_clone_sv.web.dto.ProfileUpdate;

import com.sparta.instagram_clone_sv.domain.user.User;
import lombok.Getter;

@Getter
public class ProfileUpdateResponseDto {
    private final String nickname;
    private final String profileImageUrl;

    public ProfileUpdateResponseDto(String nickname, String profileImageUrl){
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public ProfileUpdateResponseDto(User user){
        this.nickname = user.getNickname();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
