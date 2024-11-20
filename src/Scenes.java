import javafx.scene.Scene;

/**@class Scenes
 * @description Class contains Scene creation for game
 */
public class Scenes {
    
    /**
     * @function createMainMenu
     * @description creates Scene of elements for main menu
     * @return scene for Main Menu
     */
    public static Scene createMainMenu(){


        //Create top container, set size
        HorizontalContainer top = new HorizontalContainer(10, "");
        top.setPrefSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT/4);

        //Create bottom container, set size
        VerticalContainer bottom = new VerticalContainer(10,"");
        bottom.setPrefSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT/4);

        //Create left container, set size
        VerticalContainer left = new VerticalContainer(10,"");
        left.setPrefSize(Main.WINDOW_WIDTH/4, Main.WINDOW_HEIGHT);

        //Create right container, set size
        VerticalContainer right = new VerticalContainer(10,"");
        right.setPrefSize(Main.WINDOW_WIDTH/4, Main.WINDOW_HEIGHT);

        //Create center container, set size
        VerticalMainMenu center = new VerticalMainMenu(20,"Project Interstellar", "New Game", "Load Game", "Exit Game");
        center.setPrefSize(Main.WINDOW_WIDTH/2, Main.WINDOW_HEIGHT/2);

        //Create root for scene, using created containers, set stylesheet
        Interface root = new Interface(top, bottom, left, right, center);
        root.getStylesheets().add(Interface.class.getResource("styles.css").toExternalForm());
        
        //First button calls new game function
        center.getFirstButton().setOnAction(event -> Game.newGame());

        //Second button calls load game function
        center.getSecondButton().setOnAction(event -> Game.loadGame());

        //Third button closes game
        center.getThirdButton().setOnAction(event -> Main.primaryStage.close());

        //Creates main menu scene, returns scene to Main Menu
        Scene mainMenu = new Scene(root, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        return mainMenu;
    }


    /**
     * @function createGameScene
     * @description function creates Scene of elements for running game 
     * and sets references of containers
     * to Player object
     * @param player object of Player
     * @return scene for running game
     */
    public static Scene createGameScene(Player player){

        //Create top container, set size
        HorizontalContainer top = new HorizontalContainer(0, "");
        top.setPrefSize(Main.WINDOW_WIDTH/1.5, 50);

        //Create bottom container, set size
        VerticalContainer bottom = new VerticalContainer(0, "");
        bottom.setPrefSize(Main.WINDOW_WIDTH, 50);

        //Create left container, set size
        VerticalContainer left = new VerticalContainer(20, "");
        left.setPrefSize(Main.WINDOW_WIDTH/4, Main.WINDOW_HEIGHT/1.5);

        //Create right container, set size
        VerticalContainer right = new VerticalContainer(20, "");
        right.setPrefSize(Main.WINDOW_WIDTH/4, Main.WINDOW_HEIGHT/1.5);

        //Create center container, set size
        VerticalStoryPrint center = new VerticalStoryPrint(20, "");
        center.setPrefSize(Main.WINDOW_WIDTH/2, 500);
        
        //Create playerStatus container, set size
        VerticalStatus playerStatus = new VerticalStatus(5, player.getName(), "", "", "");
        playerStatus.setPrefSize(Main.WINDOW_WIDTH/4, right.getHeight()/2);

        //Set reference of status container to player object, and update its status
        player.setStatusContainer(playerStatus); 
        player.characterStatusUpdate();

        //Set reference of character info to player object
        player.setCharacterInfo(player.Character());

        //Create root for scene, using created containers, set stylesheet
        Interface gameRoot = new Interface(top, bottom, left, right, center);
        gameRoot.getStylesheets().add(Interface.class.getResource("styles.css").toExternalForm());

        //Create scene for running game, returns scene to game initialization
        Scene gameScene = new Scene(gameRoot, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        return gameScene;
    }
  
}
