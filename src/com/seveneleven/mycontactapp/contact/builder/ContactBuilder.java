
package com.seveneleven.mycontactapp.contact.builder;

import java.util.ArrayList;
import java.util.List;

import com.seveneleven.mycontactapp.contact.model.*;

public class ContactBuilder {

    private String name;
    private List<PhoneNumber> phones = new ArrayList<>();
    private List<EmailAddress> emails = new ArrayList<>();

    public ContactBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ContactBuilder addPhone(String number) {
        phones.add(new PhoneNumber(number));
        return this;
    }

    public ContactBuilder addEmail(String email) {
        emails.add(new EmailAddress(email));
        return this;
    }

    public String getName() {
        return name;
    }

    public List<PhoneNumber> getPhones() {
        return phones;
    }

    public List<EmailAddress> getEmails() {
        return emails;
    }
}
