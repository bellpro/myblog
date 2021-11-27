package com.bellpro.myblog.controller;

import com.bellpro.myblog.domain.dto.KakaoUserInfoDto;
import com.bellpro.myblog.domain.dto.SignupRequestDto;
import com.bellpro.myblog.service.KakaoUserService;
import com.bellpro.myblog.service.UserService;
import com.bellpro.myblog.validator.SignupRequestDtoValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성
@Controller // 자동 응답기, controller 명시, @Component 포항: Bean 등록
public class UserController {
    private final UserService userService;  // controller -> service 로 dto 전달하기 위해 사용
    private final KakaoUserService kakaoUserService;
    private final SignupRequestDtoValidator signupRequestDtoValidator;  // 닉네임 중복 검사, 닉네임과 비밀번호 일치 확인

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(this.signupRequestDtoValidator); // 검증 결과를 BindingResult 넣음
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupRequestDto", new SignupRequestDto()); // 빈 회원가입 dto 객체 생성하여 변수 전달
        return "user/signup";    // 회원가입 페이지 (templates/user/signup.html) 이동
    }

    @PostMapping("/signup")
    public String signupSubmit(@Valid @ModelAttribute SignupRequestDto signupRequestDto, BindingResult result){
        if (result.hasErrors()){
            return "user/signup";   // 회원가입 오류 페이지 (templates/user/signup.html) 이동
        }
        userService.registerUser(signupRequestDto); // service 로 dto 전달

        return "redirect:/login";   // 로그인 페이지 (templates/user/login.html) 이동
    }

    @GetMapping("/login")
    public String login() {
        return "user/login"; // 로그인 페이지 이동
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "user/login";
    }

    @GetMapping("/login/forbidden")
    public String forbidden(Model model) {
        model.addAttribute("forbidden", true);
        return "user/login";
    }

    @GetMapping("/login/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}
