package com.sparta.instagram_clone_sv.service;


import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.article.ArticleRepository;
import com.sparta.instagram_clone_sv.domain.comment.Comment;
import com.sparta.instagram_clone_sv.domain.comment.CommentRepository;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentCreateRequestDto;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentResponseDto;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    @Transactional
    public CommentResponseDto updateComment(Long articleId, Long commentId, CommentUpdateRequestDto commentUpdateRequestDto, User user) {

        Optional<Comment> comment = commentRepository.findByIdAndEnabled(commentId,true);

        if (comment.isPresent()) {
            if(comment.get().getUser().getId().equals(user.getId())){
                comment.get().update(commentUpdateRequestDto);
            }else{
                throw new IllegalArgumentException("로그인 한 사용자와 댓글 작성자가 다릅니다.");
            }
        } else {
            throw new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId);
        }

        return new CommentResponseDto(comment.get());
    }

    public void deleteComment(Long articleId, Long commentId, User user) {
        Optional<Comment> comment = commentRepository.findByIdAndEnabled(commentId,true);

        if(comment.isPresent()){
            if(comment.get().getUser().getId().equals(user.getId())){
                comment.get().deActivate();
            }else{
                throw new IllegalArgumentException("로그인 한 사용자와 댓글 작성자가 다릅니다.");
            }
        }else{
            throw new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId);
        }
    }
}
