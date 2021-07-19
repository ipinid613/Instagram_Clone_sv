package com.sparta.instagram_clone_sv.service;


import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.article.ArticleRepository;
import com.sparta.instagram_clone_sv.domain.liked.Liked;
import com.sparta.instagram_clone_sv.domain.liked.LikedRepository;
import com.sparta.instagram_clone_sv.domain.user.User;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikedService {

    private final LikedRepository likedRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public Boolean toggleLiked(Long articleId, User user) {
        Optional<Article> article = articleRepository.findById(articleId);

        if (article.isPresent()) {
            for (Liked liked : article.get().getLikedList()) {
                if (liked.getUser().getId().equals(user.getId())) {
                    article.get().getLikedList().remove(liked);
                    liked.disconnectArticle();
                    likedRepository.delete(liked);
                    return false;
                }
            }
            likedRepository.save(Liked.builder()
                    .user(user)
                    .article(article.get())
                    .build());
            return true;
        } else {
            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId);
        }


//        if (article.isPresent()) {
//            Optional<Liked> liked = likedRepository.findByArticleAndUser(article.get(), user);
//            if (liked.isPresent()) {
//                likedRepository.delete(liked.get());
//                return false;
//            } else {
//                likedRepository.save(Liked.builder()
//                        .user(user)
//                        .article(article.get())
//                        .build());
//                return true;
//            }
//        } else {
//            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId);
//        }
    }
}
