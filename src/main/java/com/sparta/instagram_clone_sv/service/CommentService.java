package com.sparta.instagram_clone_sv.service;


import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.article.ArticleRepository;
import com.sparta.instagram_clone_sv.domain.comment.Comment;
import com.sparta.instagram_clone_sv.domain.comment.CommentRepository;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.exception.ArticleRequestException;
import com.sparta.instagram_clone_sv.exception.CommentRequestException;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentCreateRequestDto;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentResponseDto;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentUpdateRequestDto;
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
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public List<CommentResponseDto> getAllComments(Long articleId) {
        List<Comment> comments = commentRepository.findByArticle_Id(articleId);

        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

        for (Comment comment : comments) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(
                    comment.getArticle().getId(),
                    comment.getId(),
                    comment.getContent(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt(),
                    comment.getUser().getUsername(),
                    comment.getUser().getNickname(),
                    comment.getUser().getProfileImageUrl()
            );
            commentResponseDtos.add(commentResponseDto);
        }
        return commentResponseDtos;
    }

    public void createComment(Long articleId, CommentCreateRequestDto commentCreateRequestDto, User user) {

        Optional<Article> article = articleRepository.findById(articleId);

        if (article.isPresent()) {
            Comment comment = new Comment(article.get(), commentCreateRequestDto, user);
            commentRepository.save(comment);
        } else {
            throw new CommentRequestException("해당 게시글이 없습니다. id=" + articleId);

        }
    }

    @Transactional
    public CommentResponseDto updateComment(Long articleId, Long commentId, CommentUpdateRequestDto commentUpdateRequestDto, User user) {

        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isPresent()) {
            if (comment.get().getUser().getId().equals(user.getId())) {
                comment.get().update(commentUpdateRequestDto);
            } else {
                throw new CommentRequestException("로그인 한 사용자와 댓글 작성자가 다릅니다.");
            }
        } else {
            throw new CommentRequestException("해당 댓글이 없습니다. id=" + commentId);
        }

        return new CommentResponseDto(comment.get());
    }

    public void deleteComment(Long articleId, Long commentId, User user) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isPresent()) {
            if (comment.get().getUser().getId().equals(user.getId())) {
                commentRepository.delete(comment.get());
            } else {
                throw new CommentRequestException("로그인 한 사용자와 댓글 작성자가 다릅니다.");
            }
        } else {
            throw new CommentRequestException("해당 댓글이 없습니다. id=" + commentId);
        }
    }

    public void validateHandling(Errors errors) {
        String errorMessage;
        for (FieldError error : errors.getFieldErrors()) {
            errorMessage = error.getDefaultMessage(); // getField() 로 하면 defaultmessage 출력 불가함. getDefaultMesasge로 해야해요!
            throw new CommentRequestException(errorMessage);
        }
    }
}
