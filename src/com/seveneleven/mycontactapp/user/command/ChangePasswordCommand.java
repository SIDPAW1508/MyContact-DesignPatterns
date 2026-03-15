package com.seveneleven.mycontactapp.user.command;

import com.seveneleven.mycontactapp.user.model.User;

public class ChangePasswordCommand implements ProfileCommand {

    private User user;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordCommand(User user, String oldPassword, String newPassword) {
        this.user = user;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    public void execute() {
        user.changePassword(oldPassword, newPassword);
        System.out.println("Password changed successfully.");
    }
}