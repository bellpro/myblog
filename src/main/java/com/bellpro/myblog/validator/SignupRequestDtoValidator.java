package com.bellpro.myblog.validator;

import com.bellpro.myblog.domain.dto.SignupRequestDto;
import com.bellpro.myblog.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignupRequestDtoValidator implements Validator {   // Validator 인터페이스 상속
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {   // 타입 검증 (Validator 검증할 수 있는 클래스인지 판단하는 메소드)
        return SignupRequestDto.class.isAssignableFrom(clazz); // 서브클래스가 사용될 수 있도록 isAssignableForm 사용
    }

    @Override
    public void validate(Object target, Errors errors) {    // 입력 값 검증 (실제 검증이 이루어지는 메소드)
        SignupRequestDto signupRequestDto = (SignupRequestDto) target;

        if(userRepository.existsByUsername(signupRequestDto.getUsername())) {
            errors.rejectValue("username", "invalid.username", "이미 사용중인 닉네임 입니다.");
        }
        if(signupRequestDto.getPassword().equals(signupRequestDto.getUsername())){
            errors.rejectValue("password", "wrong.password", "닉네임과 비밀번호가 같습니다.");
        }
        if(!signupRequestDto.getPassword().equals(signupRequestDto.getPasswordConfirm())){
            errors.rejectValue("passwordConfirm", "wrong.password", "비밀번호가 일치하지 않습니다.");
        }

    }

}
