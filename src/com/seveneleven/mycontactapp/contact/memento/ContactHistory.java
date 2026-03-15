package com.seveneleven.mycontactapp.contact.memento;

import java.util.Stack;

public class ContactHistory {

    private Stack<ContactMemento> history = new Stack<>();

    public void save(ContactMemento memento) {
        history.push(memento);
    }

    public ContactMemento undo() {

        if (!history.isEmpty()) {
            return history.pop();
        }

        return null;
    }
}