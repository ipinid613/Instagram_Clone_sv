package com.sparta.instagram_clone_sv.repository;

import com.sparta.instagram_clone_sv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikeRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndArticleId(String username, Long articleId);
}
