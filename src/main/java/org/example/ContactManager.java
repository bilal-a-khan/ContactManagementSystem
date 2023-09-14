package org.example;

import java.util.Scanner;

public class ContactManager {

    Scanner scanner = new Scanner(System.in);

    public ContactManager() {
        // read files and add contacts to Arrays.
    }


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
                case "p":
                    managePersonalContacts();
                    stopRepeat = true;
                    break;
                case "w":
                    manageWorkContacts();
                    stopRepeat = true;
                    break;
                case "q":
                    System.out.println("Closing CMS...");
                    System.exit(0);
                default:
                    System.out.println("invalid option selected. Please enter again.");
                    break;
            }

        } while (!stopRepeat);
    }

    private void manageWorkContacts() {
        System.out.println("managing work contacts");
    }

    private void managePersonalContacts() {
        System.out.println("managing personal contacts");
    }
}
