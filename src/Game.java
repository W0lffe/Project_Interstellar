import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static boolean gameRunning = true;

    public static void newGame(Scanner inputScanner) {

        ArrayList<Skills> playerSkills = new ArrayList<>(); // initialize empty list for player skills
        ArrayList<Items> playerInventory = new ArrayList<>(); // initialize empty list for player inventory items
        // ArrayList<Player> GameState = new ArrayList<>(); //initialize emtpy list to
        // track state of game for this player

        Skills.initializeSkillArray();

        // create a new player
        // Player player = new Player("Jaxon Ryker", 150, 150, playerInventory,
        // playerSkills, 0, null , 1, 250, true);
        Player player = new Player("Jaxon Ryker", 150, 150, playerInventory, playerSkills, null, 1, 1000, 250, true);

        // start a new game with created player
        startGame(player, inputScanner);
    }

    public static void loadGame(Scanner inputScanner) {
        System.out.println("WIP");
        return;
    }

    private static void startGame(Player player, Scanner action) {
        System.out.println("Starting Game");
        gameRunning = true;

        while (gameRunning) {
            Prologue.Start(player, action);

            Continue(action);
        }

    }

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
