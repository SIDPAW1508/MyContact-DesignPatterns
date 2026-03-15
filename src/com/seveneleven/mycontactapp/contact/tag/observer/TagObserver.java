
package com.seveneleven.mycontactapp.contact.tag.observer;

import com.seveneleven.mycontactapp.contact.model.Contact;

public interface TagObserver {

    void onTagsUpdated(Contact contact);

}
