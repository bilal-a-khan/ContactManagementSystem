package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class PersonalContactCategory implements ContactCategory {

    ContactManager contactManager = new ContactManager();
    List<Contact> personalContacts = new ArrayList<>();

    public PersonalContactCategory() {
        updateListFromFile();
    }


    public void updateListFromFile() {
        personalContacts.clear();
        try {
            FileReader fileReader = new FileReader("src/main/resources/Personal.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String fileText;
            while ((fileText = bufferedReader.readLine()) != null) {
                String[] personalContactDetails = fileText.split(Pattern.quote(","));
                String name = personalContactDetails[0];
                String email = personalContactDetails[1];
                String phoneNumber = personalContactDetails[2];
                personalContacts.add(new Contact(name, email, phoneNumber));
            }
            bufferedReader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No such file exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void addContact(String name, String email, String phoneNumber) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/Personal.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(name + "," + email + "," + phoneNumber);

            bufferedWriter.close();

            System.out.println("New contact successfully added");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        contactManager.pressEnterToReturn();
        contactManager.managePersonalContacts();
    }

    @Override
    public void removeContact(String name) {
        boolean foundResults = false;
        for (Contact c : personalContacts) {
            if (c.getName().equals(name)) {
                foundResults = true;
                break;
            }
        }
        if (foundResults) {
            removeContactFromCSV(name);
            System.out.println("\"" + name + "\" was removed from the personal contact list");
        } else {
            System.out.println("No contact found");
        }
        contactManager.pressEnterToReturn();
        contactManager.managePersonalContacts();
    }

    private void removeContactFromCSV(String name) {
        File tempFile = new File("src/main/resources/tmpPersonal.csv");
        try {
            FileReader fileReader = new FileReader("src/main/resources/Personal.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String row;
            FileWriter fileWriter = new FileWriter("src/main/resources/tmpPersonal.csv");

            while (((row = bufferedReader.readLine()) != null)) {
                String[] line = row.split(",");
                if (!line[0].equals(name)) {
                    fileWriter.write(row + "\n");
                }
            }
            fileWriter.close();
            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File originalFile = new File("src/main/resources/Personal.csv");
        originalFile.delete();
        tempFile.renameTo(originalFile);
    }


    @Override
    public void viewContacts() {
        updateListFromFile();
        personalContacts.sort(Comparator.comparing(Contact::getName));
        for (Contact c : personalContacts) {
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
        for (Contact c : personalContacts) {
            if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                foundResults = true;
                System.out.println(c.getName() + " | " + c.getEmail() + " | " + c.getPhoneNumber());
            }
        }
        if (!foundResults) {
            System.out.println("No contacts found.");
        }
        contactManager.pressEnterToReturn();
        contactManager.managePersonalContacts();
    }


}
