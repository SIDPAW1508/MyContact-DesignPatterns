package com.seveneleven.mycontactapp.contact.memento;

import com.seveneleven.mycontactapp.contact.model.Contact;

public class ContactMemento {

    private String name;

    public ContactMemento(Contact contact) {
        this.name = contact.getName();
    }

    public String getName() {
        return name;
    }
}