import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game {

    public static Scene gameRunningScene;

    public static void newGame(Stage primaryStage) {

        ArrayList<Skills> playerSkills = new ArrayList<>(); // initialize empty list for player skills
        ArrayList<Items> playerInventory = new ArrayList<>(); // initialize empty list for player inventory items
        ArrayList<ProgressFlags> progressFlags = new ArrayList<>();
   
        Skills.initializeSkillArray(); //Initialize array with set Skills

        // create a new player
        Player player = new Player("Jaxon Ryker", 150, 150, playerInventory, playerSkills, Weapon.NONE, 1 , 0, 250, true, progressFlags); 
        //Player player = new Player("Jaxon Ryker", 50, 150, playerInventory, playerSkills, Weapon.GRAVITY_CANNON, 1, 1000, 250, true, progressFlags);
        primaryStage.setScene(null);
        createGameScene(player);
        primaryStage.setScene(gameRunningScene);

        // start a new game with created player
        startGame(player);
    }


    //WIP loadGame 
    public static void loadGame(Scanner inputScanner) {
        System.out.println("WIP");
        return;
    }

    //Start Game with earlier created player
    private static void startGame(Player player) {

        Utility.Print("Game is starting\n", Utility.ActionSpeed);
        
            Prologue2.Start(player); //take player to Prologue

            //Continue(action);

            //CalyraBunker.InstanceOne(player, action);
            //CalyraBunker.BunkerMainRoom(player, action);
            //gameRunning = false;

            //Continue(action);
        

    }

    //Function asks player if they still want to play, if not player will be taken back to main menu
    private static void Continue(Scanner action) {
        String userInput;

        while (true) {
            Utility.Print("Do you want to continue your journey?\n", Utility.ActionSpeed);
            System.out.println("A) Yes \nB) No");
            userInput = Validation.UserInput(action);

            if (userInput.equals("B")) {
                break;
            } else {
                break;
            }
        }
    }


    private static void createGameScene(Player player){

        HorizontalContainer top = new HorizontalContainer(0, "");
        top.setPrefSize(Main.WINDOW_WIDTH/1.5, 50);

        VerticalContainer bottom = new VerticalContainer(0, "");
        bottom.setPrefSize(Main.WINDOW_WIDTH, 50);
        bottom.setAlignment(Pos.TOP_CENTER);

        VerticalContainer left = new VerticalContainer(10, "");
        left.setPrefSize(Main.WINDOW_WIDTH/4, Main.WINDOW_HEIGHT/1.5);

        VerticalContainer right = new VerticalContainer(10, "");
        right.setPrefSize(Main.WINDOW_WIDTH/4, Main.WINDOW_HEIGHT/1.5);

        VerticalPlayerStatus playerStatus = new VerticalPlayerStatus(5, player.getName(), "", "", "", "");
        player.setStatusContainer(playerStatus);
        player.CharacterInfo();

        playerStatus.setPrefSize(Main.WINDOW_WIDTH/4, right.getHeight()/2);
        right.getChildren().addAll(playerStatus);

        VerticalStoryPrint center = new VerticalStoryPrint(10, "");
        center.setPrefSize(Main.WINDOW_WIDTH/2, 500);

        Interface gameRoot = new Interface(top, bottom, left, right, center);
        gameRoot.getStylesheets().add(Interface.class.getResource("styles.css").toExternalForm());

        Scene gameScene = new Scene(gameRoot, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        gameRunningScene = gameScene;
    }
}
