package com.example.kafka3.menu;


import com.example.kafka3.InputClass.InputHandler;
import com.example.kafka3.kafkaconsumer.ClientConsumer;
import org.json.simple.JSONObject;

import java.util.InputMismatchException;
import java.util.Scanner;
public class MainMenu{

//    private static MovieRepository movieRepository;
//    private static MovieInfo movieToUpdate;
    private static InputHandler inputHandler;

public static void main(String[] args) {
    inputHandler = new InputHandler();
    MainMenu mainMenu = new MainMenu();
    mainMenu.menu();
    inputHandler.closeScanner();

}
    public void menu() {

        while (true) {
            try {
                menyOptions();
                int choice = getUserChoice();
                switch (choice) {
                    case 1:
                        inputHandler.getStringInput(); // Consume the newline character
                        addMovie();
                        break;
                    case 2:
                        inputHandler.getStringInput(); // Consume the newline character
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
        System.out.println("Enter topic name");
        String topicName = inputHandler.getStringInput();
        ClientConsumer.getDataFromKafka(topicName);
    }



    private static void addMovie() {
        System.out.println("Enter id");
        long id = inputHandler.getLongInput();
        inputHandler.getStringInput(); // Consume the newline character
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