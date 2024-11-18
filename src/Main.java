import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 600;
    public static Scene mainMenuScene;
    public static Stage primaryStage;

    @Override
    public void start(Stage primary){

        primaryStage = primary;

        mainMenuScene = Scenes.createMainMenu();
        primaryStage.setTitle("Project Interstellar");
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

