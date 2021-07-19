package com.sparta.instagram_clone_sv.service;


import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final UserRepository userRepository;

    @Transactional
    public Boolean toggleUser(Long userId, User user){
        //User = follow를 하는 사람
        //userId = follow를 받는 사람


    }
}
