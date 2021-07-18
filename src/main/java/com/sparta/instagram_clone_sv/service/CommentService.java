package com.sparta.instagram_clone_sv.service;


import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.article.ArticleRepository;
import com.sparta.instagram_clone_sv.domain.comment.Comment;
import com.sparta.instagram_clone_sv.domain.comment.CommentRepository;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentCreateRequestDto;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentResponseDto createComment(Long articleId, CommentCreateRequestDto commentCreateRequestDto, User user) {

        Optional<Article> article = articleRepository.findByIdAndEnabled(articleId, true);

        if (article.isPresent()) {

            Comment comment = new Comment(article.get(), commentCreateRequestDto, user);
            commentRepository.save(comment);
            return new CommentResponseDto(comment);

        } else {

            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId);

        }
    }


}
