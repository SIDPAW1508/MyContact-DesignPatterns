package com.seveneleven.mycontactapp.user.command;

import com.seveneleven.mycontactapp.user.model.User;

public class UpdateNameCommand implements ProfileCommand {

    private User user;
    private String newName;

    public UpdateNameCommand(User user, String newName) {
        this.user = user;
        this.newName = newName;
    }

    @Override
    public void execute() {
        user.setName(newName);
        System.out.println("Name updated successfully.");
    }
}