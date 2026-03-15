package com.seveneleven.mycontactapp.contact.display;

public abstract class ContactDisplayDecorator implements ContactDisplay {

    protected ContactDisplay display;

    public ContactDisplayDecorator(ContactDisplay display) {
        this.display = display;
    }
}