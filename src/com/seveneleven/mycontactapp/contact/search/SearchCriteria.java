
package com.seveneleven.mycontactapp.contact.search;

import com.seveneleven.mycontactapp.contact.model.Contact;

public interface SearchCriteria {

    boolean matches(Contact contact);

}