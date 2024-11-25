import java.util.ArrayList;
import javafx.scene.Scene;

/**@class Game
 * @description Holds main functions of game, creating new game, starting game and loading existing game
 * @future-idea Change "startGame" to work as a portal to different sections of game
 */
public class Game {
    
    /**@description Reference to running game scene, created in game initializing */
    public static Scene gameRunningScene;

    /**@Function newGame
     * @description function starts a new game, creating initializations and creating player
     */
    public static void newGame(){

        // initialize empty list for player skills, inventory and progress flags
        ArrayList<Skills> playerSkills = new ArrayList<>(); 
        ArrayList<Items> playerInventory = new ArrayList<>(); 
        ArrayList<ProgressFlags> progressFlags = new ArrayList<>();

        //Create a new player
        //Player player = new Player("Jaxon Ryker", 150, 150, playerInventory, playerSkills, Weapon.NONE, 1 , 0, 250, true, progressFlags); 
        Player player = new Player("Jaxon Ryker", 150, 150, playerInventory, playerSkills, Weapon.ANTIMATTER_RIFLE, 1, 1000, 250, true, progressFlags); //cheat/test player


        //Initialize array with predefined Skills
        Skills.initializeSkillArray(); 

        //Initiate tiered weapon lists
        Weapon.initWeaponLists();

        //Init consumables lists
        Consumables.initConsumablesList();
        
        //Initialize array with predefined arraylists of lootable items
        LootLists.initLootItemLists();

        //Initialize preset enemy lists
        NPC.initEnemyLists(player.getLevel());

        //Create scene for running game, and set active
        gameRunningScene = Scenes.createGameScene(player);
        Main.primaryStage.setScene(gameRunningScene);

        //Start game with created player
        startGame(player);
    }

    /**@Function startGame
     * @description starts game with player object
     * @param player object of created player
     * @future-idea Change to work as a portal to different sections of game
     */
    private static void startGame(Player player){

        Utility.Print("Game is starting\n", Utility.ActionSpeed);

        //Take player to prologue 
        //Prologue.PartOne(player); //take player to Prologue

        //CalyraBunker.InstanceOne(player);
        CalyraBunker.BunkerMainRoom(player);
    }

    /**@Function loadGame
     * @description WORK IN PROGRESS
     */
    public static void loadGame(){
        System.out.println("WIP");
        return;
    }
}
