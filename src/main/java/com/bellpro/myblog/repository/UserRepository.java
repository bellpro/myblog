package com.bellpro.myblog.repository;

import com.bellpro.myblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository 상속 받아서 사용 <엔티티, ID 자료형>
// 공통 인터페이스 사용 가능 (save, delete, findById ...)
// 메소드 이름으로 쿼리 생성 가능
public interface UserRepository extends JpaRepository<User, Long> {  // User 테이블 연결
    boolean existsByUsername(String username);  // 아이디 중복 검사
    boolean existsByEmail(String email);        // 이메일 중복 검사

    // Optional 은 NULL 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE 가 발생하지 않도록 도와줌
    Optional<User> findByUsername(String username); // 사용자 아이디 조회
    Optional<User> findByKakaoId(Long kakaoId);     // 카카오 아이디 조회
}
