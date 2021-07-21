package com.sparta.instagram_clone_sv.web.controller;


import com.sparta.instagram_clone_sv.domain.user.UserRepository;
import com.sparta.instagram_clone_sv.security.UserDetailsImpl;
import com.sparta.instagram_clone_sv.service.UserService;
import com.sparta.instagram_clone_sv.web.dto.user.UserSimpleResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Api(value = "RecommendedFriendsApiController")
@RestController
public class RecommendedFriendsApiController {

    private final UserRepository userRepository;
    private final UserService userService;

    @ApiOperation("랜덤 추천 받기")
    @GetMapping("/api/recommend")
    public List<UserSimpleResponseDto> getRecommendedFriends(@AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            return new ArrayList<>(); // return empty list
        }else{
            return userService.getRecommendedFriends(userDetails.getUser());
        }
    }
}
