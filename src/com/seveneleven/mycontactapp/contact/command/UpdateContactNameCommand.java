package com.seveneleven.mycontactapp.contact.command;

import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.memento.*;

public class UpdateContactNameCommand implements ContactCommand {

    private Contact contact;
    private String newName;
    private ContactHistory history;

    public UpdateContactNameCommand(Contact contact,
                                    String newName,
                                    ContactHistory history) {

        this.contact = contact;
        this.newName = newName;
        this.history = history;
    }

    @Override
    public void execute() {

        history.save(new ContactMemento(contact));

        contact.setName(newName);
    }

    @Override
   
    public void undo() {

        ContactMemento memento = history.undo();

        if (memento != null) {
            contact.setName(memento.getName());
        }
    }
}