package com.tahmidu.notes_web_app.repository;

import com.tahmidu.notes_web_app.model.User;
import com.tahmidu.notes_web_app.model.VerificationToken;
import com.tahmidu.notes_web_app.util.TimeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@DataJpaTest
@PropertySource("application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VerificationTokenRepositoryIT {

    @Autowired IVerificationTokenRepository verificationTokenRepository;
    @Autowired IUserRepository userRepository;

    @BeforeEach
    public void init(){
        Assertions.assertNotNull(verificationTokenRepository);
    }

    @Test
    public void givenActiveByTimeAndStatusToken_whenRetrievingToken_thenReturnToken(){

        // Given
        VerificationToken expectedToken = new VerificationToken(12345, TimeUtil.calculateCodeExpiryDate(10),
                true, generateUser());
        verificationTokenRepository.save(expectedToken);

        // When
        List<VerificationToken> verificationTokens = verificationTokenRepository.getActiveTokens(expectedToken.getToken(),
                expectedToken.getUser().getUserId(), TimeUtil.calculateTime());

        // Then
        VerificationToken actualToken = verificationTokens.get(0);

        Assertions.assertEquals(expectedToken, actualToken);

    }

    @Test
    public void givenInactiveByTimeToken_whenRetrievingToken_thenReturnEmptyList(){

        // Given
        int expectedListLength = 0;
        VerificationToken token = new VerificationToken(12345, TimeUtil.calculateCodeExpiryDate(0),
                true, generateUser());
        verificationTokenRepository.save(token);

        // When
        List<VerificationToken> verificationTokens = verificationTokenRepository.getActiveTokens(token.getToken(),
                token.getUser().getUserId(), TimeUtil.calculateTime());

        // Then
        int actualListLength = verificationTokens.size();

        Assertions.assertEquals(expectedListLength, actualListLength);

    }

    @Test
    public void givenInactiveByStatusToken_whenRetrievingToken_thenReturnEmptyList(){

        // Given
        int expectedListLength = 0;
        VerificationToken token = new VerificationToken(12345, TimeUtil.calculateCodeExpiryDate(0),
                true, generateUser());
        verificationTokenRepository.save(token);

        // When
        List<VerificationToken> verificationTokens = verificationTokenRepository.getActiveTokens(token.getToken(),
                token.getUser().getUserId(), TimeUtil.calculateTime());

        // Then
        int actualListLength = verificationTokens.size();

        Assertions.assertEquals(expectedListLength, actualListLength);
    }

    public User generateUser(){
        User user = new User("Tahmid", "Uddin", "TahmidU", "tahmid.uddin.dev@gmail.com",
                "123", false);
        return userRepository.save(user);
    }

}
