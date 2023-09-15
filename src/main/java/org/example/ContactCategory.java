package org.example;

public interface ContactCategory {

    public void addContact(String name, String email, String phoneNumber);


    public void removeContact(String name);

    public void viewContacts();

    public void searchContacts(String name);

}
