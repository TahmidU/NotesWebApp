package com.tahmidu.notes_web_app.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.tahmidu.notes_web_app.constant.JWTEnum;
import com.tahmidu.notes_web_app.constant.SecurityConstants;
import lombok.SneakyThrows;

import java.util.Date;

public class JWTUtil {

    @SneakyThrows
    public static String generateToken(String email, JWTEnum jwtType){

        if(jwtType == JWTEnum.ACCESS_TOKEN){
            try {
                return JWT.create()
                        .withSubject(email)
                        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.ACCESS_JWT_EXPIRATION_TIME))
                        .sign(Algorithm.HMAC256(SecurityConstants.ACCESS_SECRET));
            }catch (SignatureVerificationException ignored){
                return "";
            }
        }

        try{
            return JWT.create()
                    .withSubject(email)
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.REFRESH_JWT_EXPIRATION_TIME))
                    .sign(Algorithm.HMAC256(SecurityConstants.REFRESH_SECRET));
        }catch (SignatureVerificationException ignored){
            return "";
        }

    }

    @SneakyThrows
    public static String retrieveEmailFromJWT(String authorizationHeader, JWTEnum jwtType){

        String same = String.valueOf(System.currentTimeMillis());
        // Remove Bearer from header string
        String token = authorizationHeader.replace(SecurityConstants.TOKEN_PREFIX, "");

        if(jwtType == JWTEnum.ACCESS_TOKEN){
            try{
                return JWT.require(Algorithm.HMAC256(SecurityConstants.ACCESS_SECRET.getBytes()))
                        .build()
                        .verify(token)
                        .getSubject();
            }catch (TokenExpiredException | SignatureVerificationException | JWTDecodeException ignored){
                return "";
            }
        }

        try{
            return JWT.require(Algorithm.HMAC256(SecurityConstants.REFRESH_SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (TokenExpiredException | SignatureVerificationException | JWTDecodeException ignored){
            return "";
        }

    }

}
