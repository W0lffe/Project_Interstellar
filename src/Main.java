

import java.util.Scanner;
public class Main{

    public static void main(String[] args) {


        Scanner inputScanner = new Scanner(System.in);
        String userInput; //initialize variable for user input
        
        do {
            System.out.println("Project: Interstellar");

            System.out.println("A) New Game\nB) Load Game \nC) Exit Game");
            userInput = Validation.UserInput(inputScanner); //use validation for user input

            switch (userInput) {
                case "A":
                    Game.newGame(inputScanner); //char A to start a new game
                    break;
                case "B":
                    Game.loadGame(inputScanner); //char B to load existing game
                    break;
                case "C":
                    System.out.println("Exiting game"); //char C to exit game
                    break;
                default:
                    System.out.println("Unknown action.");
            }
        } while (!userInput.equals("C"));

        inputScanner.close();
    }
}

    