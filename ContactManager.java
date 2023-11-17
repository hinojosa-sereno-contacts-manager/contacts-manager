import org.w3c.dom.ls.LSOutput;
import utils.Input;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class ContactManager {
    private final int CHOICE_EXIT = 5;

    private Input input = new Input();

    Path pathToContactsFile = Paths.get("data/contacts.txt");

//    public ContactManager() {
//    }

    public void writeListToFile(Path pathToFile, List<String> listToWrite) {
        try {
            Files.write(pathToFile, listToWrite);
        } catch (IOException iox) {
            iox.printStackTrace();
            System.out.println(iox.getMessage());
        }
    }

    public List<String> readFile(Path pathToFile) {
        List<String> linesInFile = new ArrayList<>();
        try {
            linesInFile = Files.readAllLines(pathToFile);
        } catch (IOException iox) {
            iox.printStackTrace();
        }
        return linesInFile;
    }

    public Contact stringToContact(String contactString) {
        String name = contactString.split("\\|")[0].trim();
        String number = contactString.split("\\|")[1].trim();
        Contact contact = new Contact(name, number);
        return contact;
    }

    public String contactToString(Contact contact) {
        return contact.getName() + " | " + contact.getPhone();
    }

    public List<Contact> loadContactsList(Path pathToContactsFile) {
        List<Contact> contactsOnFile = new ArrayList<>();
        try {
            List<String> contactsList = Files.readAllLines(pathToContactsFile);
            for (String contactString : contactsList) {
                contactsOnFile.add(stringToContact(contactString));
            }
        } catch (IOException iox) {
            iox.printStackTrace();
        }
        return contactsOnFile;
    }

    public void outputList(List<String> list) {
        for (String listItem : list) {
            System.out.println(listItem);
        }
    }

    public static void main(String[] args) {
        ContactManager app = new ContactManager();
        app.start();
    }

    public void start() {


        while (true) {
            printMenu();

            int choice = input.getInt(1, CHOICE_EXIT, "Make a choice: ");

            if (choice == CHOICE_EXIT) {
                break;
            }
            doChoice(choice);
        }
    }


    private void doChoice(int choice) {
        switch (choice) {
            case 1 -> viewContactsOOP();
            case 2 -> addContacts();
            case 3 -> searchContact();
            case 4 -> deleteContact();
            default -> System.out.println("Invalid menu choice: " + choice);
        }
    }

    private void printMenu() {
        System.out.println("""
                1. View contacts.
                2. Add a new contact.
                3. Search a contact by name.
                4. Delete an existing contact.
                5. Exit.
                Enter an option (1, 2, 3, 4 or 5):
                """);
    }


    private void searchContact() {
        Input nameSearch = new Input();
        String getName = nameSearch.getString("Search contact by name: ");
        List<String> contactsList = readFile(pathToContactsFile);
        for (String contact : contactsList) {
            String name = contact.split("\\|")[0].trim();
            if (name.equalsIgnoreCase(getName)) {
                System.out.println(contact);
            }
        }
    }

    private void viewContacts() {
        List<String> printList = null;
        try {
            printList = Files.readAllLines(pathToContactsFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < printList.size(); i++) {
            System.out.println(printList.get(i));

        }
    }

    private void viewContactsOOP() {
        List<Contact> contacts = loadContactsList(pathToContactsFile);
        for (Contact contact : contacts) {
            System.out.println(contact.getName() + " | " + contact.getPhone());
        }
    }

    private void addContacts() {
        System.out.println("adding new contact");

        Input addContact = new Input();
        String getName = addContact.getString("Add new contact name: ");
        String getPhone = addContact.getString("Add new contact phone number: ");

        Path contactListPath = Paths.get("data", "contacts.txt");

        String together = getName + " | " + getPhone;


        List<String> moreContacts = Arrays.asList(together);

        // We use Files.write, but with the APPEND option
        try {
            Files.write(contactListPath, moreContacts, StandardOpenOption.APPEND);
        } catch (IOException iox) {
            System.out.println("Error writing to file " + iox.getMessage());
        }

//        System.out.println("-----------------------");
        outputList(readFile(contactListPath));

    }

    private void deleteContact() {
        Input nameSearch = new Input();
        String getName = nameSearch.getString("Delete contact by name: ");

        List<Contact> current = loadContactsList(pathToContactsFile);
        current.removeIf(contact -> contact.getName().equalsIgnoreCase(getName));

        writeListToFile(pathToContactsFile, current.stream()
                .map(this::contactToString)
                .collect(Collectors.toList()));

        System.out.println("-------------------------");
        outputList(current.stream()
                .map(this::contactToString)
                .collect(Collectors.toList()));
    }
}

//    List<String> contactsList = readFile(pathToContactsFile);
//        for (String contact : contactsList) {
//        String name = contact.split("\\|")[0].trim();
//        if (name.equalsIgnoreCase(getName)) {
//            System.out.println(contact);
//        }

//    private void deleteContact() {
//        Input nameSearch = new Input();
//        String getName = nameSearch.getString("Delete contact by name: ");
////        List<String> contactsList = readFile(pathToContactsFile);
//        Iterator<String> contactsList = readFile(pathToContactsFile).iterator();
//        String contactDelete = getName;
//
////        for (String contact : contactsList) {
////            String name = contact.split("\\|")[0].trim();
////            if (name.equalsIgnoreCase(getName)) {
//                contactsList.removeIf(newDelete -> newDelete.equalsIgnoreCase(getName));
////                System.out.println("$$$L ";
//
//            writeListToFile(pathToContactsFile, contactsList);
//            System.out.println("-------------------------");
//            outputList(contactsList);
//    }
//public void deleteContact() {
//    Input nameSearch = new Input();
//        String getName = nameSearch.getString("Delete contact by name: ");
//        List<String> contactsList = readFile(pathToContactsFile);
////        Iterator<String> contactsList = readFile(pathToContactsFile).iterator();
//        String contactDelete = getName;
//    contactsList.removeIf(contact -> contact.trim().equalsIgnoreCase(getName.trim()));
//}

//    private void deleteContact(){
//        Input nameSearch = new Input();
//        String getName = nameSearch.getString("Delete contact by name: ");
//        List<String> current = readFile(pathToContactsFile);
//        String contactDelete = getName;
//        current.removeIf(newDelete -> newDelete.equals(contactDelete));
//        writeListToFile(pathToContactsFile, current);
//        System.out.println("-------------------------");
//        outputList(current);
//    }




//
//    public void deleteContact() {
//        Input deleteContact = new Input();
//        List<String> currentListOfContacts = readFile(pathToContactsFile);
//        System.out.println("Current list of contacts: " + currentListOfContacts);
//
//        String deleteName = deleteContact.getString("Delete contact by name: ");
//
//        // Check if the contact name exists in the list
//        boolean contactExists = currentListOfContacts.stream().anyMatch(contact -> contact.equalsIgnoreCase(deleteName));
//        System.out.println("Contact exists: " + contactExists);
//
//        if (!contactExists) {
//            System.out.println("Contact not found.");
//            return;
//        }
//
//        // Remove the contact from the list
//        currentListOfContacts.removeIf(contact -> contact.equalsIgnoreCase(deleteName));
//        System.out.println("Updated list of contacts: " + currentListOfContacts);
//
//        // Write the updated list back to the file
//        writeListToFile(pathToContactsFile, currentListOfContacts);
//
//        System.out.println("-------------------------");
//        outputList(currentListOfContacts);
//    }



