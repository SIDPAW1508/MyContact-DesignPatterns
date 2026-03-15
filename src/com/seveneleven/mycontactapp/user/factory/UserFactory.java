package com.seveneleven.mycontactapp.user.factory;

import com.seveneleven.mycontactapp.user.model.*;
import com.seveneleven.mycontactapp.user.builder.UserBuilder;

public class UserFactory {

    public static User createUser(String type, UserBuilder builder) {

        if(type.equalsIgnoreCase("FREE")) {
            return new FreeUser(
                    builder.getEmail(),
                    builder.getPassword(),
                    builder.getName()
            );
        }

        else if(type.equalsIgnoreCase("PREMIUM")) {
            return new PremiumUser(
                    builder.getEmail(),
                    builder.getPassword(),
                    builder.getName()
            );
        }

        throw new IllegalArgumentException("Invalid user type");
    }
}