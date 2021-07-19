package com.sparta.instagram_clone_sv.web.controller;

import com.sparta.instagram_clone_sv.domain.comment.CommentRepository;
import com.sparta.instagram_clone_sv.security.UserDetailsImpl;
import com.sparta.instagram_clone_sv.service.CommentService;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentCreateRequestDto;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentResponseDto;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentUpdateRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(value = "CommentApiController")
@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @ApiOperation("댓글 작성")
    @PostMapping("/api/articles/{articleId}/comments")
    public CommentResponseDto createComment(@PathVariable Long articleId, @RequestBody CommentCreateRequestDto commentCreateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(articleId, commentCreateRequestDto, userDetails.getUser());
    }

    @ApiOperation("댓글 수정, 여기 articleId 아무거나 보내셔도 되어요.. 사실 commentId만 필요한데, 형식을 맞추기 위함이라..")
    @PutMapping("/api/articles/{articleId}/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long articleId, @PathVariable Long commentId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(articleId, commentId, commentUpdateRequestDto, userDetails.getUser());
    }

    @ApiOperation("댓글 삭제, 이하 수정과 동일합니다.")
    @DeleteMapping("/api/articles/{articleId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long articleId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(articleId, commentId, userDetails.getUser());
    }
}