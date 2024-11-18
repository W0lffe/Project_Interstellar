import java.util.ArrayList;
import javafx.scene.Scene;

public class Game {
    
    public static Scene gameRunningScene;

    public static void newGame(){

        ArrayList<Skills> playerSkills = new ArrayList<>(); // initialize empty list for player skills
        ArrayList<Items> playerInventory = new ArrayList<>(); // initialize empty list for player inventory items
        ArrayList<ProgressFlags> progressFlags = new ArrayList<>();

        Skills.initializeSkillArray(); //Initialize array with set Skills

        //Create a new player
        //Player player = new Player("Jaxon Ryker", 150, 150, playerInventory, playerSkills, Weapon.NONE, 1 , 0, 250, true, progressFlags); 
        Player player = new Player("Jaxon Ryker", 150, 150, playerInventory, playerSkills, Weapon.QUANTUM_REPEATER, 1, 1000, 250, true, progressFlags);

        gameRunningScene = Scenes.createGameScene(player);
        Main.primaryStage.setScene(gameRunningScene);


        startGame(player);
    }

    private static void startGame(Player player){

        Utility.Print("Game is starting\n", Utility.ActionSpeed);

        //Combat.CombatTesting(player);

        Prologue.PartOne(player); //take player to Prologue

        //CalyraBunker.InstanceOne(player, action);
        //CalyraBunker.BunkerMainRoom(player, action);
    }

    public static void loadGame(){
        System.out.println("WIP");
        return;
    }
}
