package Farm.Team4.findOwn.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    private static final String[] whiteList = {"/", "/api/member", "/api/login", "/api/mail"}; // 기본 홈 화면, 회원가입, 로그인
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        try{
            log.info("login filter 진입");
            if (checkURL(requestURI)) {
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute("loginMember") == null){
                    log.info("인증되지 않은 사용자 요청, requestURL: {}", requestURI);
                    httpResponse.sendError(401, "로그인이 필요한 서비스입니다.");
                    return ;
                }
            }
            chain.doFilter(request, response);
        } catch(Exception e){
            throw e;
        } finally {
            log.info("login filter 종료");
        }

    }
    /**
     * 우리가 확인해야 하는 url 값인지 파악
     * -> 확인을 해야하는 url -> 참
     * -> 확인이 필요없는 url (홈화면, 회원가입, 로그인)-> 부정
     * @param requestURL -> 요청을 보낸 url
     * @return boolean
     */
    private boolean checkURL(String requestURL){
        return !PatternMatchUtils.simpleMatch(whiteList, requestURL);
    }
}
