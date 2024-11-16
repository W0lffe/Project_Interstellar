import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


//Main Class
public class Main extends Application{

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 900;
    public static Scene mainMenuScene;


    //Main

    public void start(Stage primaryStage){

        HorizontalContainer top = new HorizontalContainer("");
        top.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT/4);

        HorizontalContainer bottom = new HorizontalContainer("");
        bottom.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT/4);

        VerticalContainer left = new VerticalContainer("");
        left.setPrefSize(WINDOW_WIDTH/4, WINDOW_HEIGHT);

        VerticalContainer right = new VerticalContainer("");
        right.setPrefSize(WINDOW_WIDTH/4, WINDOW_HEIGHT);

        VerticalMainMenu center = new VerticalMainMenu("Project Interstellar", "New Game", "Load Game", "Exit Game");
        center.setPrefSize(WINDOW_WIDTH/2, WINDOW_HEIGHT/2);

        Interface root = new Interface(top, bottom, left, right, center);
        

        //BUTTONS HERE

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

    