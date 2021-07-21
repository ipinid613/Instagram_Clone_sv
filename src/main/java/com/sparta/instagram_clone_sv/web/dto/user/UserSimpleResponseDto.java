package com.sparta.instagram_clone_sv.web.dto.user;

import com.sparta.instagram_clone_sv.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSimpleResponseDto {

    private Long userId;
    private String profileImageUrl;
    private String nickname;

    @Builder
    public UserSimpleResponseDto(Long userId, String profileImageUrl, String nickname) {
        this.userId = userId;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
    }

    public static UserSimpleResponseDto of(User entity){
        return new UserSimpleResponseDto(entity.getId(), entity.getProfileImageUrl(), entity.getNickname());
    }
}
