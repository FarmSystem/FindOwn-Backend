package Farm.Team4.FindOwnv2.service.auth;

import Farm.Team4.FindOwnv2.dto.login.JwtDTO;
import Farm.Team4.FindOwnv2.service.auth.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    public JwtDTO login(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        JwtDTO jwtDTO = jwtProvider.generateToken(authenticate);

        return jwtDTO;
    }
}
