import java.util.Scanner;

//Main Class
public class Main{

    //Main

    public static void main(String[] args) {


        Scanner inputScanner = new Scanner(System.in); //Create object Scanner
        String userInput; //initialize variable for user input
        
        do {
            Utility.Print("Project: Interstellar\n", 20);

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
                    break;
            }
        } while (!userInput.equals("C")); //If userInput is C, program closes

        inputScanner.close();
    }
}

    