
package com.seveneleven.mycontactapp;

import java.util.*;

import com.seveneleven.mycontactapp.user.model.*;
import com.seveneleven.mycontactapp.user.validation.*;
import com.seveneleven.mycontactapp.user.builder.*;
import com.seveneleven.mycontactapp.user.command.*;
import com.seveneleven.mycontactapp.user.factory.*;

public class Main {

    private static Map<String, User> users = new HashMap<>();

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


        /* ---------------- UC-03 PROFILE MANAGEMENT ---------------- */

        ProfileManager manager = new ProfileManager();

        while (true) {

            System.out.println("\n=== PROFILE MANAGEMENT ===");
            System.out.println("1. Update Name");
            System.out.println("2. Change Password");
            System.out.println("3. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

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
                    System.out.println("Exiting profile management.");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

