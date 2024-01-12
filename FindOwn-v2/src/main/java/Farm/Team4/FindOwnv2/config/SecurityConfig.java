package Farm.Team4.FindOwnv2.config;

import Farm.Team4.FindOwnv2.filter.JwtAuthenticationFilter;
import Farm.Team4.FindOwnv2.handler.JwtAccessDeniedHandler;
import Farm.Team4.FindOwnv2.handler.JwtAuthenticationEntryPoint;
import Farm.Team4.FindOwnv2.service.auth.provider.JwtProvider;
import Farm.Team4.FindOwnv2.service.auth.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception{
        http
                .cors(cors -> cors.configure(http))
                .csrf(csrf -> csrf.disable())
                .formLogin(login -> login.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(request -> request
                        // 메인 페이지, 로그인, 회원가입, 이메일 인증 -> permitAll
                        // 그 외 모든 api -> user 권한 필요
                        .requestMatchers("/api/v2/no-auth/**").permitAll()
                        .requestMatchers("/api/v2/users").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        return http.getOrBuild();

    }
}
