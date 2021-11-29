package com.bellpro.myblog.service;

import com.bellpro.myblog.domain.User;
import com.bellpro.myblog.dto.UserInfoDto;
import com.bellpro.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성,  @Autowired 대신 사용
@Service    // Service 명시(@Component 포함): Bean 등록
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 클라이언트 회원가입 요청 시
    @Transactional  // 트랜잭션 처리
    public void registerUser(UserInfoDto userInfoDto){
        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));   // 비밀번호 암호화

        // 클라이언트가 요청한 사용자 객체 생성 후 DB 저장
        User user = new User(userInfoDto);
        userRepository.save(user);

    }
}
