package com.tahmidu.notes_web_app.config;

import com.tahmidu.notes_web_app.filter.JWTAuthenticationFilter;
import com.tahmidu.notes_web_app.filter.JWTAuthorizationFilter;
import com.tahmidu.notes_web_app.service.UserDetailsImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired private UserDetailsService userDetailsService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        final String baseURL = "/api/auth";
        final String[] permittedURLPatterns = new String[]{
                String.format("%s/signup/**", baseURL),
                String.format("%s/verify/**", baseURL),
                String.format("%s/verification-resend/**", baseURL),
                String.format("%s/refresh-token/**", baseURL)
        };

        http
                .cors()
                .and()
                .requestMatchers()
                .antMatchers("/login")
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(permittedURLPatterns[0]).permitAll()
                .antMatchers(permittedURLPatterns[1]).permitAll()
                .antMatchers(permittedURLPatterns[2]).permitAll()
                .antMatchers(permittedURLPatterns[3]).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
