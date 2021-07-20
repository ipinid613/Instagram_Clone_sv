package com.sparta.instagram_clone_sv.web.controller;

import com.sparta.instagram_clone_sv.domain.liked.LikedRepository;
import com.sparta.instagram_clone_sv.security.UserDetailsImpl;
import com.sparta.instagram_clone_sv.service.LikedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "LikeApiController")
@RequiredArgsConstructor
@RestController
public class LikeApiController {

    private final LikedService likedService;

    @ApiOperation("라이크 누름")
    @PostMapping("/api/articles/{articleId}/like")
    public Boolean likeArticle(@PathVariable Long articleId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likedService.toggleLiked(articleId,userDetails.getUser());
    }
}
