
package com.seveneleven.mycontactapp.contact.search;

import com.seveneleven.mycontactapp.contact.model.Contact;

public class NameCriteria implements SearchCriteria {

    private String keyword;

    public NameCriteria(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean matches(Contact contact) {

        return contact.getName()
                .toLowerCase()
                .contains(keyword);
    }
}