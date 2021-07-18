package com.sparta.instagram_clone_sv.web.controller;

import com.sparta.instagram_clone_sv.domain.comment.CommentRepository;
import com.sparta.instagram_clone_sv.security.UserDetailsImpl;
import com.sparta.instagram_clone_sv.service.CommentService;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentCreateRequestDto;
import com.sparta.instagram_clone_sv.web.dto.comment.CommentResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
