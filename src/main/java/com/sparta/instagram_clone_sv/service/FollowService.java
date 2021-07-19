package com.sparta.instagram_clone_sv.service;


import com.sparta.instagram_clone_sv.domain.follow.Follow;
import com.sparta.instagram_clone_sv.domain.follow.FollowRepository;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public Boolean toggleUser(Long userId, User user) {
        //User = follow를 하는 사람 (follower)
        //userId = follow를 받는 사람 (followee)
        Optional<User> PreyUser = userRepository.findById(userId);
        Optional<User> HunterUser = userRepository.findById(user.getId());
        if (!HunterUser.isPresent()) {
            throw new IllegalArgumentException("아니 이럴리가 없는데");
        }

        if (PreyUser.isPresent()) {
            for (Follow follow :PreyUser.get().getFolloweeList()) {
                if(follow.getFollower().getId().equals(HunterUser.get().getId())){
                    PreyUser.get().getFolloweeList().remove(follow);
                    HunterUser.get().getFollowerList().remove(follow);
                    follow.disconnectFollowee();
                    follow.disconnectFollower();
                    followRepository.delete(follow);
                    return false;
                }
            }
            followRepository.save(Follow.builder()
                    .follower(HunterUser.get())
                    .followee(PreyUser.get())
                    .build());
            return true;
        } else {
            throw new IllegalArgumentException("해당 유저가 없습니다. userId=" + userId);
        }
    }
}
