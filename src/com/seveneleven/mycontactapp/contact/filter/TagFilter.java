
package com.seveneleven.mycontactapp.contact.filter;

import com.seveneleven.mycontactapp.contact.model.Contact;

public class TagFilter implements ContactFilter {

    private String tag;

    public TagFilter(String tag){
        this.tag = tag.toLowerCase();
    }

    @Override
    public boolean apply(Contact contact){

        return contact.getName()
                .toLowerCase()
                .contains(tag);
    }
}