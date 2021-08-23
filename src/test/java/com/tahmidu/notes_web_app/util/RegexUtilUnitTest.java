package com.tahmidu.notes_web_app.util;

import com.tahmidu.notes_web_app.model.FakeUser;
import com.tahmidu.notes_web_app.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegexUtilUnitTest {

    @Test
    public void givenValidUser_whenCheckUserInputValidity_thenReturnTrue(){

        // Given
        User user = new User("Tahmid", "Uddin", "TahmidU",
                "tahmid.uddin.dev@gmail.com", "HellO!123");

        // When
        boolean actualResult = RegexUtil.checkUserInputValidity(user);

        // Then
        Assertions.assertTrue(actualResult);

    }

    @Test
    public void givenUserWithInvalidEmail_whenCheckUserInputValidity_thenReturnFalse(){

        // Given
        User user = new User("Tahmid", "Uddin", "TahmidU",
                "tahmid.uddin.dev", "HellO!123");

        // When
        boolean actualResult = RegexUtil.checkUserInputValidity(user);

        // Then
        Assertions.assertFalse(actualResult);
    }

    @Test
    public void givenUserWithInvalidFirstName_whenCheckUserInputValidity_thenReturnFalse(){

        // Given
        User user = new User("TaHmid", "Uddin", "TahmidU",
                "tahmid.uddin.dev@gmail.com", "HellO!123");

        // When
        boolean actualResult = RegexUtil.checkUserInputValidity(user);

        // Then
        Assertions.assertFalse(actualResult);
    }

    @Test
    public void givenUserWithInvalidLastName_whenCheckUserInputValidity_thenReturnFalse(){

        // Given
        User user = new User("Tahmid", "U.d.d.i.n", "TahmidU",
                "tahmid.uddin.dev@gmail.com", "HellO!123");

        // When
        boolean actualResult = RegexUtil.checkUserInputValidity(user);

        // Then
        Assertions.assertFalse(actualResult);
    }

    @Test
    public void givenUserWithInvalidDisplayUsername_whenCheckUserInputValidity_thenReturnFalse(){

        // Given
        User user = new User("Tahmid", "Uddin", ".",
                "tahmid.uddin.dev@gmail.com", "HellO!123");

        // When
        boolean actualResult = RegexUtil.checkUserInputValidity(user);

        // Then
        Assertions.assertFalse(actualResult);
    }

    @Test
    public void givenUserWithInvalidPassword_whenCheckUserInputValidity_thenReturnFalse(){

        // Given
        User user = new User("Tahmid", "Uddin", "TahmidU",
                "tahmid.uddin.dev@gmail.com", "hello123");

        // When
        boolean actualResult = RegexUtil.checkUserInputValidity(user);

        // Then
        Assertions.assertFalse(actualResult);
    }
}
