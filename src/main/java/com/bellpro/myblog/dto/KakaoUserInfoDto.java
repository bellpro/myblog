package com.bellpro.myblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter // set 메소드 자동 생성
@AllArgsConstructor // 생성자 자동 생성
public class KakaoUserInfoDto {
    private Long id;
    private String nickname;
    private String email;
}