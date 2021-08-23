package com.tahmidu.notes_web_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "verification_token")
@Data
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_token_id")
    private Long verificationTokenId;

    @Column(name = "token", nullable = false) private Integer token;
    @Column(name = "expiry", nullable = false) private Long expiry;

    @Column(name = "is_active", nullable = false, columnDefinition = "TINYINT", length = 1)
    @Accessors(fluent = true)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private User user;

    public VerificationToken(){}

    public VerificationToken(Integer token, Long expiry, Boolean isActive, User user) {
        this.token = token;
        this.expiry = expiry;
        this.isActive = isActive;
        this.user = user;
    }

    public boolean isActive(){
        return isActive;
    }

    public void setActiveStatus(boolean status){
        isActive = status;
    }
}
