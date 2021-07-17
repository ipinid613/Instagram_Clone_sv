package com.sparta.instagram_clone_sv.repository;

import com.sparta.instagram_clone_sv.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
