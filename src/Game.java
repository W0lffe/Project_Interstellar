import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static boolean gameRunning = true; // Set game running TRUE

    public static void newGame(Scanner inputScanner) {

        ArrayList<Skills> playerSkills = new ArrayList<>(); // initialize empty list for player skills
        ArrayList<Items> playerInventory = new ArrayList<>(); // initialize empty list for player inventory items
        ArrayList<ProgressFlags> progressFlags = new ArrayList<>();
   
        Skills.initializeSkillArray(); //Initialize array with set Skills

        // create a new player
        Player player = new Player("Jaxon Ryker", 150, 150, playerInventory, playerSkills, Weapon.NONE, 1 , 0, 250, true, progressFlags); 
        //Player player = new Player("Jaxon Ryker", 50, 150, playerInventory, playerSkills, Weapon.GRAVITY_CANNON, 1, 1000, 250, true, progressFlags);

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
        Utility.Print("Game is starting\n", Utility.ActionSpeed);
        gameRunning = true; //sets gameRunning true again, incase for example player dies and starts again

        while (gameRunning) {
            Prologue.Start(player, action); //take player to Prologue

            Continue(action);

            CalyraBunker.InstanceOne(player, action);
            //CalyraBunker.BunkerMainRoom(player, action);
            //gameRunning = false;

            //Continue(action);
        }

    }

    //Function asks player if they still want to play, if not player will be taken back to main menu
    private static void Continue(Scanner action) {
        String userInput;

        while (true) {
            Utility.Print("Do you want to continue your journey?\n", Utility.ActionSpeed);
            System.out.println("A) Yes \nB) No");
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
