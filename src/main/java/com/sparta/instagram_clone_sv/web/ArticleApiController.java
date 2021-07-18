package com.sparta.instagram_clone_sv.web;

import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.article.ArticleRepository;
import com.sparta.instagram_clone_sv.service.ArticleService;
import com.sparta.instagram_clone_sv.web.dto.Article.ArticleResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "ArticleApiController")
@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    @ApiOperation("게시물 조회")
    @GetMapping("/api/articles")
    public List<ArticleResponseDto> readArticles(){

        return articleService.findAll();
    }

}
