package com.tahmidu.notes_web_app.service;

import com.tahmidu.notes_web_app.model.User;

public interface IAccountManagementService {

    User registerUserAccount(User user);
    boolean checkIfUserExists(User user);
    boolean verifyAccount(String email, int token);
    boolean checkIfVerified(String email);
    void resendVerificationToken(String email);
}
