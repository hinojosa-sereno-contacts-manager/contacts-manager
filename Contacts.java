import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import utils.Input;

public class Contacts {

    private String name;
    private String phone;


    //constructor
    public Contacts(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.length() != 10){
            throw new IllegalArgumentException("Phone number requires 10 digits");
        }
        this.phone = phone;
    }

    public String checkNull(String contactProperty){
        return contactProperty == null ? "" : contactProperty;
    }

    public static Contacts searchContact(String name, List<Contacts> contactsList) {
        for (Contacts contact : contactsList) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }



    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
//@Override
//public String toString() {
//    return name + " | " + phone +'\n';
//}


    public static void main(String[] args) throws IOException {
        //////INITIALIZER//////INITIALIZER//////INITIALIZER
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
        //////INITIALIZER//////INITIALIZER//////INITIALIZER

        System.out.println("""
                1. View contacts.
                2. Add a new contact.
                3. Search a contact by name.
                4. Delete an existing contact.
                5. Exit.
                Enter an option (1, 2, 3, 4 or 5):
                """);
//        int numberPick =
        Input enterOption = new Input();
        int getNumbOption = enterOption.getInt();
        if(getNumbOption == 1){
            // Printing and display the elements in ArrayList
            for (Contacts contacts : contactsList)
                System.out.print(contacts);
        } else if (getNumbOption == 2){
            System.out.println("adding new contact");

            Input addContact = new Input();
            String getName = addContact.getString("Add new contact name: ");
            String getPhone = addContact.getString("Add new contact phone number: ");

            Path contactListPath = Paths.get("data", "contacts.txt");
            List<String> cList = null;
            try {
                cList = Files.readAllLines(contactListPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < cList.size(); i++) {
                System.out.println(cList.get(i));
            }

            Files.write(
                    Paths.get("data", "contacts.txt"),
                    Arrays.asList(getName, getPhone),
                    StandardOpenOption.APPEND
            );



        } else if (getNumbOption == 3){
            //SEARCH BY NAME
            //tested. probably move from main to a test class. Look up a contact by name//
            Input nameSearch = new Input();
            String getName = nameSearch.getString("Search contact by name: ");
            Contacts foundContact = searchContact(getName, contactsList);
            if (foundContact != null) {
                System.out.println("Found contact: " + foundContact);
            } else {
                System.out.println("Contact not found");
            }
        } else if (getNumbOption == 4){
            System.out.println("deleting contact");
        } else if (getNumbOption == 5){
            System.out.println("BYE!");
        }





















    }
}







//        ///MADE THE ARRAY LIST IN TO A HASH MAP//
//        // Create a map of contacts
//        Map<String, Contacts> contactsMap = new HashMap<>();
//        for (Contacts contact : contactsList) {
//            contactsMap.put(contact.getName(), contact);
//        }
//
//        // Look up a contact by name
//        String nameToSearch = "Joe";
//        Contacts foundContact = contactsMap.get(nameToSearch);
//        if (foundContact != null) {
//            System.out.println("Found contact: " + foundContact);
//        } else {
//            System.out.println("Contact not found");
//        }
//}

