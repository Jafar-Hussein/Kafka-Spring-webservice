package com.example.menu;



import com.example.InputClass.InputHandler;
import com.example.kafkaconsumer.ClientConsumer;
import org.json.simple.JSONObject;

import java.util.InputMismatchException;

public class MainMenu{

    private static InputHandler inputHandler;

    //metod som anropar meny och stänger scanner
public void runMenu() {
    inputHandler = new InputHandler();
    menu();
    inputHandler.closeScanner();
}
    public void menu() {
        //anropar meny med olika val och switch case
        while (true) {
            try {
                menyOptions();
                int choice = getUserChoice();
                switch (choice) {
                    case 1:
                        inputHandler.getStringInput();
                        addMovie();
                        break;
                    case 2:
                        inputHandler.getStringInput(); // konsumerar new line character
                        viewMovies();
                        break;
                    case 3:
                        exit();
                        break;
                    default:
                        System.out.println("Invalid input. Please enter a valid option.");
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                inputHandler.getStringInput(); // Consume the newline character
            }
        }

    }

    private void exit() {
        System.out.println("Exiting the application.");
        System.exit(0);
    }


    private static void viewMovies() {
        ClientConsumer.getDataFromKafka("movieListTopic");
    }



    private static void addMovie() { // metod som skapar en ny film
        System.out.println("Enter id");
        long id = inputHandler.getLongInput();
        inputHandler.getStringInput(); //Konsumerar new line character
        System.out.print("Enter Movie Title: ");
        String title = inputHandler.getStringInput();
        System.out.print("Enter Movie Genre: ");
        String genre = inputHandler.getStringInput();
        System.out.print("Enter Movie Release Date: ");
        String releaseDate = inputHandler.getStringInput();

        JSONObject myObj = new JSONObject();
        myObj.put("id", id);
        myObj.put("movieTitle", title);
        myObj.put("movieGenre", genre);
        myObj.put("movieReleaseDate", releaseDate);

        ClientConsumer.sendToWebAPI(myObj);
    }

    private void menyOptions(){
        String[] options = {"Main Menu:","1. Add a movie", "2. View movies", "3. Exit"};
        for (String option : options) {
            System.out.println(option);
        }
    }
    private int getUserChoice() {
        System.out.print("Select an option:\n ");
        return inputHandler.getIntInput();
    }


}