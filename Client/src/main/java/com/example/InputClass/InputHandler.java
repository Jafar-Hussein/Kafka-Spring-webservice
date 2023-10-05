package com.example.InputClass;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;
    public InputHandler() {
       scanner = new Scanner(System.in);
    }

    public String getStringInput() { //returnerar string input
        return scanner.nextLine();
    }

    public Long getLongInput() { // denna metod används för att få in long input och kastar exception om det inte är en nummer
        Long input = 0L;
        boolean validInput = true; //används för att kolla om input är valid
        while (validInput) { //loopar tills validInput är false
            try {
                input = scanner.nextLong();
                validInput = false;
            } catch (Exception e) { //om input inte är en nummer så kastas exception och användaren får försöka igen
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine();
            }
        }
        return input;
    }

    public int getIntInput() { // denna metod används för att få in int input och kastar exception om det inte är en nummer
        //lika som getLongInput
        int input = 0;
        boolean validInput = true;
        while (validInput) {
            try {
                input = scanner.nextInt();
                validInput = false;
            } catch (Exception e) { //om input inte är en nummer så kastas exception och användaren får försöka igen
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine(); // Consume the newline character
            }
        }
        return input;
    }

    public void closeScanner() { //stänger scanner för att undvika resource leak
        scanner.close();
    }

}
