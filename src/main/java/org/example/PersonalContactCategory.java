package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PersonalContactCategory implements ContactCategory {

    ContactManager contactManager = new ContactManager();
    List<Contact> personalContacts = new ArrayList<>();
    FileInputStream fis;    // defines FileInputSteam that points to input file
    Scanner fileScanner;    // defines Scanner that will read file

    public PersonalContactCategory() {
        updateListFromFile();
    }


    public void updateListFromFile() {
        personalContacts.clear();
        try {   // try block form attempting to extract data from input file
            fis = new FileInputStream("src/main/resources/Personal.csv");   // points to input file
            fileScanner = new Scanner(fis); // defines Scanner that will read file

            while (fileScanner.hasNextLine()) {
                String input = fileScanner.nextLine();
                String[] personalContactDetails = input.split(Pattern.quote(","));
                String name = personalContactDetails[0];
                String email = personalContactDetails[1];
                String phoneNumber = personalContactDetails[2];
                personalContacts.add(new Contact(name, email, phoneNumber));
            }
            fis.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No such file exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void addContact(String name, String email, String phoneNumber) {
        //take input details. add contact to hashmap. add to file too.

    }

    @Override
    public void removeContact(String name) {
// find item in list and remove
    }

    @Override
    public void viewContacts() {
        updateListFromFile();
        for (Contact c:personalContacts) {
            System.out.println(c.getName() + " | " + c.getEmail() + " | " + c.getPhoneNumber());
        }
        contactManager.pressEnterToReturn();
        contactManager.managePersonalContacts();
    }

    @Override
    public void searchContacts(String name) {
        updateListFromFile();
        boolean foundResults = false;
        System.out.println("Searching for contact names containing \"" + name + "\" ...");
        for (Contact c:personalContacts){
            if (c.getName().toLowerCase().contains(name)){
                foundResults = true;
                System.out.println(c.getName() + " | " + c.getEmail() + " | " + c.getPhoneNumber());
            }
        }
        if (!foundResults){
            System.out.println("No contacts found.");
        }
        contactManager.pressEnterToReturn();
        contactManager.managePersonalContacts();
    }




}
