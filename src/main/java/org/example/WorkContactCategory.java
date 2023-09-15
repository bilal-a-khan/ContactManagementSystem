package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class WorkContactCategory implements ContactCategory {

    ContactManager contactManager = new ContactManager();
    List<Contact> workContacts = new ArrayList<>();

    public WorkContactCategory() {
        updateListFromFile();
    }


    public void updateListFromFile() {
        workContacts.clear();
        try {
            FileReader fileReader = new FileReader("src/main/resources/Work.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String fileText;
            while ((fileText = bufferedReader.readLine()) != null) {
                String[] workContactDetails = fileText.split(Pattern.quote(","));
                String name = workContactDetails[0];
                String email = workContactDetails[1];
                String phoneNumber = workContactDetails[2];
                workContacts.add(new Contact(name, email, phoneNumber));
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
            FileWriter fileWriter = new FileWriter("src/main/resources/Work.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(name + "," + email + "," + phoneNumber);

            bufferedWriter.close();

            System.out.println("New contact successfully added");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        contactManager.pressEnterToReturn();
        contactManager.manageWorkContacts();
    }

    @Override
    public void removeContact(String name) {
        boolean foundResults = false;
        for (Contact c : workContacts) {
            if (c.getName().equals(name)) {
                foundResults = true;
                break;
            }
        }
        if (foundResults) {
            removeContactFromCSV(name);
            System.out.println("\"" + name + "\" was removed from the work contact list");
        } else {
            System.out.println("No contact found");
        }
        contactManager.pressEnterToReturn();
        contactManager.manageWorkContacts();
    }

    private void removeContactFromCSV(String name) {
        File tempFile = new File("src/main/resources/tmpWork.csv");
        try {
            FileReader fileReader = new FileReader("src/main/resources/Work.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String row;
            FileWriter fileWriter = new FileWriter("src/main/resources/tmpWork.csv");

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

        File originalFile = new File("src/main/resources/Work.csv");
        originalFile.delete();
        tempFile.renameTo(originalFile);
    }


    @Override
    public void viewContacts() {
        updateListFromFile();
        workContacts.sort(Comparator.comparing(Contact::getName));
        for (Contact c : workContacts) {
            System.out.println(c.getName() + " | " + c.getEmail() + " | " + c.getPhoneNumber());
        }
        contactManager.pressEnterToReturn();
        contactManager.manageWorkContacts();
    }

    @Override
    public void searchContacts(String name) {
        updateListFromFile();
        boolean foundResults = false;
        System.out.println("Searching for contact names containing \"" + name + "\" ...");
        for (Contact c : workContacts) {
            if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                foundResults = true;
                System.out.println(c.getName() + " | " + c.getEmail() + " | " + c.getPhoneNumber());
            }
        }
        if (!foundResults) {
            System.out.println("No contacts found.");
        }
        contactManager.pressEnterToReturn();
        contactManager.manageWorkContacts();
    }


}
