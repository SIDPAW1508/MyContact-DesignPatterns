package com.seveneleven.mycontactapp.user.command;

public class ProfileManager {

    public void executeCommand(ProfileCommand command) {
        command.execute();
    }
}