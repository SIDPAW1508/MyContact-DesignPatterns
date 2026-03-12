package com.seveneleven.mycontactapp;

import java.util.Scanner;

import com.seveneleven.mycontactapp.user.builder.UserBuilder;
import com.seveneleven.mycontactapp.user.factory.UserFactory.UserType;
import com.seveneleven.mycontactapp.user.model.User;
import com.seveneleven.mycontactapp.exception.ValidationException;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            // User input
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            System.out.print("Enter User Type (FREE/PREMIUM): ");
            String typeInput = scanner.nextLine().toUpperCase();

            UserType userType = UserType.valueOf(typeInput);

            // Build user using Builder pattern
            User user = new UserBuilder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .type(userType)
                    .build();

            // Print user details
            printUserDetails(user);

        } catch (ValidationException e) {
            System.out.println("Validation error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user type. Please enter FREE or PREMIUM.");
        } finally {
            scanner.close();
        }
    }

    private static void printUserDetails(User user) {
        System.out.println("\nUser Details:");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Role: " + user.getRole());
    }
}