import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


//Main Class
public class Main extends Application{

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 600;
    public static Scene mainMenuScene;


    //Main

    public void start(Stage primaryStage){

        HorizontalContainer top = new HorizontalContainer(10, "");
        top.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT/4);

        VerticalContainer bottom = new VerticalContainer(10,"");
        bottom.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT/4);

        VerticalContainer left = new VerticalContainer(10,"");
        left.setPrefSize(WINDOW_WIDTH/4, WINDOW_HEIGHT);

        VerticalContainer right = new VerticalContainer(10,"");
        right.setPrefSize(WINDOW_WIDTH/4, WINDOW_HEIGHT);

        VerticalMainMenu center = new VerticalMainMenu(20,"Project Interstellar", "New Game", "Load Game", "Exit Game");
        center.setPrefSize(WINDOW_WIDTH/2, WINDOW_HEIGHT/2);

        Interface root = new Interface(top, bottom, left, right, center);
        root.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        center.getFirstButton().setOnAction(event -> Game.newGame(primaryStage));
        center.getSecondButton().setOnAction(event -> Game.loadGame(null));
        center.getThirdButton().setOnAction(event -> primaryStage.close());

        
        Scene mainMenu = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        mainMenuScene = mainMenu;
        primaryStage.setTitle("Project Interstellar");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

    