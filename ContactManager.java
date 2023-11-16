import utils.Input;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContactManager {

//    private Map<String, String> contact;
    private Input input = new Input();

    public ContactManager() {
    }

    public static void main(String[] args) {
        ContactManager app = new ContactManager();
        app.start();
    }

    public void start() {

        printMenu();

        String contactInput = input.getString("> ");
//        printGitName(gitName);


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


    private void startApp(){

    }














}
