package com.bellpro.myblog.validator;

import com.bellpro.myblog.dto.UserInfoDto;
import com.bellpro.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성,  @Autowired 대신 사용
@Component  // Bean 등록
public class UserInfoDtoValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {   // 타입 검증 (Validator 검증할 수 있는 클래스인지 판단하는 메소드)
        return UserInfoDto.class.isAssignableFrom(clazz); // 서브클래스가 사용될 수 있도록 isAssignableForm 사용
    }

    @Override
    public void validate(Object target, Errors errors) {    // 입력 값 검증 (실제 검증이 이루어지는 메소드)
        UserInfoDto userInfoDto = (UserInfoDto) target;

        if(userRepository.existsByUsername(userInfoDto.getUsername())) {
            errors.rejectValue("username", "invalid.username", "이미 사용중인 닉네임 입니다.");
        }
        if(userRepository.existsByEmail(userInfoDto.getEmail())) {
            errors.rejectValue("email", "invalid.email", "이미 사용중인 이메일 입니다.");
        }
        if(userInfoDto.getPassword().equals(userInfoDto.getUsername())){
            errors.rejectValue("password", "wrong.password", "비밀번호가 닉네임과 같습니다.");
        }
        if(!userInfoDto.getPassword().equals(userInfoDto.getPasswordConfirm())){
            errors.rejectValue("passwordConfirm", "wrong.password", "비밀번호가 일치하지 않습니다.");
        }

    }

}
