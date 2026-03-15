
package com.seveneleven.mycontactapp.contact.tag.observer;

import com.seveneleven.mycontactapp.contact.model.Contact;

public class TagUIObserver implements TagObserver {

    @Override
    public void onTagsUpdated(Contact contact){

        System.out.println("Tags updated for contact: "
                + contact.getName());
    }
}
