package com.sparta.instagram_clone_sv.service;

import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.article.ArticleRepository;
import com.sparta.instagram_clone_sv.domain.liked.Liked;
import com.sparta.instagram_clone_sv.domain.liked.LikedRepository;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.domain.user.UserRepository;
import com.sparta.instagram_clone_sv.exception.ArticleRequestException;
import com.sparta.instagram_clone_sv.web.dto.article.ArticleCreateRequestDto;
import com.sparta.instagram_clone_sv.web.dto.article.ArticleResponseDto;
import com.sparta.instagram_clone_sv.web.dto.article.ArticleUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final LikedRepository likedRepository;
    private final UserRepository userRepository;


    public ArticleResponseDto createArticle(ArticleCreateRequestDto articleCreateRequestDto, User user) {
        Article article = new Article(articleCreateRequestDto, user);
        articleRepository.save(article);
        return new ArticleResponseDto(article, false);
    }


    public List<ArticleResponseDto> readArticles(User user) {
        List<Article> articles = articleRepository.findAllByOrderByCreatedAtDesc();

        if (user == null) { // 로그인 하지 않은 사용자
            List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();

            for (Article article : articles) {
                articleResponseDtoList.add(new ArticleResponseDto(article, false));
            }

            return articleResponseDtoList;
        } else {
            Optional<User> contextUser = userRepository.findById(user.getId());
            List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();

            if (!contextUser.isPresent()) {
                throw new IllegalArgumentException("뭔가...문제가 있음...");
            }

            boolean isLike;
            for (Article article : articles) {
                isLike = false;
                for (Liked liked : contextUser.get().getLikedList()) {
                    if (liked.getArticle().getId().equals(article.getId())) {
                        isLike = true;
                        break;
                    }
                }
                articleResponseDtoList.add(new ArticleResponseDto(article, isLike));
            }

            return articleResponseDtoList;
        }
    }


    public ArticleResponseDto readArticle(Long articleId, User user) {
        Optional<Article> article = articleRepository.findById(articleId);

        if (user == null) {
            if (article.isPresent()) {
                return new ArticleResponseDto(article.get(), false);
            } else {
                throw new ArticleRequestException("해당 게시글이 없습니다. id=" + articleId);
            }
        } else {
            Optional<User> contextUser = userRepository.findById(user.getId());

            if (!contextUser.isPresent()) {
                throw new IllegalArgumentException("뭔가...문제가 있음...");
            }

            if (article.isPresent()) {

                boolean isLike = false;

                for (Liked liked : contextUser.get().getLikedList()) {
                    if (liked.getArticle().getId().equals(article.get().getId())) {
                        isLike = true;
                        break;
                    }
                }
                return new ArticleResponseDto(article.get(), isLike);

            } else {
                throw new ArticleRequestException("해당 게시글이 없습니다. id=" + articleId);
            }
        }


    }

    @Transactional
    public ArticleResponseDto updateArticle(Long articleId, ArticleUpdateRequestDto articleUpdateRequestDto, User user) {
        Optional<Article> article = articleRepository.findById(articleId);
        Optional<User> contextUser = userRepository.findById(user.getId());

        if (article.isPresent()) {
            if (article.get().getUser().getId().equals(user.getId())) {
                article.get().update(articleUpdateRequestDto);
                boolean isLiked = false;
                for(Liked liked:contextUser.get().getLikedList()){
                    if(liked.getArticle().getId().equals(article.get().getId())){
                        isLiked = true;
                        break;
                    }
                }
                return new ArticleResponseDto(article.get(), isLiked);
            } else {
                throw new ArticleRequestException("로그인 한 사용자와 게시물 작성자가 다릅니다.");
            }
        } else {
            throw new ArticleRequestException("해당 게시글이 없습니다. id=" + articleId);
        }


    }

    @Transactional
    public void deleteArticle(Long articleId, User user) {
        Optional<Article> article = articleRepository.findById(articleId);

        if (article.isPresent()) {
            if (article.get().getUser().getId().equals(user.getId())) {
                articleRepository.delete(article.get());
            } else {
                throw new ArticleRequestException("로그인 한 사용자와 게시물 작성자가 다릅니다.");
            }
        } else {
            throw new ArticleRequestException("해당 게시글이 없습니다. id=" + articleId);
        }
    }

    public void validateHandling(Errors errors) {
        String errorMessage;
        for (FieldError error : errors.getFieldErrors()) {
            errorMessage = error.getDefaultMessage(); // getField() 로 하면 defaultmessage 출력 불가함. getDefaultMesasge로 해야해요!
            throw new ArticleRequestException(errorMessage);
        }
    }

}
