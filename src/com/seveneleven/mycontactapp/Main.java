package com.seveneleven.mycontactapp;

import java.util.*;

import com.seveneleven.mycontactapp.user.model.*;
import com.seveneleven.mycontactapp.user.validation.*;
import com.seveneleven.mycontactapp.user.builder.*;
import com.seveneleven.mycontactapp.user.factory.*;

public class Main {

    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        /* ---------------- UC-01 REGISTRATION ---------------- */

        System.out.println("=== USER REGISTRATION ===");

        System.out.println("Enter name:");
        String name = sc.nextLine();

        System.out.println("Enter email:");
        String email = sc.nextLine();

        System.out.println("Enter password:");
        String password = sc.nextLine();

        String hashedPassword = ValidationUtil.hashPassword(password);

        // Builder Pattern
        UserBuilder builder = new UserBuilder()
                .setName(name)
                .setEmail(email)
                .setPassword(hashedPassword);

        // Factory Pattern
        User user = UserFactory.createUser("FREE", builder);

        // Store user
        users.put(user.getEmail(), user);

        System.out.println("Registration Successful!\n");


        /* ---------------- UC-02 AUTHENTICATION ---------------- */

        System.out.println("=== LOGIN ===");

        System.out.println("Enter email:");
        String loginEmail = sc.nextLine();

        System.out.println("Enter password:");
        String loginPassword = sc.nextLine();

        AuthenticationStrategy auth = new BasicAuth(users);

        Optional<User> loggedInUser =
                auth.authenticate(loginEmail, loginPassword);

        if (loggedInUser.isPresent()) {
            System.out.println("Login Successful: " +
                    loggedInUser.get().getName());
        } else {
            System.out.println("Invalid Credentials");
        }
    }
}