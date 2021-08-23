package com.tahmidu.notes_web_app.model;

/**
 * This class was created since the original (user) class was having problems with the fact that
 * password was set to Json write only. Therefore, I could not send a JSON with a password field.
 */

public class FakeUser {

    private Long userId;
    private String firstName;
    private String lastName;
    private String displayUsername;
    private String email;
    private String password;
    private Boolean isEnabled;

    public FakeUser(String firstName, String lastName, String displayUsername, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayUsername = displayUsername;
        this.email = email;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayUsername() {
        return displayUsername;
    }

    public void setDisplayUsername(String displayUsername) {
        this.displayUsername = displayUsername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
