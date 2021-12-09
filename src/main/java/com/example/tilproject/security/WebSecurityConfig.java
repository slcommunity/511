package com.example.tilproject.security;

import com.example.tilproject.controller.JwtAuthenticationEntryPoint;
import com.example.tilproject.controller.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtRequestFilter;
    private final AdminAccessHandler adminAccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http
//                .authorizeRequests()
//                // image 폴더를 login 없이 허용
//                .antMatchers("/image/**").permitAll()
//                // css 폴더를 login 없이 허용
//                .antMatchers("/css/**").permitAll()
//                .antMatchers("/userInfo/**").permitAll()
//                .antMatchers("/h2-console/**").permitAll()
//                .antMatchers("/sign-in").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/js/**.js").permitAll()
//                .antMatchers("/signup").permitAll()
//                .antMatchers("/static/**.html").permitAll()
//                .antMatchers("/**.html").permitAll()
//                .antMatchers("/users").permitAll()
//                .antMatchers("/admin/**").permitAll()
//                .antMatchers("/roles").permitAll()
//                // 그 외 모든 요청은 인증과정 필요
//                .anyRequest().authenticated()
//                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().accessDeniedHandler(adminAccessHandler);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
