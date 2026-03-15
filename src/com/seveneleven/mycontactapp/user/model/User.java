package com.seveneleven.mycontactapp.user.model;

import java.util.ArrayList;
import java.util.List;

import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.exception.ValidationException;
import com.seveneleven.mycontactapp.user.validation.ValidationUtil;

public abstract class User {

    private String name;
    private String email;
    private String hashedPassword;

    // Each user has their own contacts
    private List<Contact> contacts = new ArrayList<>();

    protected User(String email, String hashedPassword, String name) {
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.name = name;
    }

    public abstract String getRole();

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    // UC-03: Change name
    public void setName(String name) {
        ValidationUtil.validateNotBlank(name, "Name");
        this.name = name;
    }

    // UC-03: Change password
    public void changePassword(String oldPassword, String newPassword) {

        if (!checkPassword(oldPassword)) {
            throw new ValidationException("Old password is incorrect.");
        }

        ValidationUtil.validatePassword(newPassword);
        this.hashedPassword = ValidationUtil.hashPassword(newPassword);
    }

    // Password verification
    public boolean checkPassword(String rawPassword) {
        return ValidationUtil.hashPassword(rawPassword)
                .equals(this.hashedPassword);
    }

    // Optional email update
    public void setEmail(String email) {
        ValidationUtil.validateEmail(email);
        this.email = email.trim().toLowerCase();
    }
}