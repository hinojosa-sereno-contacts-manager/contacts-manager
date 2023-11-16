package utils;

import java.util.Scanner;

public class Input {
    private final Scanner scanner;
//    private String string;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String getString() {
        return scanner.nextLine();
    }

    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public boolean yesNo() {
        System.out.println("Do you like stuff?");
        String userInput = scanner.nextLine();
        return userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes");
    }

    public boolean yesNo(String prompt) {
        System.out.print(prompt);
        return yesNo();
    }

    public int getInt(String prompt) {
        System.out.print(prompt);
        return getInt();
    }

    public int getInt(int min, int max, String prompt) {
        System.out.print(prompt);
        int anInt = getInt();
        if(anInt < min || anInt > max) {
            System.out.printf("Hey! That was not between %d and %d!!%n", min, max);
            return getInt(min, max, prompt);
        }
        return anInt;
    }

    public int getInt() {
        int userInt = 0;
        String s = getString();
        try{
            userInt = Integer.valueOf(s);
//            userInt = Integer.parseInt(s);
        } catch(NumberFormatException e){
            System.out.println("Not an integer. Try again: ");
            userInt = getInt();
        }
        return userInt;
    }
    public double getDouble(double min, double max){
        double userDouble;
        do{
            System.out.printf("Enter a number between %f and %f: %n", min, max);
            userDouble = Double.parseDouble(scanner.nextLine());
        } while(userDouble > max || userDouble < min);
        return userDouble;
    }

    public double getDouble(){
        double userDub = 0;
        String s = getString();
        try {
            userDub = Double.valueOf(s);
//            userDub = Double.parseDouble(s);
        } catch(NumberFormatException e){
            System.out.println("Wrong double. Try again: ");
            userDub = getDouble();
        }
        return userDub;
    }


}
