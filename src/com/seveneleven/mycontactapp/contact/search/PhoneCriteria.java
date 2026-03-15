package com.seveneleven.mycontactapp.contact.search;

import com.seveneleven.mycontactapp.contact.model.*;

public class PhoneCriteria implements SearchCriteria {

    private String phone;

    public PhoneCriteria(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean matches(Contact contact) {

        return contact.getPhoneNumbers()
                .stream()
                .anyMatch(p -> p.getNumber().contains(phone));
    }
}