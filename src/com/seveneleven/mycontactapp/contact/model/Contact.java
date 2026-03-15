package com.seveneleven.mycontactapp.contact.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public abstract class Contact {

    private UUID id;
    private String name;
    private List<PhoneNumber> phoneNumbers;
    private List<EmailAddress> emailAddresses;
    private LocalDateTime createdAt;

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
}
