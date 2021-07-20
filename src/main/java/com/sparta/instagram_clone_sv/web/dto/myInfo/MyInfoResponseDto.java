package com.sparta.instagram_clone_sv.web.dto.myInfo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyInfoResponseDto {

    private final Long userId;
    private final String username;
    private final LocalDateTime createdAt;

    @Builder
    public MyInfoResponseDto(Long userId, String username, LocalDateTime createdAt) {
        this.userId = userId;
        this.username = username;
        this.createdAt = createdAt;
    }
}
