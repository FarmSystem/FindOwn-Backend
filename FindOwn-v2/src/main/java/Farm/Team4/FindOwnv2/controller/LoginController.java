package Farm.Team4.FindOwnv2.controller;

import Farm.Team4.FindOwnv2.dto.login.LoginDTO;
import Farm.Team4.FindOwnv2.dto.login.JwtDTO;
import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import Farm.Team4.FindOwnv2.exception.FindOwnException;
import Farm.Team4.FindOwnv2.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private static String BEARER_PREFIX = "Bearer ";

    @PostMapping("/login")
    public JwtDTO login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        try {
            JwtDTO token = loginService.login(loginDTO.getUsername(), loginDTO.getPassword());
            response.setHeader("Authorization", BEARER_PREFIX + token.getAccessToken());
            // 로그인 성공 시 200 OK와 함께 응답
            return token;
        } catch (AuthenticationException e) {
            // 로그인 실패 시 401 Unauthorized와 함께 에러 메시지 응답
            throw new FindOwnException(CustomErrorCode.INVALID_INFORMATION);
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "logout success";
    }
}
