package com.bellpro.myblog.domain;

import com.bellpro.myblog.dto.UserInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter // set 메소드 자동 생성
@Getter // get 메소드 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
@Entity     // DB 테이블 설정
public class User extends Timestamped { // 생성날짜/수정날짜 상속 받음
    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.AUTO)  // 자동으로 인덱스 증가
    private Long id;

    @Column(nullable = false, unique = true)    // 열 설정 (무조건 입력, 중복 허용 X)
    private String username;                    // 사용자 아이디

    @Column(nullable = false)                   // 열 설정 (무조건 입력)
    private String password;                    // 사용자 패스워드

    @Column(nullable = false, unique = true)    // 열 설정 (무조건 입력, 중복 허용 X)
    private String email;                       // 사용자 이메일

    @Column(unique = true)                      // 열 설정 (중복 허용 X)
    private Long kakaoId;                       // 사용자 카카오 아이디

    // 사용자 dto 생성자
    public User(UserInfoDto userInfoDto){
        this.username = userInfoDto.getUsername();
        this.password = userInfoDto.getPassword();
        this.email = userInfoDto.getEmail();
    }

    // 카카오 사용자 dto 생성자
    public User(String username, String password, String email, Long kakaoId){
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId = kakaoId;
    }
}
