
package com.seveneleven.mycontactapp;

import java.util.*;

import com.seveneleven.mycontactapp.user.model.*;
import com.seveneleven.mycontactapp.user.validation.*;
import com.seveneleven.mycontactapp.contact.builder.ContactBuilder;
import com.seveneleven.mycontactapp.contact.factory.ContactFactory;
import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.model.EmailAddress;
import com.seveneleven.mycontactapp.contact.model.PhoneNumber;
import com.seveneleven.mycontactapp.contact.observer.ContactLogger;
import com.seveneleven.mycontactapp.contact.search.ContactSearchService;
import com.seveneleven.mycontactapp.contact.search.EmailCriteria;
import com.seveneleven.mycontactapp.contact.search.NameCriteria;
import com.seveneleven.mycontactapp.contact.search.PhoneCriteria;
import com.seveneleven.mycontactapp.contact.search.SearchCriteria;
import com.seveneleven.mycontactapp.contact.service.ContactManager;
import com.seveneleven.mycontactapp.user.builder.*;
import com.seveneleven.mycontactapp.user.factory.*;

public class Main {

    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        /* ----------- REGISTER ----------- */

        System.out.println("=== USER REGISTRATION ===");

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        String hashedPassword = ValidationUtil.hashPassword(password);

        UserBuilder builder = new UserBuilder()
                .setName(name)
                .setEmail(email)
                .setPassword(hashedPassword);

        User user = UserFactory.createUser("FREE", builder);

        users.put(user.getEmail(), user);

        System.out.println("Registration Successful!\n");


        /* ----------- LOGIN ----------- */

        System.out.println("=== LOGIN ===");

        System.out.print("Enter email: ");
        String loginEmail = sc.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = sc.nextLine();

        AuthenticationStrategy auth = new BasicAuth(users);

        Optional<User> loggedInUser =
                auth.authenticate(loginEmail, loginPassword);

        if (!loggedInUser.isPresent()) {
            System.out.println("Invalid Credentials");
            return;
        }

        User loggedUser = loggedInUser.get();
        List<Contact> contacts = loggedUser.getContacts();

        System.out.println("Login Successful: " + loggedUser.getName());


        /* ----------- MAIN MENU ----------- */

        boolean running = true;

        while (running) {

            System.out.println("\n===== CONTACT MENU =====");
            System.out.println("1 Add Contact");
            System.out.println("2 View Contacts");
            System.out.println("3 Edit Contact");
            System.out.println("4 Delete Contact");
            System.out.println("5 Bulk Delete (name starts with)");
            
            System.out.println("6 Search Contacts");
            System.out.println("7 Profile Settings");
            System.out.println("8 Logout");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();


            switch (choice) {

                /* ----------- ADD CONTACT ----------- */

                case 1:

                    if (loggedUser instanceof FreeUser &&
                            contacts.size() >= 5) {

                        System.out.println("Free user limit reached.");
                        break;
                    }

                    System.out.print("Enter contact type (PERSON / ORG): ");
                    String type = sc.nextLine();

                    System.out.print("Enter contact name: ");
                    String contactName = sc.nextLine();

                    ContactBuilder contactBuilder =
                            new ContactBuilder().setName(contactName);
                    
                    /* ----------- PHONE NUMBERS ----------- */

                    System.out.print("How many phone numbers to add? ");
                    int phoneCount = sc.nextInt();
                    sc.nextLine();

                    for (int i = 1; i <= phoneCount; i++) {

                        System.out.print("Enter phone number " + i + ": ");
                        String phone = sc.nextLine();

                        contactBuilder.addPhone(phone);
                    }


                    /* ----------- EMAIL ADDRESSES ----------- */

                    System.out.print("How many emails to add? ");
                    int emailCount = sc.nextInt();
                    sc.nextLine();

                    for (int i = 1; i <= emailCount; i++) {

                        System.out.print("Enter email " + i + ": ");
                        String emailInput = sc.nextLine();

                        contactBuilder.addEmail(emailInput);
                    }
                   

                    Contact contact =
                            ContactFactory.createContact(type, contactBuilder);

                    contacts.add(contact);

                    System.out.println("Contact added successfully.");
                    break;


                /* ----------- VIEW CONTACTS ----------- */

                case 2:

                    if (contacts.isEmpty()) {
                        System.out.println("No contacts available.");
                        break;
                    }

                    for (int i = 0; i < contacts.size(); i++) {
                        System.out.println("\nContact " + (i+1));
                        System.out.println(contacts.get(i));
                    }

                    break;
                /* ----------- EDIT CONTACT ----------- */

                case 3:


                    if (contacts.isEmpty()) {
                        System.out.println("No contacts to edit.");
                        break;
                    }

                    for (int i = 0; i < contacts.size(); i++) {
                        System.out.println((i+1) + ". " + contacts.get(i).getName());
                    }

                    System.out.print("Select contact: ");
                    int editIndex = sc.nextInt();
                    sc.nextLine();

                    Contact editContact = contacts.get(editIndex - 1);

                    System.out.println("1 Change Name");
                    System.out.println("2 Add Phone");
                    System.out.println("3 Add Email");

                    System.out.print("Choose option: ");
                    int editChoice = sc.nextInt();
                    sc.nextLine();

                    switch(editChoice){

                        case 1:

                            System.out.print("Enter new name: ");
                            String newName = sc.nextLine();

                            editContact.setName(newName);
                            break;

                        case 2:

                            System.out.print("Enter new phone: ");
                            String newPhone = sc.nextLine();

                            editContact.getPhoneNumbers().add(
                                    new PhoneNumber(newPhone));

                            break;

                        case 3:

                            System.out.print("Enter new email: ");
                            String newEmail = sc.nextLine();

                            editContact.getEmailAddresses().add(
                                    new EmailAddress(newEmail));

                            break;
                    }

                    System.out.println("Contact updated.");
                    break;


                /* ----------- DELETE CONTACT ----------- */

                case 4:

                    if (contacts.isEmpty()) {
                        System.out.println("No contacts to delete.");
                        break;
                    }

                    for (int i = 0; i < contacts.size(); i++) {

                        System.out.println((i+1) + ". " +
                                contacts.get(i).getName());
                    }

                    System.out.print("Select contact to delete: ");
                    int deleteIndex = sc.nextInt();
                    sc.nextLine();

                    Contact selected =
                            contacts.get(deleteIndex - 1);

                    System.out.print("Confirm delete (yes/no): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("yes")) {

                        ContactManager manager =
                                new ContactManager(contacts);

                        manager.addObserver(new ContactLogger());

                        manager.deleteContact(selected);
                    }

                    break;


                /* ----------- BULK DELETE ----------- */

                case 5:

                    System.out.print(
                      "Delete contacts starting with letter: ");

                    String prefix = sc.nextLine();

                    contacts.removeIf(
                            c -> c.getName().startsWith(prefix)
                    );

                    System.out.println("Bulk delete completed.");
                    break;


                /* ----------- LOGOUT ----------- */

                case 6:

                    if (contacts.isEmpty()) {
                        System.out.println("No contacts available.");
                        break;
                    }

                    System.out.println("Search by:");
                    System.out.println("1 Name");
                    System.out.println("2 Phone");
                    System.out.println("3 Email");

                    int searchType = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter search value: ");
                    String value = sc.nextLine();

                    SearchCriteria criteria = null;

                    if(searchType == 1)
                        criteria = new NameCriteria(value);

                    else if(searchType == 2)
                        criteria = new PhoneCriteria(value);

                    else if(searchType == 3)
                        criteria = new EmailCriteria(value);

                    List<Contact> results =
                            ContactSearchService.search(contacts, criteria);

                    if(results.isEmpty()){
                        System.out.println("No contacts found.");
                    }
                    else{
                        results.forEach(System.out::println);
                    }

                    break;
               

                case 7:
                    running = false;
                    System.out.println("Logged out.");
                    break;


                default:
                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}
