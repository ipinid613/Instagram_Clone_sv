package com.sparta.instagram_clone_sv.service;

import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.article.ArticleRepository;
import com.sparta.instagram_clone_sv.web.dto.Article.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

//    public List<ArticleResponseDto> findAll() {
//        List<Article> articles = articleRepository.findAllByOrderByCreatedAtDesc();
//
//        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();
//
//        for (Article article : articles) {
//
//            articleResponseDtoList.add(ArticleResponseDto.builder()
//            .articleId(article.getId())
//            .)
//        }
//    }
}
