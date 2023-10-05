package com.example.kafka3.InputClass;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;
    public InputHandler() {
       scanner = new Scanner(System.in);
    }

    public String getStringInput() {
        return scanner.nextLine();
    }

    public Long getLongInput() {
        Long input = 0L;
        boolean validInput = true;
        while (validInput) {
            try {
                input = scanner.nextLong();
                validInput = false;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid option.33");
                scanner.nextLine(); // Consume the newline character
            }
        }
        return input;
    }

    public int getIntInput() {
        int input = 0;
        boolean validInput = true;
        while (validInput) {
            try {
                input = scanner.nextInt();
                validInput = false;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine(); // Consume the newline character
            }
        }
        return input;
    }

    public void closeScanner() {
        scanner.close();
    }

}
