package Farm.Team4.FindOwnv2.security.provider;

import Farm.Team4.FindOwnv2.security.service.CustomUserDetailsService;
import Farm.Team4.FindOwnv2.security.service.PrincipalContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        PrincipalContext principalContext = (PrincipalContext) userDetailsService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, principalContext.getMember().getPassword()))
            throw new BadCredentialsException("password doesn't match! check your password!");

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(principalContext.getMember(), principalContext.getPassword(), principalContext.getAuthorities());

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
