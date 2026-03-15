package com.seveneleven.mycontactapp;

import com.seveneleven.mycontactapp.user.model.User;

public class SessionManager {

    private static SessionManager instance;

    private User loggedInUser;

    private SessionManager() {}

    public static SessionManager getInstance() {

        if(instance == null) {
            instance = new SessionManager();
        }

        return instance;
    }

    public void login(User user) {
        loggedInUser = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
    }
}