
package com.seveneleven.mycontactapp.contact.filter;

import java.util.List;
import java.util.stream.Collectors;

import com.seveneleven.mycontactapp.contact.model.Contact;

public class FilterService {

    public static List<Contact> filterContacts(
            List<Contact> contacts,
            ContactFilter filter){

        return contacts.stream()
                .filter(filter::apply)
                .collect(Collectors.toList());
    }
}