package Farm.Team4.FindOwnv2.security.config;

import Farm.Team4.FindOwnv2.security.handler.CustomAccessDeniedHandler;
import Farm.Team4.FindOwnv2.security.handler.CustomAuthenticationEntryPointHandler;
import Farm.Team4.FindOwnv2.security.handler.CustomAuthenticationFailureHandler;
import Farm.Team4.FindOwnv2.security.handler.CustomAuthenticationSuccessHandler;
import Farm.Team4.FindOwnv2.security.provider.CustomAuthenticationProvider;
import Farm.Team4.FindOwnv2.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;
    private final CustomAuthenticationEntryPointHandler authenticationEntryPointHandler;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(
            CustomUserDetailsService customUserDetailsService,
            PasswordEncoder passwordEncoder) {
        CustomAuthenticationProvider customAuthenticationProvider
        = new CustomAuthenticationProvider(customUserDetailsService, passwordEncoder);
        return new ProviderManager(customAuthenticationProvider);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/", "/api/v2/login").permitAll()
                        .requestMatchers("/api/v2/users").hasRole("USER")
                        .anyRequest().authenticated());
        // 회원가입, 이메일 인증, 로그인 permit all 해야함 -> 그 외 api는 유저 권한으로 제한해도 충분할 듯?

        http
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login")
                        .successHandler(authenticationSuccessHandler)
                        .failureHandler(authenticationFailureHandler)
                        .permitAll());

        http
                .sessionManagement(session -> session
                        .sessionFixation(fix -> fix.newSession())
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false));

        http
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPointHandler)
                        .accessDeniedHandler(accessDeniedHandler));

        return http.build();

    }
}
