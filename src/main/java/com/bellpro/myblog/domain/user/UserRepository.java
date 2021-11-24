package com.bellpro.myblog.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속 받아서 사용 <엔티티, ID 자료형>
// 공통 인터페이스 사용 가능 (save, delete, findById ...)
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);  // 닉네임 중복검사 (메소드 이름으로 쿼리 생성)

    // Optional 은 NULL 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE 가 발생하지 않도록 도와줌
    // Optional<User> findByUsername(String username); // 닉네임 (메소드 이름으로 쿼리 생성)
}
