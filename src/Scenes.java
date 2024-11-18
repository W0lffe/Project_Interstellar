import javafx.scene.Scene;


public class Scenes {
    
    public static Scene createMainMenu(){

        HorizontalContainer top = new HorizontalContainer(10, "");
        top.setPrefSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT/4);

        VerticalContainer bottom = new VerticalContainer(10,"");
        bottom.setPrefSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT/4);

        VerticalContainer left = new VerticalContainer(10,"");
        left.setPrefSize(Main.WINDOW_WIDTH/4, Main.WINDOW_HEIGHT);

        VerticalContainer right = new VerticalContainer(10,"");
        right.setPrefSize(Main.WINDOW_WIDTH/4, Main.WINDOW_HEIGHT);

        VerticalMainMenu center = new VerticalMainMenu(20,"Project Interstellar", "New Game", "Load Game", "Exit Game");
        center.setPrefSize(Main.WINDOW_WIDTH/2, Main.WINDOW_HEIGHT/2);

        Interface root = new Interface(top, bottom, left, right, center);
        //root.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        center.getFirstButton().setOnAction(event -> Game.newGame());
        center.getSecondButton().setOnAction(event -> Game.loadGame());
        center.getThirdButton().setOnAction(event -> Main.primaryStage.close());

        Scene mainMenu = new Scene(root, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        return mainMenu;
    }

    public static Scene createGameScene(Player player){

        HorizontalContainer top = new HorizontalContainer(0, "");
        top.setPrefSize(Main.WINDOW_WIDTH/1.5, 50);

        VerticalContainer bottom = new VerticalContainer(0, "");
        bottom.setPrefSize(Main.WINDOW_WIDTH, 50);

        VerticalContainer left = new VerticalContainer(20, "");
        left.setPrefSize(Main.WINDOW_WIDTH/4, Main.WINDOW_HEIGHT/1.5);

        VerticalContainer right = new VerticalContainer(20, "");
        right.setPrefSize(Main.WINDOW_WIDTH/4, Main.WINDOW_HEIGHT/1.5);

        VerticalStatus playerStatus = new VerticalStatus(5, player.getName(), "", "", "");
        player.setStatusContainer(playerStatus); //reference to player class
        player.characterStatusUpdate();
        player.setCharacterInfo(player.Character());

        playerStatus.setPrefSize(Main.WINDOW_WIDTH/4, right.getHeight()/2);

        VerticalStoryPrint center = new VerticalStoryPrint(20, "");
        center.setPrefSize(Main.WINDOW_WIDTH/2, 500);

        Interface gameRoot = new Interface(top, bottom, left, right, center);
        //gameRoot.getStylesheets().add(Interface.class.getResource("styles.css").toExternalForm());

        Scene gameScene = new Scene(gameRoot, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        return gameScene;
    }
    
    public static Scene createCombatScene(){
     
        VerticalStoryPrint center = new VerticalStoryPrint(10, "");
        center.setPrefSize(Main.WINDOW_WIDTH/2, 500);

        Interface combatRoot = new Interface(Utility.topContainer, Utility.bottomContainer, Utility.leftContainer, Utility.rightContainer, center);

        Scene combatScene = new Scene(combatRoot, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        return combatScene;

    }

}
