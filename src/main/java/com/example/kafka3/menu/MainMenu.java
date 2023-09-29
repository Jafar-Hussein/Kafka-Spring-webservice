package com.example.kafka3.menu;


import com.example.kafka3.kafkaconsumer.ClientConsumer;
import org.json.simple.JSONObject;

import java.util.InputMismatchException;
import java.util.Scanner;
public class MainMenu{

//    private static MovieRepository movieRepository;
//    private static MovieInfo movieToUpdate;
    private static final Scanner scanner = new Scanner(System.in);

public static void main(String[] args) {
    MainMenu mainMenu = new MainMenu();
    mainMenu.menu();
    scanner.close();
}
    public void menu() {

        while (true) {
            try {
                menyOptions();
                int choice = getUserChoice();
                switch (choice) {
                    case 1:
                        scanner.nextLine(); // Consume the newline character
                        addMovie();
                        break;
                    case 2:
                        scanner.nextLine(); // Consume the newline character
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
                scanner.nextLine(); // Consume the newline character
            }
        }
    }

    private void exit() {
        System.out.println("Exiting the application.");
        System.exit(0);
    }
//private static void updateMoviesFromDb() {
//    System.out.print("Enter Movie ID to Update: ");
//    long movieIdToUpdate = scanner.nextLong();
//    scanner.nextLine(); // Consume the newline character
//
////    MovieInfo movieToUpdate = movieRepository.findById(movieIdToUpdate).orElse(null);
////    if (movieToUpdate == null) {
////        System.out.println("Movie not found!");
////        return;
////    }
//
//    System.out.print("Enter Updated Movie Title: ");
//    String title = scanner.nextLine();
//    System.out.print("Enter Updated Movie Genre: ");
//    String genre = scanner.nextLine();
//    System.out.print("Enter Updated Movie Release Date: ");
//    String releaseDate = scanner.nextLine();
//
//    movieToUpdate.setMovieTitle(title);
//    movieToUpdate.setMovieGenre(genre);
//    movieToUpdate.setMovieReleaseDate(releaseDate);
//
//    movieRepository.save(movieToUpdate);
//
//    System.out.println("Movie updated successfully!");
//}


//    private void updateMoviesFromDb() {
//        System.out.print("Enter Movie ID to Update: ");
//        long movieIdToUpdate = scanner.nextLong();
//        scanner.nextLine(); // Consume the newline character
//
//        MovieInfo movieInfo = new MovieInfo();
//        movieInfo.setId(movieIdToUpdate);
//
//        System.out.print("Enter Updated Movie Title: ");
//        String title = scanner.nextLine();
//        System.out.print("Enter Updated Movie Genre: ");
//        String genre = scanner.nextLine();
//        System.out.print("Enter Updated Movie Release Date: ");
//        String releaseDate = scanner.nextLine();
//
//        movieInfo.setMovieTitle(title);
//        movieInfo.setMovieGenre(genre);
//        movieInfo.setMovieReleaseDate(releaseDate);
//
//        movieService.updateMovie(movieInfo);
//        System.out.println("Movie updated successfully!");
//    }

//    private void deleteMoviesFromDb() {
//        System.out.print("Enter Movie ID to Delete: ");
//        long movieIdToDelete = scanner.nextLong();
//        scanner.nextLine(); // Consume the newline character
//
//        movieRepository.deleteById(movieIdToDelete);
//        System.out.println("Movie deleted successfully!");
//    }


    private static void viewMovies() {
        System.out.println("Enter topic name");
        String topicName = scanner.nextLine();
        ClientConsumer.getDataFromKafka(topicName);
    }



    private static void addMovie() {
        System.out.println("Enter id");
        long id = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Movie Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Movie Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Movie Release Date: ");
        String releaseDate = scanner.nextLine();

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
        return scanner.nextInt();
    }


}