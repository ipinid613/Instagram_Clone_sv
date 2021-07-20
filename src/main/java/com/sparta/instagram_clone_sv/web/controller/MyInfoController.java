package com.sparta.instagram_clone_sv.web.controller;

import com.sparta.instagram_clone_sv.security.UserDetailsImpl;
import com.sparta.instagram_clone_sv.service.MyInfoService;
import com.sparta.instagram_clone_sv.web.dto.myInfo.MyInfoResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(value="MyInfoController")
@RequiredArgsConstructor
@RestController
public class MyInfoController {

    private final MyInfoService myInfoService;

    @ApiOperation("현재 로그인 한 유저정보")
    @GetMapping("/api/myinfo")
    public MyInfoResponseDto readMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return myInfoService.readMyInfo(userDetails.getUser());
    }

}
