
package com.seveneleven.mycontactapp.contact.tag.service;

import java.util.*;

import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.tag.Tag;
import com.seveneleven.mycontactapp.contact.tag.observer.TagObserver;

public class TagManager {

    private List<TagObserver> observers = new ArrayList<>();

    public void addObserver(TagObserver observer){
        observers.add(observer);
    }

    public void applyTag(Contact contact, Tag tag){

        contact.addTag(tag);
        notifyObservers(contact);
    }

    public void removeTag(Contact contact, Tag tag){

        contact.removeTag(tag);
        notifyObservers(contact);
    }

    private void notifyObservers(Contact contact){

        for(TagObserver obs : observers){
            obs.onTagsUpdated(contact);
        }
    }
}
