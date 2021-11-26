package com.bellpro.myblog.domain.user;

import com.bellpro.myblog.domain.Timestamped;
import com.bellpro.myblog.domain.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Setter // set 메소드 자동 생성
@Getter // get 메소드 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
@Entity // DB 테이블 역할
public class User extends Timestamped { // Timestamped 상속
    @Id // ID 기본키(PK) 설정
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가
    private Long id;                                // 기본키 ID

    @Column(nullable = false, unique = true)    // 열 설정 (무조건 입력, 중복 안됨)
    @Length(min = 3)
    private String username;

    @Column(nullable = false)                   // 열 설정 (무조건 입력)
    private String password;

    @Column(unique = true)                          // 열 설정 (중복 안됨)
    private Long kakaoId;                           // 카카오 ID

    // 회원가입 dto 생성자 (닉네임, 비밀번호)
    public User(SignupRequestDto signupRequestDto){
        this.username = signupRequestDto.getUsername();
        this.password = signupRequestDto.getPassword();
    }
}
