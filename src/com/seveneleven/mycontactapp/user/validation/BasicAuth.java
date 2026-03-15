package com.seveneleven.mycontactapp.user.validation;

import java.util.Optional;
import java.util.Map;

import com.seveneleven.mycontactapp.user.model.User;

public class BasicAuth implements AuthenticationStrategy {

    private Map<String, User> users;

    public BasicAuth(Map<String, User> users) {
        this.users = users;
    }

    @Override
    public Optional<User> authenticate(String email, String password) {

        User user = users.get(email);

        if (user != null && user.checkPassword(password)) {
            return Optional.of(user);
        }

        return Optional.empty();
    }
}