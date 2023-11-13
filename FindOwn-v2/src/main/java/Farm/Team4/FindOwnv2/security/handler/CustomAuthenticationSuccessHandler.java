package Farm.Team4.FindOwnv2.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("authentication success handler 진입 성공: 인증 성공");
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest == null){
            log.info("기존 요청이 없는 상황 -> 기본 페이지로 이동");
            response.sendRedirect("http://localhost:3000/home");
        } else {
            log.info("기존 요청이 인증 이슈로 인한 반려, 그 요청으로 다시 redirect");
            response.sendRedirect(savedRequest.getRedirectUrl());
        }

    }
}
