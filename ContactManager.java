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

    private ArrayList<String> listOfContacts;
    private Input input = new Input();

    public ContactManager() {
    }

    public void writeListToFile(Path pathToFile, List<String> listToWrite){
        try {
            Files.write(pathToFile, listToWrite);
        } catch (IOException iox) {
            iox.printStackTrace();
            System.out.println(iox.getMessage());
        }
    }

    public List<String> readFile(Path pathToFile){
        List<String> linesInFile = new ArrayList<>();
        try {
            linesInFile = Files.readAllLines(pathToFile);
        } catch (IOException iox){
            iox.printStackTrace();
        }
        return linesInFile;
    }


    public void outputList(List<String> list){
        for (String listItem : list){
            System.out.println(listItem);
        }
    }
    public static void main(String[] args) {
        ContactManager app = new ContactManager();
        app.start();
    }

    public void start() {
            initializer();

        while(true) {
            printMenu();

            int choice = input.getInt(1, CHOICE_EXIT, "Make a choice: ");

            if(choice == CHOICE_EXIT) {
                break;
            }

            doChoice(choice);

        }

        String contactInput = input.getString("> ");
//        printGitName(gitName);


        }




    private void doChoice(int choice) {
        switch (choice) {
            case 1 -> viewContacts();
            case 2 -> addContacts();
//            case 3 -> printClassAverage();
//            case 4 -> printCSV();
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
    public static Contacts searchContact(String name, List<Contacts> contactsList) {
        for (Contacts contact : contactsList) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    private void viewContacts(){

        for (Contacts contacts : listOfContacts)
            System.out.print(contacts);
    }

    private void addContacts(){
        System.out.println("adding new contact");

        Input addContact = new Input();
        String getName = addContact.getString("Add new contact name: ");
        String getPhone = addContact.getString("Add new contact phone number: ");

        Path contactListPath = Paths.get("data", "contacts.txt");
//        List<String> cList = null;
//        try {
//            cList = Files.readAllLines(contactListPath);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        for (int i = 0; i < cList.size(); i++) {
//            System.out.println((i + 1) + ": " + cList.get(i));
//        }
//
//        Files.write(
//                Paths.get("data", "contacts.txt"),
//                Arrays.asList(getName, getPhone),
//                StandardOpenOption.APPEND
//        );

        List<String> moreContacts = Arrays.asList(getName, getPhone);

        // We use Files.write, but with the APPEND option
        try {
            Files.write(contactListPath, moreContacts, StandardOpenOption.APPEND);
        } catch (IOException iox){
            System.out.println("Error writing to file " + iox.getMessage());
        }

//        System.out.println("-----------------------");
        outputList(readFile(contactListPath));

    }

    private void initializer(){
        String directory = "data";
        String filename = "contacts.txt";

        //create a list of contacts
        List<Contacts> contactsList = new ArrayList<>();
        contactsList.add(new Contacts("Joe", "2103334492"));
        contactsList.add(new Contacts("Bob", "3212313324"));
        contactsList.add(new Contacts("larry", "9827417524"));
//        System.out.println(contactsList);

        //path objects where the information is going to be stored
        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);
        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }
            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }
        } catch (IOException iox) {
            iox.getMessage();
        }

        //converts list of objects in to a list of string objects
        List<String> contactsStrings = contactsList.stream()
                .map(Contacts::toString)//applies toString() to each Contacts obj -> String obj
                .collect(Collectors.toList());//collects the String objs in a new list

        //Files.write expects data to be written in String form
        try {
            Files.write(dataFile, contactsStrings, Charset.defaultCharset());
        } catch (IOException iox) {
            iox.printStackTrace();
        }
//    }

//    private void viewContacts(){
//        List<Contacts> contactsList = new ArrayList<>();
//        for (Contacts contacts : contactsList)
//            System.out.print(contacts);

    }

    private void startApp(){

    }














}
