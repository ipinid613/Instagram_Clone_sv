package com.sparta.instagram_clone_sv.domain.article;

import com.sparta.instagram_clone_sv.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByCreatedAtDesc();
}
