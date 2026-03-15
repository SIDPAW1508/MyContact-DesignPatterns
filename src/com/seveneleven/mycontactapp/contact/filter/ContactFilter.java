
package com.seveneleven.mycontactapp.contact.filter;

import com.seveneleven.mycontactapp.contact.model.Contact;

public interface ContactFilter {

    boolean apply(Contact contact);

}