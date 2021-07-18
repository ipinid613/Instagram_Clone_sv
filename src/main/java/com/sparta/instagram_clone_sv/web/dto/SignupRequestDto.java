package com.sparta.instagram_clone_sv.web.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class SignupRequestDto {
    private Long id;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "실명은 필수 입력 값입니다.")
    private String realname;

    @NotBlank(message = "유저명은 필수 입력 값입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

////////// 비밀번호 재확인(클론코딩 상 요구사항 아니므로 삭제 //////////
//    @NotBlank(message = "비밀번호는 확인이 필요합니다.")
//    private String repassword;
}