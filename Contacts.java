import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Contacts {

    private String name;
    private String phone;

    //constructor


//    public Contacts() {
//        this.name = name;
//        this.phone = phone;
//    }

    public Contacts(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public static void main(String[] args) {


        //creating file and directory
        String directory = "data";
        String filename = "contacts.txt";

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

//        Path Files.write(Path , List<String> lines)

//        List<String> contactsList = Arrays.asList("John | 899790000");
//        Path filepath = Paths.get("data", "contacts.txt");
//        try {
//            Files.write(filepath, contactsList );
//        } catch (IOException iox) {
//            throw new RuntimeException(iox);
//        }


    }

}

