package com.seveneleven.mycontactapp.contact.command;

public interface ContactCommand {
    void execute();
    void undo();
}