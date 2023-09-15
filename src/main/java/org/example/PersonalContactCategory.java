package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

            while (fileScanner.hasNextLine()) { // while loop which will keep reading file until there is no next line
                String input = fileScanner.nextLine();  // stores the current line in a string called input
                String[] personalContactDetails = input.split(Pattern.quote(","));  // splits input by each "," and stores each part in an array called studentDetails
                String name = personalContactDetails[0];    // index 0 of the array contain name which is assigned to String name variable
                String email = personalContactDetails[1];  // index 1 of the array contain date as a String which is parsed to LocalDate dateOfBirth variable using the dateFormatter
                String phoneNumber = personalContactDetails[2];   // index 2 of the array contain the course name which is assigned to String course variable
                personalContacts.add(new PersonalContact(name, email, phoneNumber));   // adds a new entry to the array list which is a new object of the Student class. Calls the Student Constructor class in which Input variable are set for each variable
            }
            fis.close();    // closes the file input stream to break link (performance and efficiency related)
            // catch blocks to catch thrown exceptions which are necessary when using the file scanner
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
//find item in array and print details
        //or say not found if name does not exist.
    }




}
