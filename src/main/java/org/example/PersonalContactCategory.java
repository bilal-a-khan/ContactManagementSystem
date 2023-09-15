package org.example;

public class PersonalContactCategory implements ContactCategory {


    public PersonalContactCategory() {
        // read from file and create the hashmap

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
// call the constructor
        // list out all names
    }

    @Override
    public void searchContacts(String name) {
//find item in array and print details
        //or say not found if name does not exist.
    }
}
