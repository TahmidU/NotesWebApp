package com.tahmidu.notes_web_app.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tahmidu.notes_web_app.constant.JWTEnum;
import com.tahmidu.notes_web_app.constant.SecurityConstants;
import com.tahmidu.notes_web_app.model.User;
import com.tahmidu.notes_web_app.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try{
            User cred = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cred.getEmail(),
                    cred.getPassword(), new ArrayList<>()));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();

        String refreshToken = JWTUtil.generateToken(user.getEmail(), JWTEnum.REFRESH_TOKEN);
        String accessToken = JWTUtil.generateToken(user.getEmail(), JWTEnum.ACCESS_TOKEN);

        Map<String, String> jsonRes = new HashMap<>();
        jsonRes.put(SecurityConstants.JSON_REFRESH, refreshToken);
        jsonRes.put(SecurityConstants.JSON_ACCESS, accessToken);

        response.getWriter().write(new ObjectMapper().writeValueAsString(jsonRes));
        response.setContentType("application/json");

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.addHeader(SecurityConstants.HEADER_AUTH_FAIL, failed.getMessage());
    }
}
