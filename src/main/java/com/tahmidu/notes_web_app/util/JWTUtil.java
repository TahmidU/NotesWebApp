package com.tahmidu.notes_web_app.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.tahmidu.notes_web_app.constant.JWTEnum;
import com.tahmidu.notes_web_app.constant.SecurityConstants;

import java.util.Date;

public class JWTUtil {

    public static String generateToken(String email, JWTEnum jwtType){

        if(jwtType == JWTEnum.ACCESS_TOKEN)
            return JWT.create()
                    .withSubject(email)
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.ACCESS_JWT_EXPIRATION_TIME))
                    .sign(Algorithm.HMAC256(SecurityConstants.SECRET));

        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.REFRESH_JWT_EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SecurityConstants.SECRET));
    }

    public static String retrieveEmailFromJWT(String authorizationHeader){

        // Remove Bearer from header string
        String token = authorizationHeader.replace(SecurityConstants.TOKEN_PREFIX, "");

        try{
            return JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (TokenExpiredException e){
            e.getSuppressed();
            throw new TokenExpiredException(e.getMessage());
        }

    }

}
