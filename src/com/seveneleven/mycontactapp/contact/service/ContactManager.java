package com.seveneleven.mycontactapp.contact.service;

import java.util.*;

import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.observer.ContactObserver;

public class ContactManager {

    private List<Contact> contacts;
    private List<ContactObserver> observers = new ArrayList<>();

    public ContactManager(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addObserver(ContactObserver observer) {
        observers.add(observer);
    }

    public void deleteContact(Contact contact) {

        contacts.remove(contact);

        notifyObservers(contact);
    }

    private void notifyObservers(Contact contact) {

        for (ContactObserver observer : observers) {
            observer.onContactDeleted(contact);
        }
    }
}