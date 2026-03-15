
package com.seveneleven.mycontactapp.contact.factory;

import com.seveneleven.mycontactapp.contact.builder.ContactBuilder;
import com.seveneleven.mycontactapp.contact.model.*;

public class ContactFactory {

    public static Contact createContact(String type,
                                        ContactBuilder builder) {

        if(type.equalsIgnoreCase("PERSON")) {
            return new PersonContact(
                    builder.getName(),
                    builder.getPhones(),
                    builder.getEmails()
            );
        }

        else if(type.equalsIgnoreCase("ORG")) {
            return new OrganizationContact(
                    builder.getName(),
                    builder.getPhones(),
                    builder.getEmails()
            );
        }

        throw new IllegalArgumentException("Invalid contact type");
    }
}