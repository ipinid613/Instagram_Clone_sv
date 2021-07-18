package com.sparta.instagram_clone_sv.service;

import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.article.ArticleRepository;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.web.dto.article.ArticleCreateRequestDto;
import com.sparta.instagram_clone_sv.web.dto.article.ArticleResponseDto;
import com.sparta.instagram_clone_sv.web.dto.article.ArticleUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;


    public ArticleResponseDto createArticle(ArticleCreateRequestDto articleCreateRequestDto, User user) {
        Article article = new Article(articleCreateRequestDto,user);
        articleRepository.save(article);
        return new ArticleResponseDto(article);
    }


    public List<ArticleResponseDto> readArticles() {
        List<Article> articles = articleRepository.findAllByEnabledOrderByCreatedAtDesc(true);

        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();

        for (Article article : articles) {
            articleResponseDtoList.add(new ArticleResponseDto(article));
        }

        return articleResponseDtoList;
    }


    public ArticleResponseDto readArticle(Long articleId) {
        Optional<Article> article = articleRepository.findByIdAndEnabled(articleId,true);

        if(article.isPresent()){
            return new ArticleResponseDto(article.get());
        }else{

        }
    }

    @Transactional
    public ArticleResponseDto updateArticle(Long articleId, ArticleUpdateRequestDto articleUpdateRequestDto, User user) {
        Optional<Article> article = articleRepository.findByIdAndEnabled(articleId,true);

        if(article.isPresent()){
            if(article.get().getUser().getId().equals(user.getId())){
                article.get().update(articleUpdateRequestDto);
            }else{
             throw new IllegalArgumentException("로그인 한 사용자와 게시물 작성자가 다릅니다.");
            }
        }else{
            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId);
        }

        return new ArticleResponseDto(article.get());
    }

    @Transactional
    public void deleteArticle(Long articleId, User user) {
        Optional<Article> article = articleRepository.findByIdAndEnabled(articleId,true);

        if(article.isPresent()){
            if(article.get().getUser().getId().equals(user.getId())){
                article.get().deActivate();
            }else{
                throw new IllegalArgumentException("로그인 한 사용자와 게시물 작성자가 다릅니다.");
            }
        }else{
            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId);
        }
    }


}
