package com.tahmidu.notes_web_app.event;

import com.tahmidu.notes_web_app.model.User;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationComplete extends ApplicationEvent {

    private final User user;

    public OnRegistrationComplete(User user){
        super(user);
        this.user = user;
    }

    public User getUser(){
        return user;
    }
}
