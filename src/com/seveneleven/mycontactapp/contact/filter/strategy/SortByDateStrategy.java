
package com.seveneleven.mycontactapp.contact.filter.strategy;

import java.util.Comparator;
import java.util.List;

import com.seveneleven.mycontactapp.contact.model.Contact;

public class SortByDateStrategy implements SortStrategy {

    @Override
    public void sort(List<Contact> contacts){

        contacts.sort(Comparator.comparing(Contact::getCreatedAt));
    }
}