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
        if (phone.length() == 10){
        this.phone = phone;
        } else {
            this.phone = "";
        }
    }

    public String checkNull(String contactProperty){
        return contactProperty == null ? "" : contactProperty;
    }


//    public String display(){
//        return String.format(
//                "Name: %s%n" +
//                        "Mobile: %s%n" +
//                name, checkNull(phone), checkNull("")
//        );
//    }

    @Override
    public String toString() {
//        return "[" +
//                "name='" + name + '\'' +
//                ", phone='" + phone + '\'' +
//                ']';
        return name + " | " + phone +"\n";
    }

    public static void main(String[] args) {

        List<Contacts> person = new ArrayList<Contacts>();
        person.add(new Contacts("Joe", "2103334492"));
        person.add(new Contacts("Bob", "32123124"));
        person.add(new Contacts("larry", "98274124"));
//        System.out.println(person);

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

//        List<String> contactsList = Arrays.asList("matt | 899790000");
//        Path filepath = Paths.get("data", "contacts.txt");
//        Path pathToFile = Paths.get("data/contacts.txt");

        List<String> contactsList = Arrays.asList(person.toString());
        Path filepath = Paths.get("data", "contacts.txt");
        try {
            Files.write(filepath, contactsList);
        } catch (IOException iox) {
            throw new RuntimeException(iox);
        }

//                try {
////            Files.write(filepath, person);
//            Files.write(filepath, List<Contacts> person);
//                } catch (IOException iox) {
//            iox.printStackTrace();
//            System.out.println(iox.getMessage());
//        }

    }

}

