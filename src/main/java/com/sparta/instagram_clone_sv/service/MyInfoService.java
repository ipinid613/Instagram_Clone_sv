package com.sparta.instagram_clone_sv.service;

import com.sparta.instagram_clone_sv.domain.article.Article;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.domain.user.UserRepository;
import com.sparta.instagram_clone_sv.web.dto.myInfo.MyInfoArticleResponseDto;
import com.sparta.instagram_clone_sv.web.dto.myInfo.MyInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyInfoService {

    private final UserRepository userRepository;

    public MyInfoResponseDto readMyInfo(User user) {
        User contextUser = userRepository.findById(user.getId()).get();

        List<Article> articleList = contextUser.getArticleList();
        List<MyInfoArticleResponseDto> myInfoArticleResponseDtoList = new ArrayList<>();
        for(Article article:articleList){
            myInfoArticleResponseDtoList.add(MyInfoArticleResponseDto.of(article));
        }



        return MyInfoResponseDto.builder()
                .myProfileImageUrl(contextUser.getProfileImageUrl())
                .nickname(contextUser.getNickname())
                .articleCount(contextUser.getUserInfo().getArticleCount())
                .followerCount(contextUser.getUserInfo().getFollowerCount())
                .followCount(contextUser.getUserInfo().getFollowCount())
                .myInfoArticleResponseDtoList(myInfoArticleResponseDtoList)
                .build();
    }
}
