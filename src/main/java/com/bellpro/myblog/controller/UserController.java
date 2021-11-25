package com.bellpro.myblog.controller;

import com.bellpro.myblog.domain.dto.SignupRequestDto;
import com.bellpro.myblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성
@Controller // 자동 응답기, controller 명시, @Component 포항: Bean 등록
public class UserController {
    private final UserService userService;  // controller -> service 로 dto 전달하기 위해 사용

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupRequestDto", new SignupRequestDto()); // 빈 회원가입 dto 객체 생성하여 변수 전달
        return "user/signup";    // 회원가입 페이지 (templates/user/signup.html) 이동
    }

    @PostMapping("/signup")
    public String signupSubmit(@Valid @ModelAttribute SignupRequestDto signupRequestDto, BindingResult result){
        if (result.hasErrors()){
            return "user/signup";
        }
        userService.registerUser(signupRequestDto);

        return "user/login";   // 로그인 페이지 (templates/user/login.html) 이동
    }
}
