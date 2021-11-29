package com.bellpro.myblog.controller;

import com.bellpro.myblog.dto.UserInfoDto;
import com.bellpro.myblog.security.UserDetailsImpl;
import com.bellpro.myblog.service.UserService;
import com.bellpro.myblog.validator.UserInfoDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성, @Autowired 대신 사용
@Controller // 자동 응답기, controller 명시(@Component 포함): Bean 등록
public class UserController {
    private final UserService userService;
    private final UserInfoDtoValidator userInfoDtoValidator;

    // 회원가입 페이지 요청 (GET)
    @GetMapping("/user/signup")
    public String signup(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails != null){   // 이미 로그인한 사용자면 메인 페이지로 이동
            return "user/logged";
        }
        model.addAttribute("userInfoDto", new UserInfoDto()); // 빈 dto 객체 생성하여 변수 전달
        return "user/signup";    // 회원가입 페이지 (초기)
    }

    // 회원가입 요청 (POST)
    @PostMapping("/user/signup")
    public String registerUser(@Valid @ModelAttribute UserInfoDto userInfoDto, Errors errors){
        // 검증
        userInfoDtoValidator.validate(userInfoDto, errors);

        // 오류 처리
        if (errors.hasErrors()){
            return "user/signup";   // 회원가입 페이지 이동 (오류 내용 포함)
        }

        // 정상 처리
        userService.registerUser(userInfoDto);  // service 로 dto 전달
        return "redirect:/user/login";   // 로그인 페이지 (templates/user/login.html) 이동
    }

    // 사용자 로그인 페이지 요청 (GET)
    @GetMapping("/user/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails != null){   // 이미 로그인한 사용자면 메인 페이지로 이동
            return "user/logged";
        }
        return "user/login";    // 로그인 안했으면 로그인 페이지로 이동
    }

}
