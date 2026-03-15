
package com.seveneleven.mycontactapp.contact.search;

import java.util.*;
import java.util.stream.*;

import com.seveneleven.mycontactapp.contact.model.Contact;

public class ContactSearchService {

    public static List<Contact> search(
            List<Contact> contacts,
            SearchCriteria criteria){

        return contacts.stream()
                .filter(criteria::matches)
                .collect(Collectors.toList());
    }
}