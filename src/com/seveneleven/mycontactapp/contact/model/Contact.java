package com.seveneleven.mycontactapp.contact.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.seveneleven.mycontactapp.contact.tag.Tag;

public abstract class Contact {

    private UUID id;
    private String name;
    private List<PhoneNumber> phoneNumbers;
    private List<EmailAddress> emailAddresses;
    private LocalDateTime createdAt;
    private Set<Tag> tags = new HashSet<>();
    

    protected Contact(String name,
                      List<PhoneNumber> phones,
                      List<EmailAddress> emails) {

        this.id = UUID.randomUUID();
        this.name = name;
        this.phoneNumbers = phones;
        this.emailAddresses = emails;
        this.createdAt = LocalDateTime.now();
    }

    public abstract String getType();

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public List<EmailAddress> getEmailAddresses() {
        return emailAddresses;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addTag(Tag tag){
        tags.add(tag);
    }
    public void removeTag(Tag tag){
        tags.remove(tag);
    }
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Contact ID: ").append(id).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Type: ").append(getType()).append("\n");

        sb.append("Phones:\n");
        for (PhoneNumber p : phoneNumbers) {
            sb.append("  ").append(p.getNumber()).append("\n");
        }

        sb.append("Emails:\n");
        for (EmailAddress e : emailAddresses) {
            sb.append("  ").append(e.getEmail()).append("\n");
        }

        sb.append("Created At: ").append(createdAt);

        return sb.toString();
    }
    public Contact(Contact other) {

        this.id = other.id;
        this.name = other.name;

        this.phoneNumbers = new ArrayList<>(other.phoneNumbers);
        this.emailAddresses = new ArrayList<>(other.emailAddresses);

        this.createdAt = other.createdAt;
    }
}
