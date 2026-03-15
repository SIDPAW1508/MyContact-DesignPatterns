package com.seveneleven.mycontactapp.user.command;

import com.seveneleven.mycontactapp.user.model.User;

public class UpdateEmailCommand implements ProfileCommand {

    private User user;
    private String newEmail;

    public UpdateEmailCommand(User user, String newEmail) {
        this.user = user;
        this.newEmail = newEmail;
    }

    @Override
    public void execute() {
        user.setEmail(newEmail);
        System.out.println("Email updated successfully.");
    }
}