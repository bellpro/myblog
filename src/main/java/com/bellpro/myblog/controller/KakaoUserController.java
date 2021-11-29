package com.bellpro.myblog.controller;

import com.bellpro.myblog.service.KakaoUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성, @Autowired 대신 사용
@Controller // 자동 응답기, controller 명시(@Component 포함): Bean 등록
public class KakaoUserController {
    private final KakaoUserService kakaoUserService;

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {    // @RequestParam: 단일 파라미터 전달 받음
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        kakaoUserService.kakaoLogin(code);  // 인증 젍차
        return "redirect:/";    // 메인 페이지로 이동
    }
}
