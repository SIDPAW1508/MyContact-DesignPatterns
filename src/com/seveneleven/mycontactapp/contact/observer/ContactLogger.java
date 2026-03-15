package com.seveneleven.mycontactapp.contact.observer;

import com.seveneleven.mycontactapp.contact.model.Contact;

public class ContactLogger implements ContactObserver {

    @Override
    public void onContactDeleted(Contact contact) {

        System.out.println("Observer Notification:");
        System.out.println("Contact deleted -> " + contact.getName());

    }
}