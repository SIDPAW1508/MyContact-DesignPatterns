
package com.seveneleven.mycontactapp.contact.filter;

import java.util.*;

import com.seveneleven.mycontactapp.contact.model.Contact;

public class CompositeFilter implements ContactFilter {

    private List<ContactFilter> filters = new ArrayList<>();

    public void addFilter(ContactFilter filter){
        filters.add(filter);
    }

    @Override
    public boolean apply(Contact contact){

        for(ContactFilter f : filters){
            if(!f.apply(contact))
                return false;
        }

        return true;
    }
}