package com.sparta.instagram_clone_sv.web.controller;

import com.sparta.instagram_clone_sv.web.dto.signUp.SignupRequestDto;
import com.sparta.instagram_clone_sv.exception.UserRequestException;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.domain.user.UserRepository;
import com.sparta.instagram_clone_sv.security.JwtTokenProvider;
import com.sparta.instagram_clone_sv.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Api(value = "UserController")
@RestController
public class UserApiController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    // 회원 가입 요청 처리
    @ApiOperation("회원가입")
    @PostMapping("/signup")
    public void registerUser(@Valid @RequestBody SignupRequestDto signupRequestDto, Errors errors) {
        if (errors.hasErrors()) {
            userService.validateHandling(errors);
        }
        userService.registerUser(signupRequestDto);
    }

    // 로그인
    @PostMapping("/login")
    public List<Map<String,String>> login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByUsername(user.get("username"))
                .orElseThrow(() -> new UserRequestException("가입되지 않은 회원입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new UserRequestException("잘못된 비밀번호입니다.");
        }
        Map<String,String>username =new HashMap<>();
        Map<String,String>token = new HashMap<>();
        List<Map<String,String>> tu = new ArrayList<>(); // -> 리스트를 만드는데, Map형태(키:밸류 형태)의 변수들을 담을 것이다.
        token.put("token",jwtTokenProvider.createToken(member.getUsername(), member.getEmail())); // "username" : {username}
        username.put("username",member.getUsername()); // "token" : {token}
        tu.add(username); //List형태 ["username" : {username}]
        tu.add(token); //List형태 ["token" : {token}]
        return tu; // List형태 ["username" : {username}, "token" : {token}]
    }
}