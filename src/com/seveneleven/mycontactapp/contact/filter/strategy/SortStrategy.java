
package com.seveneleven.mycontactapp.contact.filter.strategy;

import java.util.List;
import com.seveneleven.mycontactapp.contact.model.Contact;

public interface SortStrategy {

    void sort(List<Contact> contacts);

}