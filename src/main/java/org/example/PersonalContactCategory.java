package org.example;

public class PersonalContactCategory implements ContactCategory {

    @Override
    public void addContact() {
        System.out.println("adding contact");
    }

    @Override
    public void removeContact() {
        System.out.println("adding contact");
    }

    @Override
    public void viewContacts() {
        System.out.println("listing contact");
    }

    @Override
    public void searchContacts() {
        System.out.println("searching contact");
    }
}
