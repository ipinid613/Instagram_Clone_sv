package com.sparta.instagram_clone_sv.web.dto.ProfileUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ProfileUpdateRequestDto {
    @NotBlank(message = "닉네임은 빈칸으로만 이루어질 수 없습니다.")
    private String nickname;

    private String profileImageUrl;

    public ProfileUpdateRequestDto(String nickname, String profileImageUrl){
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
}
