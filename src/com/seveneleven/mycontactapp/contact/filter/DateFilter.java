
package com.seveneleven.mycontactapp.contact.filter;

import java.time.LocalDate;
import com.seveneleven.mycontactapp.contact.model.Contact;

public class DateFilter implements ContactFilter {

    private LocalDate date;

    public DateFilter(LocalDate date){
        this.date = date;
    }

    @Override
    public boolean apply(Contact contact){

        return contact.getCreatedAt()
                .toLocalDate()
                .isAfter(date);
    }
}
