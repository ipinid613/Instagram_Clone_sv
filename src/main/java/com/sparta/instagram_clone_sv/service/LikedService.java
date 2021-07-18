package com.sparta.instagram_clone_sv.service;


import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.liked.Liked;
import com.sparta.instagram_clone_sv.domain.liked.LikedRepository;
import com.sparta.instagram_clone_sv.domain.user.User;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikedService {

    private final LikedRepository likedRepository;

    @Transactional
    public void toggleLiked (Article article, User user){
        Optional<Liked> liked = likedRepository.findByArticleAndUserAndEnabled(article,user,true);

        if(liked.isPresent()){
            liked.get().deActivate();
        }else{
            likedRepository.save(Liked.builder()
            .article(article)
            .user(user)
            .build());
        }
    }
}
