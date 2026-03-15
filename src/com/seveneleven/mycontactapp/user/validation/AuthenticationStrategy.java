package com.seveneleven.mycontactapp.user.validation;

import java.util.Optional;
import com.seveneleven.mycontactapp.user.model.User;

public interface AuthenticationStrategy {
    Optional<User> authenticate(String username, String password);
}