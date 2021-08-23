package com.tahmidu.notes_web_app.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tahmidu.notes_web_app.event.OnRegistrationComplete;
import com.tahmidu.notes_web_app.model.FakeUser;
import com.tahmidu.notes_web_app.model.User;
import com.tahmidu.notes_web_app.model.VerificationToken;
import com.tahmidu.notes_web_app.repository.IUserRepository;
import com.tahmidu.notes_web_app.repository.IVerificationTokenRepository;
import com.tahmidu.notes_web_app.util.TimeUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class AccountManagementServiceUnitTest {

    @Mock ApplicationEventPublisher applicationEventPublisher;
    @Mock OnRegistrationComplete registrationComplete;

    @Mock IUserRepository userRepository;
    @Mock IVerificationTokenRepository verificationTokenRepository;

    @Mock BCryptPasswordEncoder passwordEncoder;

    @InjectMocks AccountManagementService accountManagementService;

    @BeforeEach
    public void init(){

        MockitoAnnotations.openMocks(this);

        Assertions.assertNotNull(accountManagementService);
    }

    @Test
    public void givenValidUser_whenRegisterUserAccount_thenReturnUserWithEnableStatusFalseAndPasswordEncrypted(){

        // Given
        User validUser = generateValidUser();
        String expectedPassword = "Encoded_Password";

        // When
        Mockito.when(passwordEncoder.encode(validUser.getPassword())).thenReturn(expectedPassword);
        // Return the first argument provide in save().
        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenAnswer(i -> i.getArguments()[0]);
        Mockito.doNothing().when(applicationEventPublisher).publishEvent(Matchers.any(OnRegistrationComplete.class));

        User actualUser = accountManagementService.registerUserAccount(validUser);

        // Then
        Assertions.assertFalse(actualUser.isEnabled());
        Assertions.assertEquals(validUser.getPassword(), actualUser.getPassword());

    }

    @Test
    public void givenEmailAndToken_whenVerifyAccountAndVerificationTokenRepositoryReturnEmpty_thenReturnFalse(){

        // Given
        String email = "tahmid.uddin.dev@gmail.com";
        int token = 12345;

        // When
        User user = generateValidUser();
        user.setUserId(1L);

        Mockito.when(userRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(verificationTokenRepository
                .getActiveTokens(ArgumentMatchers.anyInt(), ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(new ArrayList<>());

        boolean actualResult = accountManagementService.verifyAccount(email, token);

        // Then
        Assertions.assertFalse(actualResult);

    }

    @Test
    public void givenEmailAndToken_whenVerifyAccountAndVerificationTokenRepositoryReturnsActiveToken_thenReturnTrue(){

        // Given
        String email = "tahmid.uddin.dev@gmail.com";
        int token = 12345;

        // When
        User user = generateValidUser();
        user.setUserId(1L);
        List<VerificationToken> verificationTokens = new ArrayList<>();
        verificationTokens
                .add(new VerificationToken(token, TimeUtil.calculateCodeExpiryDate(10), true, user));

        Mockito.when(userRepository.findByEmail(email)).thenReturn(user);
        Mockito.when(verificationTokenRepository
                .getActiveTokens(ArgumentMatchers.anyInt(), ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(verificationTokens);
        Mockito.when(verificationTokenRepository.saveAll(ArgumentMatchers.anyList())).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        boolean actualResult = accountManagementService.verifyAccount(email, token);

        // Then
        Assertions.assertTrue(actualResult);
    }

    private User generateValidUser(){
        return new User("Tahmid", "Uddin", "TahmidU", "tahmid.uddin.dev@gmail.com",
                "HellO!123");
    }
}
