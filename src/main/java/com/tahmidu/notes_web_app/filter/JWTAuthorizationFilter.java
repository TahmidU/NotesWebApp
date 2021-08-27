package com.tahmidu.notes_web_app.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tahmidu.notes_web_app.constant.JWTEnum;
import com.tahmidu.notes_web_app.constant.SecurityConstants;
import com.tahmidu.notes_web_app.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        // Exclude
        if(request.getRequestURI().equals("/api/auth/refresh-token") ||
                request.getRequestURI().equals("/api/auth/refresh-token/")){
            chain.doFilter(request, response);
            return;
        }


        String authorizationHeader = request.getHeader(SecurityConstants.HEADER_JWT_AUTHORIZATION);

        if(authorizationHeader != null && !authorizationHeader.startsWith(SecurityConstants.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(getAuthentication(request, authorizationHeader));
        chain.doFilter(request, response);

    }

    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String authorizationHeader){

        if(authorizationHeader != null && !authorizationHeader.isEmpty()){

            String email = JWTUtil.retrieveEmailFromJWT(authorizationHeader, JWTEnum.ACCESS_TOKEN);
            if(email != null && !email.isEmpty())
                return new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());

        }

        return null;
    }
}
