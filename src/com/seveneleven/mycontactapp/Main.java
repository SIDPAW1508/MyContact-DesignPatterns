
package com.seveneleven.mycontactapp;

import java.util.*;

import com.seveneleven.mycontactapp.user.model.*;
import com.seveneleven.mycontactapp.user.validation.*;
import com.seveneleven.mycontactapp.contact.builder.ContactBuilder;
import com.seveneleven.mycontactapp.contact.factory.ContactFactory;
import com.seveneleven.mycontactapp.contact.model.Contact;
import com.seveneleven.mycontactapp.contact.observer.ContactLogger;
import com.seveneleven.mycontactapp.contact.service.ContactManager;
import com.seveneleven.mycontactapp.user.builder.*;
import com.seveneleven.mycontactapp.user.command.*;
import com.seveneleven.mycontactapp.user.factory.*;
import com.seveneleven.mycontactapp.contact.display.*;

public class Main {

	private static Map<String, User> users = new HashMap<>();
	private static List<Contact> contacts = new ArrayList<>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		/* ---------------- UC-01 REGISTRATION ---------------- */

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


		/* ---------------- UC-02 LOGIN ---------------- */

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

		System.out.println("Login Successful: " + loggedUser.getName());


		boolean managingProfile = true;
		ProfileManager manager = new ProfileManager();

		while (managingProfile) {

			System.out.println("\n=== PROFILE MANAGEMENT ===");
			System.out.println("1. Update Name");
			System.out.println("2. Change Password");
			System.out.println("3. Continue");

			System.out.print("Choose option: ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {

			case 1:
				System.out.print("Enter new name: ");
				String newName = sc.nextLine();

				ProfileCommand nameCmd =
						new UpdateNameCommand(loggedUser, newName);

				manager.executeCommand(nameCmd);
				break;

			case 2:
				System.out.print("Enter old password: ");
				String oldPass = sc.nextLine();

				System.out.print("Enter new password: ");
				String newPass = sc.nextLine();

				ProfileCommand passCmd =
						new ChangePasswordCommand(loggedUser, oldPass, newPass);

				manager.executeCommand(passCmd);
				break;

			case 3:
				managingProfile = false;
				break;

			default:
				System.out.println("Invalid option.");
			}
		}
		/* ---------------- UC-04 CREATE CONTACT ---------------- */

		System.out.println("\n=== CREATE CONTACT ===");

		System.out.print("Enter contact type (PERSON / ORG): ");
		String type = sc.nextLine();

		System.out.print("Enter contact name: ");
		String contactName = sc.nextLine();

		ContactBuilder contactBuilder = new ContactBuilder()
				.setName(contactName);

		/* ---- Phone numbers ---- */
		while (true) {
			System.out.print("Enter phone number (or type 'done'): ");
			String phone = sc.nextLine();

			if (phone.equalsIgnoreCase("done")) {
				break;
			}

			contactBuilder.addPhone(phone);
		}

		/* ---- Emails ---- */
		while (true) {
			System.out.print("Enter email (or type 'done'): ");
			String emailInput = sc.nextLine();

			if (emailInput.equalsIgnoreCase("done")) {
				break;
			}

			contactBuilder.addEmail(emailInput);
		}

		/* ---- Create Contact ---- */
		Contact contact = ContactFactory.createContact(type, contactBuilder);


		contacts.add(contact);

		System.out.println("Contact Created Successfully!");
		System.out.println("Contact ID: " + contact.getId());
		System.out.println("Name: " + contact.getName());
		System.out.println("Type: " + contact.getType());

		/* ---------------- UC-05 VIEW CONTACT ---------------- */

		System.out.println("\n=== VIEW CONTACT ===");

		if (contacts.isEmpty()) {
			System.out.println("No contacts available.");
		} else {

			System.out.println("Available Contacts:");

			for (int i = 0; i < contacts.size(); i++) {
				System.out.println((i + 1) + ". " + contacts.get(i).getName());
			}

			System.out.print("Select contact number: ");
			int index = sc.nextInt();
			sc.nextLine();

			Optional<Contact> selected =
					Optional.ofNullable(contacts.get(index - 1));

			if (selected.isPresent()) {

				ContactDisplay display =
						new FancyDisplayDecorator(
								new BasicContactDisplay(selected.get())
								);

				System.out.println(display.display());

			} else {
				System.out.println("Contact not found.");
			}
		}
		System.out.println("\n=== DELETE CONTACT ===");

		if (contacts.isEmpty()) {
			System.out.println("No contacts available.");
		}
		else {

			for (int i = 0; i < contacts.size(); i++) {
				System.out.println((i+1) + ". " + contacts.get(i).getName());
			}

			System.out.print("Select contact number to delete: ");
			int index = sc.nextInt();
			sc.nextLine();

			Contact selected = contacts.get(index - 1);

			System.out.print("Are you sure? (yes/no): ");
			String confirm = sc.nextLine();

			if(confirm.equalsIgnoreCase("yes")) {

				ContactManager manager1 = new ContactManager(contacts);

				manager1.addObserver(new ContactLogger());

				manager1.deleteContact(selected);

				System.out.println("Contact deleted successfully.");

			}
			else {
				System.out.println("Deletion cancelled.");
			}
		}
	}
}

