package com.seveneleven.mycontactapp.contact.composite;

import com.seveneleven.mycontactapp.contact.model.Contact;
import java.util.List;

public class ContactLeaf implements ContactComponent {

    private Contact contact;
    private List<Contact> contacts;

    public ContactLeaf(Contact contact, List<Contact> contacts) {
        this.contact = contact;
        this.contacts = contacts;
    }

    @Override
    public void delete() {

        contacts.remove(contact);
        System.out.println("Deleted contact: " + contact.getName());

    }
}