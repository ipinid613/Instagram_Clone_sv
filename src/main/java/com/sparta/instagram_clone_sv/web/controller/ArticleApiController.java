package com.sparta.instagram_clone_sv.web.controller;

import com.sparta.instagram_clone_sv.domain.article.ArticleRepository;
import com.sparta.instagram_clone_sv.security.UserDetailsImpl;
import com.sparta.instagram_clone_sv.service.ArticleService;
import com.sparta.instagram_clone_sv.web.dto.article.ArticleCreateRequestDto;
import com.sparta.instagram_clone_sv.web.dto.article.ArticleResponseDto;
import com.sparta.instagram_clone_sv.web.dto.article.ArticleUpdateRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "ArticleApiController")
@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    @ApiOperation("게시물 작성")
    @PostMapping("/api/articles")
    public ArticleResponseDto createArticle(@Valid @RequestBody ArticleCreateRequestDto articleCreateRequestDto, Errors errors, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (errors.hasErrors()) {
            articleService.validateHandling(errors);
        }
        return articleService.createArticle(articleCreateRequestDto,userDetails.getUser());
    }

    @ApiOperation("게시물 조회")
    @GetMapping("/api/articles")
    public List<ArticleResponseDto> readArticles(@AuthenticationPrincipal UserDetailsImpl userDetails){

        return articleService.readArticles(userDetails);
    }

    @ApiOperation("특정 게시물 조회")
    @GetMapping("/api/articles/{articleId}")
    public ArticleResponseDto readArticle(@PathVariable Long articleId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return articleService.readArticle(articleId, userDetails);
    }

    @ApiOperation("특정 게시물 수정")
    @PutMapping("/api/articles/{articleId}")
    public ArticleResponseDto updateArticle(@PathVariable Long articleId, @RequestBody ArticleUpdateRequestDto articleUpdateRequestDto , @AuthenticationPrincipal UserDetailsImpl userDetails){
        return articleService.updateArticle(articleId, articleUpdateRequestDto,userDetails);
    }

    @ApiOperation("특정 게시물 삭제")
    @DeleteMapping("/api/articles/{articleId}")
    public void deleteArticle(@PathVariable Long articleId , @AuthenticationPrincipal UserDetailsImpl userDetails){
        articleService.deleteArticle(articleId,userDetails);
    }
}
