package com.sparta.instagram_clone_sv.domain.article;

import com.sparta.instagram_clone_sv.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByCreatedAtDesc();

    List<Article> findAllByEnabledOrderByCreatedAtDesc(boolean b);

    Optional<Article> findByIdAndEnabled(Long articleId, boolean b);
}
