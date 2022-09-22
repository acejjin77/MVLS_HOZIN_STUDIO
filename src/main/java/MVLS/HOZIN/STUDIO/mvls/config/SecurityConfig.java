package MVLS.HOZIN.STUDIO.mvls.config;
import MVLS.HOZIN.STUDIO.mvls.handler.CustomAccessDeniedHandler;
import MVLS.HOZIN.STUDIO.mvls.handler.CustomAuthenticationEntryPoint;
import MVLS.HOZIN.STUDIO.mvls.handler.LoginFailureHandler;
import MVLS.HOZIN.STUDIO.mvls.handler.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${server.env}")
    private String envStatus;

    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final LoginFailureHandler loginFailureHandler;
    private final LoginSuccessHandler loginSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (envStatus.equals("production")) {
            log.info("Server environment : production");
            http
                    .csrf().disable()
//                    .csrf().and()
//                    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().httpBasic().disable()
                    .authorizeRequests()
                    .antMatchers(
                            "/exec/**", "/css/**", "/js/**", "/images/**",
                            "/useradmin/**", "/public/**")
                    .permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("admId")
                    .passwordParameter("admPwd")
                    .successHandler(loginSuccessHandler)
                    .failureHandler(loginFailureHandler)
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .logoutSuccessUrl("/login")
                    .and()
                    .exceptionHandling()
                    .accessDeniedHandler(customAccessDeniedHandler)
                    .authenticationEntryPoint(customAuthenticationEntryPoint);
        } else {
            http.authorizeRequests().anyRequest().permitAll();
            log.warn("It is a development environment.");
        }
    }

//    private AccessDeniedHandler accessDeniedHandler() {
//        return new CustomAccessDeniedHandler();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}