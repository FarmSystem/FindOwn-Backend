package Farm.Team4.FindOwnv2.handler;

import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("request.getRequestURI() = {} ", request.getRequestURI());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");


        String jsonResponse = String.format("{\"error\": \"%s\"}", CustomErrorCode.NOT_LOGIN_USER.getCode());

        PrintWriter maker = response.getWriter();
        maker.print(jsonResponse);
        maker.flush();

        log.error("error = {}", CustomErrorCode.NOT_LOGIN_USER.getCode());
    }
}
