import java.util.ArrayList;
import java.util.Scanner;


public class Game{
    
    public static void newGame(Scanner inputScanner){

        ArrayList<Skills> playerSkills = new ArrayList<>(); //initialize empty list for player skills
        ArrayList<Items> playerInventory = new ArrayList<>(); //initialize empty list for player inventory items
        //ArrayList<Player> GameState = new ArrayList<>(); //initialize emtpy list to track state of game for this player

        //create a new player
        Player player = new Player("Jaxon Ryker", 150, 150, playerInventory, playerSkills, 0, Items.QUANTUM_REPEATER, 1, 0, 500, true);
     
        //start a new game with created player
        startGame(player, inputScanner);
}

    public static void loadGame(Scanner inputScanner){
        System.out.println("WIP");
        return;
    }

    private static void startGame(Player player, Scanner action){
        System.out.println("Starting Game");
        boolean gameRunning = true;

        while (gameRunning) {
            Prologue.Start(player, action);
            
        }

    }
}
