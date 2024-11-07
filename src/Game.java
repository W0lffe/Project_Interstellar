import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static boolean gameRunning = true; // Set game running TRUE

    public static void newGame(Scanner inputScanner) {

        ArrayList<Skills> playerSkills = new ArrayList<>(); // initialize empty list for player skills
        ArrayList<Items> playerInventory = new ArrayList<>(); // initialize empty list for player inventory items
   
        Skills.initializeSkillArray(); //Initialize array with set Skills

        // create a new player
        // Player player = new Player("Jaxon Ryker", 150, 150, playerInventory, playerSkills, 0, null , 1, 250, true); 
        Player player = new Player("Jaxon Ryker", 150, 150, playerInventory, playerSkills, null, 1, 1000, 250, true);

        // start a new game with created player
        startGame(player, inputScanner);
    }


    //WIP loadGame 
    public static void loadGame(Scanner inputScanner) {
        System.out.println("WIP");
        return;
    }

    //Start Game with earlier created player
    private static void startGame(Player player, Scanner action) {
        System.out.println("Starting Game");
        gameRunning = true; //sets gameRunning true again, incase for example player dies and starts again

        while (gameRunning) {
            //Prologue.Start(player, action); //take player to Prologue

            Continue(action);

            ActOne.InstanceOne(player, action);
        }

    }

    //Function asks player if they still want to play, if not player will be taken back to main menu
    private static void Continue(Scanner action) {
        String userInput;

        while (true) {
            System.out.println("Do you want to continue your journey?\nA) Yes \nB) No");
            userInput = Validation.UserInput(action);

            if (userInput.equals("B")) {
                gameRunning = false;
                break;
            } else {
                break;
            }
        }

    }
}
