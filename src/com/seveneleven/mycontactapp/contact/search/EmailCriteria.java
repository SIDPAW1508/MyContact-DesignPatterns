
package com.seveneleven.mycontactapp.contact.search;

import com.seveneleven.mycontactapp.contact.model.*;

public class EmailCriteria implements SearchCriteria {

    private String email;

    public EmailCriteria(String email) {
        this.email = email.toLowerCase();
    }

    @Override
    public boolean matches(Contact contact) {

        return contact.getEmailAddresses()
                .stream()
                .anyMatch(e -> e.getEmail()
                .toLowerCase()
                .contains(email));
    }
}