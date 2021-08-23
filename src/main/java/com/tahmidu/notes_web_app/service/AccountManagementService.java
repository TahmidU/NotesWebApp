package com.tahmidu.notes_web_app.service;

import com.tahmidu.notes_web_app.event.OnRegistrationComplete;
import com.tahmidu.notes_web_app.model.User;
import com.tahmidu.notes_web_app.model.VerificationToken;
import com.tahmidu.notes_web_app.repository.IUserRepository;
import com.tahmidu.notes_web_app.repository.IVerificationTokenRepository;
import com.tahmidu.notes_web_app.util.TimeUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class AccountManagementService implements IAccountManagementService{

    @Autowired private IUserRepository userRepository;
    @Autowired private IVerificationTokenRepository verificationTokenRepository;

    @Autowired private ApplicationEventPublisher applicationEventPublisher;

    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUserAccount(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnableStatus(false);

        userRepository.save(user);
        applicationEventPublisher.publishEvent(new OnRegistrationComplete(user));
        return user;
    }

    @Override
    public boolean verifyAccount(String email, int token) {

        User fullUser = userRepository.findByEmail(email);

        List<VerificationToken> verificationTokens;
        if(fullUser != null && fullUser.getUserId() != null) {
            verificationTokens = verificationTokenRepository.getActiveTokens(token,
                    fullUser.getUserId(),
                    TimeUtil.calculateTime());
        }else{
            return false;
        }



        if(!verificationTokens.isEmpty()) {

            boolean foundActiveToken = false;
            List<VerificationToken> modifiedVerificationToken = new ArrayList<>();

            for(VerificationToken vt : verificationTokens){

                if(vt.isActive()){
                    foundActiveToken = true;
                }

                vt.setActiveStatus(false);
                modifiedVerificationToken.add(vt);

            }
            verificationTokenRepository.saveAll(modifiedVerificationToken);

            if(foundActiveToken){
                fullUser.setEnableStatus(true);
                userRepository.save(fullUser);

            }

            return true;
        }

        return false;
    }

    @Override
    public boolean checkIfUserExists(User user) {
        return userRepository.existsByEmail(user.getEmail());
    }

    @Override
    public boolean checkIfVerified(String email) {
        return userRepository.findByEmail(email).isEnabled();
    }

    @Override
    public void resendVerificationToken(String email) {

        User user = userRepository.findByEmail(email);

        applicationEventPublisher.publishEvent(new OnRegistrationComplete(user));

    }

}
