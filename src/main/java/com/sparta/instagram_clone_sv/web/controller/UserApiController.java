package com.sparta.instagram_clone_sv.web.controller;

import com.sparta.instagram_clone_sv.security.UserDetailsImpl;
import com.sparta.instagram_clone_sv.web.dto.ProfileUpdate.ProfileReadResponseDto;
import com.sparta.instagram_clone_sv.web.dto.ProfileUpdate.ProfileUpdateRequestDto;
import com.sparta.instagram_clone_sv.web.dto.ProfileUpdate.ProfileUpdateResponseDto;
import com.sparta.instagram_clone_sv.web.dto.signUp.SignupRequestDto;
import com.sparta.instagram_clone_sv.exception.UserRequestException;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.domain.user.UserRepository;
import com.sparta.instagram_clone_sv.security.JwtTokenProvider;
import com.sparta.instagram_clone_sv.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Api(value = "UserController")
@RestController
public class UserApiController {

    private final UserService userService;


    // 회원 가입 요청 처리
    @ApiOperation("회원가입")
    @PostMapping("/api/signup")
    public void registerUser(@Valid @RequestBody SignupRequestDto signupRequestDto, Errors errors) {
        if (errors.hasErrors()) {
            userService.validateHandling(errors);
        }
        userService.registerUser(signupRequestDto);
    }


    // 로그인
    @PostMapping("/api/login")
    public List<Map<String,String>> login(@RequestBody Map<String, String> user) {
        return userService.login(user);
    }

    //유저 프로필 조회(닉네임, 프로필사진)//
    @ApiOperation("유저의 프로필 조회 (메인페이지에서 오른쪽에 띄우는 것에 대한 api는 아님)")
    @GetMapping("/api/user")
    public ProfileReadResponseDto readProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.readProfile(userDetails);
    }

    //유저 프로필 수정(닉네임, 프로필사진)//
    @PutMapping("/api/user")
    public ProfileUpdateResponseDto updateProfile(@Valid @RequestBody ProfileUpdateRequestDto profileUpdateRequestDto, Errors errors, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (errors.hasErrors()) {
            userService.validateHandling(errors);
        }
        return userService.updateProfile(profileUpdateRequestDto, userDetails);
    }

}