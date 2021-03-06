package com.tahmidu.notes_web_app.controller;

import com.tahmidu.notes_web_app.constant.SecurityConstants;
import com.tahmidu.notes_web_app.model.User;
import com.tahmidu.notes_web_app.service.IAuthService;
import com.tahmidu.notes_web_app.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {"/api/auth", "/api/auth/"})
@CrossOrigin("*")
public class AuthController {

    @Autowired private IAuthService authService;

    @PostMapping(value = {"/signup", "/signup/"})
    public ResponseEntity<?> registerUser(@RequestBody User user){

        if(!RegexUtil.checkUserInputValidity(user)) {
            String userRegistrationRegexFailedMsg = "Invalid user details.";
            return new ResponseEntity<>(new Error(userRegistrationRegexFailedMsg), HttpStatus.BAD_REQUEST);
        }

        if(authService.checkIfUserExists(user)){
            String emailAlreadyExistsMsg = "Email already registered with another account.";
            return new ResponseEntity<>(new Error(emailAlreadyExistsMsg), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(authService.registerUserAccount(user), HttpStatus.OK);
    }

    @PostMapping(value = {"/verify", "/verify/"})
    public ResponseEntity<?> confirmRegistration(@RequestParam String email, @RequestParam int token){

        if(!authService.verifyAccount(email, token)){
            String tokenInvalidMsg = "The token provided is invalid, either cause it is expired or the token is" +
                    " incorrect.";
            return new ResponseEntity<>(new Error(tokenInvalidMsg), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = {"/verification-resend", "/verification-resend/"})
    public ResponseEntity<?> resendVerificationEmail(@RequestParam String email){

        if(authService.checkIfVerified(email)){
            String verificationMsg = "This email is already verified.";
            return new ResponseEntity<>(new Error(verificationMsg), HttpStatus.OK);
        }

        authService.resendVerificationToken(email);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping(value = {"/refresh-token", "/refresh-token/"})
    public ResponseEntity<?> refreshAccessToken(
            @RequestHeader(SecurityConstants.HEADER_JWT_AUTHORIZATION) String refreshToken){

        String trimmedRefreshToken = refreshToken.replace(SecurityConstants.TOKEN_PREFIX, "");
        String email = authService.retrieveEmailFromJWT(trimmedRefreshToken);
        if(email.isEmpty())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put(SecurityConstants.JSON_REFRESH, trimmedRefreshToken);
        jsonResponse.put(SecurityConstants.JSON_ACCESS, authService.refreshAccessToken(email));

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @GetMapping(value = {"/test", "/test/"})
    public String test(){
        return "Hello!";
    }
}
