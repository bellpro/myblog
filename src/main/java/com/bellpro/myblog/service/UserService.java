package com.bellpro.myblog.service;

import com.bellpro.myblog.domain.dto.SignupRequestDto;
import com.bellpro.myblog.domain.user.User;
import com.bellpro.myblog.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성
@Service // Service 명시, @Component 포함: Bean 등록
public class UserService {
    private final UserRepository userRepository;    // SQL 사용 (회원가입 -> 사용자 저장)
//    private final PasswordEncoder passwordEncoder;  // 비밀번호 암호화

    @Transactional  // 트랜잭션 처리 선언
    public void registerUser(SignupRequestDto signUpRequestDto) {
//        signUpRequestDto.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));   // 비밀번호 암호화
        User user = new User(signUpRequestDto); // 회원가입 요청 (닉네임, 비밀번호, 비밀번호 확인) -> 사용자 객체 생성 (닉네임, 비밀번호)
        userRepository.save(user);  // DB에 사용자 저장
    }
}