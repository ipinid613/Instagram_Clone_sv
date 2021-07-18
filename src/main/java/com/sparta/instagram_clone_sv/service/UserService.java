package com.sparta.instagram_clone_sv.service;

import com.sparta.instagram_clone_sv.web.dto.SignupRequestDto;
import com.sparta.instagram_clone_sv.exception.UserRequestException;
import com.sparta.instagram_clone_sv.domain.user.User;
import com.sparta.instagram_clone_sv.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입 시, 유효성 체크
    public void validateHandling(Errors errors) {
        String errorMessage;
        for (FieldError error : errors.getFieldErrors()) {
            errorMessage = error.getDefaultMessage(); // getField() 로 하면 defaultmessage 출력 불가함. getDefaultMesasge로 해야해요!
            throw new UserRequestException(errorMessage);
        }
    }
    ////// 기존 코드 //////
    //public Map<String, String> validateHandling(Errors errors) {
    //        Map<String, String> validatorResult = new HashMap<>();
    //        for (FieldError error : errors.getFieldErrors()) {
    //            // errors.getFieldErrors() :
    //            // 입력 정보(아이디, 비밀번호, 비번 재확인, 이메일)의 유효성검사 결과에 대한 오류값(ex : 공백이 있다거나, 정규식 위반하는 경우들)
    //            // 여러 오류값들을 for문을 돌며 하나 하나씩 꺼냄(ex : 아이디 유효성검사 결과 오류 하나)
    //            String validKeyName = String.format("error", error.getField());
    //            // 하나의 오류값을 format처리하여 validKeyName에 저장. / ex) {valid_username : "유저명은 필수 입력 값입니다"}
    //            validatorResult.put(validKeyName, error.getDefaultMessage());
    //        }
    //        return validatorResult; // 유효성검사 통과 시 {msg : null} 반환하고 db에 저장됨.
    //    }


    // 회원가입
    public void registerUser(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String errorMessage;
        // 중복 이메일 확인
        String email = signupRequestDto.getEmail();
        if (userRepository.findByEmail(email).isPresent()) {
            errorMessage = "중복된 email이 존재합니다.";
            throw new UserRequestException(errorMessage);
        }
        // 회원 실명 중복 확인
        String realname = signupRequestDto.getRealname();
        if (userRepository.findByRealname(realname).isPresent()) {
            errorMessage = "중복된 실명이 존재합니다.";
            throw new UserRequestException(errorMessage);
        }
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            errorMessage = "중복된 유저명이 존재합니다.";
            throw new UserRequestException(errorMessage);
        }

        // 패스워드 속에 유저명 중복 없애기
        if(signupRequestDto.getPassword().contains(username) || username.contains(signupRequestDto.getPassword())) {
            errorMessage = "유저명을 포함한 비밀번호는 사용불가합니다.";
            throw new UserRequestException(errorMessage);
        }
        // 패스워드 속에 이메일 중복 없애기
        if(signupRequestDto.getPassword().contains(email) || email.contains(signupRequestDto.getPassword())) {
            errorMessage = "이메일을 포함한 비밀번호는 사용불가합니다.";
            throw new UserRequestException(errorMessage);
        }
        // 패스워드 속에 실명 중복 없애기
        if(signupRequestDto.getPassword().contains(realname) || realname.contains(signupRequestDto.getPassword())) {
            errorMessage = "실명을 포함한 비밀번호는 사용불가합니다.";
            throw new UserRequestException(errorMessage);
        }

        ////////// 비밀번호 재확인(클론코딩 상 요구사항 아니므로 삭제 //////////
//        if (!signupRequestDto.getPassword().equals(signupRequestDto.getRepassword())) {
//            errorMessage = "비밀번호가 일치하지 않습니다.";
//            throw new UserRequestException(errorMessage);
//        }
        // 패스워드 인코딩
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        /// 위의 조건을 다 통과한 경우에 한해 userRepository.save 가능함 ///
        User user = new User(email, realname, username, password);
        userRepository.save(user);
    }

    ////// 유저 비밀번호 변경(update) 관련 부분 //////
//    @Transactional
//    public void update(String newPass, User user){
//        user.setPassword(passwordEncoder.encode(newPass));
//        userRepository.save(user);
//    }
}

