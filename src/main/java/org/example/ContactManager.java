package org.example;

import java.util.List;
import java.util.Scanner;

public class ContactManager {

    Scanner scanner = new Scanner(System.in);


    public void selectCategory() {
        boolean stopRepeat = false;
        System.out.println("""
                Please enter a key to select which category of contacts to you would like to manage.
                p - manage personal contacts
                w - manage work contacts
                q - quit the app""");
        do {
            String userInput = scanner.nextLine();
            switch (userInput.toLowerCase()) {
                case "p" -> {
                    managePersonalContacts();
                    stopRepeat = true;
                }
                case "w" -> {
                    System.out.println("Managing work contacts.");
                    stopRepeat = true;
                }
                case "q" -> {
                    System.out.println("Closing CMS...");
                    System.exit(0);
                }
                default -> System.out.println("invalid option selected. Please enter again.");
            }

        } while (!stopRepeat);
    }


    public void managePersonalContacts() {
        PersonalContactCategory personalContactCategory = new PersonalContactCategory();

        boolean stopRepeat = false;
        System.out.println("""
                *Managing Personal Contacts*
                Please enter a key to select what you would like to do.
                a - add a contact
                r - remove a contact
                v - view all contacts
                s - search for a contact
                q - return to category selection""");
        do {
            String userInput = scanner.nextLine();
            switch (userInput.toLowerCase()) {
                case "a" -> {
                    System.out.println("Please enter you new contacts full name");
                    String name = scanner.nextLine();
                    System.out.println("Please enter you new contacts email address");
                    String email = scanner.nextLine();
                    System.out.println("Please enter you new contacts phone number");
                    String phoneNumber = scanner.nextLine();
                    personalContactCategory.addContact(name, email, phoneNumber);
                    stopRepeat = true;
                }
                case "r" -> {
                    System.out.println("Please enter the full name (case-sensitive) of the contact you would like to remove.");
                    userInput = scanner.nextLine();
                    personalContactCategory.removeContact(userInput);
                    stopRepeat = true;
                }
                case "v" -> {
                    personalContactCategory.viewContacts();
                    stopRepeat = true;
                }
                case "s" -> {
                    System.out.println("Please enter the name you would like to search");
                    userInput = scanner.nextLine();
                    personalContactCategory.searchContacts(userInput);
                    stopRepeat = true;
                }
                case "q" -> {
                    System.out.println("Return to category selection...");
                    selectCategory();
                    stopRepeat = true;
                }
                default -> System.out.println("invalid option selected. Please enter again.");
            }
        } while (!stopRepeat);
    }

    public void pressEnterToReturn(){
        System.out.println("\nPress enter to continue...");
        scanner.nextLine();
    }
}

