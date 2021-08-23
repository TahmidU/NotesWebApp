package com.tahmidu.notes_web_app.util;

import com.tahmidu.notes_web_app.model.User;
import lombok.SneakyThrows;

import java.util.regex.Pattern;

public class RegexUtil {

    @SneakyThrows
    public static boolean checkUserInputValidity(User user) {

        String nameRegex = "^(?=.*[A-Z][a-z])[A-Z][a-z]{1,30}$";
        String displayNameRegex = "^(?=.*[A-Za-z]).{1,30}$";
        String emailRegex = "^(.+)@(.+)\\.+(.+)$";
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,16}$";

        return Pattern.compile(nameRegex).matcher(user.getFirstName()).matches() &&
                Pattern.compile(nameRegex).matcher(user.getLastName()).matches() &&
                Pattern.compile(displayNameRegex).matcher(user.getDisplayUsername()).matches() &&
                Pattern.compile(emailRegex).matcher(user.getEmail()).matches() &&
                Pattern.compile(passwordRegex).matcher(user.getPassword()).matches();
    }
}
