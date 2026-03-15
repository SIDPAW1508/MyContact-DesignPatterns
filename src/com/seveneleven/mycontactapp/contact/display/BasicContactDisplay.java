package com.seveneleven.mycontactapp.contact.display;

import com.seveneleven.mycontactapp.contact.model.Contact;

public class BasicContactDisplay implements ContactDisplay {

    private Contact contact;

    public BasicContactDisplay(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String display() {
        return contact.toString();
    }
}