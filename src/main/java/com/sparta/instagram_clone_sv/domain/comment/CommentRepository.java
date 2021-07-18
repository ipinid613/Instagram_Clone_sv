package com.sparta.instagram_clone_sv.domain.comment;

import com.sparta.instagram_clone_sv.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
