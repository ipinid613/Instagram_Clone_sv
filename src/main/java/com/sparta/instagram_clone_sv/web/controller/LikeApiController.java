package com.sparta.instagram_clone_sv.web.controller;

import com.sparta.instagram_clone_sv.domain.liked.LikedRepository;
import com.sparta.instagram_clone_sv.service.LikedService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "LikeApiController")
@RequiredArgsConstructor
@RestController
public class LikeApiController {

    private final LikedRepository likedRepository;
    private final LikedService likedService;
}
