package com.tahmidu.notes_web_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name", nullable = false) private String firstName;
    @Column(name = "last_name", nullable = false) private String lastName;
    @Column(name = "display_username", nullable = false) private String displayUsername;
    @Column(name = "email", nullable = false, unique = true) private String email;

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "TINYINT", length = 1)
    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean isEnabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Note> notes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<VerificationToken> verificationTokens;

    public User(){}

    public User(String displayUsername, String email, String password) {
        this.displayUsername = displayUsername;
        this.email = email;
        this.password = password;
    }



    public User(String firstName, String lastName, String displayUsername, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayUsername = displayUsername;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String displayUsername, String email, String password,
                Boolean isEnabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayUsername = displayUsername;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return String.valueOf(userId);
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnableStatus(boolean status){
        isEnabled = status;
    }
}
