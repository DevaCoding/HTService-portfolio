package com.springboot2.htservice.config.auth;


import com.springboot2.htservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/assets/**", "/login","/board/**", "/css/**", "/img/**", "/js/**", "/h2-console/**",
                        "/trainerboard/**","/**").permitAll()
                .antMatchers("/api/v1/board/**","/api/v1/board","/mypage/**","/trainerboard/**/order","trainerboard/save").hasRole(Role.USER.name())
                .antMatchers("/api/v1/**","/trainerboard/**/update").hasRole(Role.TRAINER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login().loginPage("/login")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
