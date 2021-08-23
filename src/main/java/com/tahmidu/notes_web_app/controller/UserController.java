package com.tahmidu.notes_web_app.controller;

import com.tahmidu.notes_web_app.model.User;
import com.tahmidu.notes_web_app.service.IAccountManagementService;
import com.tahmidu.notes_web_app.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api/user", "/api/user/"})
public class UserController {

    @Autowired private IAccountManagementService accountManagementService;

    @PostMapping(value = {"/signup", "/signup/"})
    public ResponseEntity<?> registerUser(@RequestBody User user){

        if(!RegexUtil.checkUserInputValidity(user)) {
            String userRegistrationRegexFailedMsg = "Invalid user details.";
            return new ResponseEntity<>(new Error(userRegistrationRegexFailedMsg), HttpStatus.BAD_REQUEST);
        }

        if(accountManagementService.checkIfUserExists(user)){
            String emailAlreadyExistsMsg = "Email already registered with another account.";
            return new ResponseEntity<>(new Error(emailAlreadyExistsMsg), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(accountManagementService.registerUserAccount(user), HttpStatus.OK);
    }

    @PostMapping(value = {"/verify", "/verify/"})
    public ResponseEntity<?> confirmRegistration(@RequestParam String email, @RequestParam int token){

        if(!accountManagementService.verifyAccount(email, token)){
            String tokenInvalidMsg = "The token provided is invalid, either cause it is expired or the token is" +
                    " incorrect.";
            return new ResponseEntity<>(new Error(tokenInvalidMsg), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = {"/verification-resend", "/verification-resend/"})
    public ResponseEntity<?> resendVerificationEmail(@RequestParam String email){

        if(accountManagementService.checkIfVerified(email)){
            String verificationMsg = "This email is already verified.";
            return new ResponseEntity<>(new Error(verificationMsg), HttpStatus.OK);
        }

        accountManagementService.resendVerificationToken(email);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
