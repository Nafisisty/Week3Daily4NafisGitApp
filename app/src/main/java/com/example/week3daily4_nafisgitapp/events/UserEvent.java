package com.example.week3daily4_nafisgitapp.events;

import com.example.week3daily4_nafisgitapp.model.user.UserProfile;

public class UserEvent {

    private UserProfile user;

    public UserEvent(UserProfile user) {
        this.user = user;
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }
}
