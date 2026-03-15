package com.seveneleven.mycontactapp.contact.composite;

import java.util.ArrayList;
import java.util.List;

public class ContactGroup implements ContactComponent {

    private List<ContactComponent> components = new ArrayList<>();

    public void add(ContactComponent component) {
        components.add(component);
    }

    @Override
    public void delete() {

        for(ContactComponent component : components) {
            component.delete();
        }

    }
}