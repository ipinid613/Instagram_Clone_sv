package com.sparta.instagram_clone_sv.service;


import com.sparta.instagram_clone_sv.domain.follow.Follow;
import com.sparta.instagram_clone_sv.domain.follow.FollowRepository;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.domain.user.UserRepository;
import com.sparta.instagram_clone_sv.exception.UserRequestException;
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
            throw new UserRequestException("회원님의 계정을 찾을 수 없습니다.");
        }

        if (PreyUser.isPresent()) {
            for (Follow follow :PreyUser.get().getFolloweeList()) { // follow 받는 사람의 팔로워 리스트 중 하나
                if(follow.getFollower().getId().equals(HunterUser.get().getId())){ // 그 리스트에 이미 헌터유저(팔로우 요청 보내는사람)이 있다면
                    PreyUser.get().getFolloweeList().remove(follow); // 언팔
                    HunterUser.get().getFollowerList().remove(follow);
                    follow.disconnectFollowee();
                    follow.disconnectFollower();

                    HunterUser.get().hasUnFollowing();
                    PreyUser.get().hasUnFollowed();

                    followRepository.delete(follow);
                    return false;
                }
            }
            followRepository.save(Follow.builder()
                    .follower(HunterUser.get())
                    .followee(PreyUser.get())
                    .build());
            HunterUser.get().hasFollowing();
            PreyUser.get().hasFollowed();
            return true;
        } else {
            throw new UserRequestException("해당 유저가 없습니다. userId=" + userId);
        }
    }
}
