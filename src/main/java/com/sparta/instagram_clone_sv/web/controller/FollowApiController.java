package com.sparta.instagram_clone_sv.web.controller;

import com.sparta.instagram_clone_sv.exception.UserRequestException;
import com.sparta.instagram_clone_sv.security.UserDetailsImpl;
import com.sparta.instagram_clone_sv.service.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "FollowApiController")
@RequiredArgsConstructor
@RestController
public class FollowApiController {

    private final FollowService followService;

    @ApiOperation("팔로우 누름")
    @PostMapping("/api/follow/{userId}")
    public Boolean followUser(@PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails.getUser().getId().equals(userId)){
            throw new UserRequestException("본인을 팔로우할 수 없습니다.");
        }
        return followService.toggleUser(userId, userDetails.getUser());
    }
}
