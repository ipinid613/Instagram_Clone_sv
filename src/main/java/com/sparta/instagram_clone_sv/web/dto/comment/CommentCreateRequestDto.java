package com.sparta.instagram_clone_sv.web.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CommentCreateRequestDto {
    @NotBlank(message = "댓글 내용 입력은 필수입니다.")
    private String content;

    public CommentCreateRequestDto(String content) {
        this.content = content;
    }
}
