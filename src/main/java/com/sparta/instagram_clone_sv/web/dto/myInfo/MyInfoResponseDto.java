package com.sparta.instagram_clone_sv.web.dto.myInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MyInfoResponseDto {

    private String myProfileImageUrl;
    private String nickname;
    private Long articleCount;
    private Long followerCount;
    private Long followCount;

    private List<MyInfoArticleResponseDto> myInfoArticleResponseDtoList;
    @Builder

    public MyInfoResponseDto(String myProfileImageUrl, String nickname, Long articleCount, Long followerCount, Long followCount, List<MyInfoArticleResponseDto> myInfoArticleResponseDtoList) {
        this.myProfileImageUrl = myProfileImageUrl;
        this.nickname = nickname;
        this.articleCount = articleCount;
        this.followerCount = followerCount;
        this.followCount = followCount;
        this.myInfoArticleResponseDtoList = myInfoArticleResponseDtoList;
    }
}
