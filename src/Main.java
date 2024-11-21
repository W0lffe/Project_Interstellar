import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    /**@description Window width set to 1200px */
    public static final int WINDOW_WIDTH = 1600; 

    /**@description Window height set to 600px */
    public static final int WINDOW_HEIGHT = 900;
    
    /**@description Reference to main menu scene created in Main */
    public static Scene mainMenuScene;

    /**@description Reference to primary stage created in Main*/
    public static Stage primaryStage;

    @Override
    public void start(Stage primary){

        //Store primaryStage
        primaryStage = primary;

        //Create scene for main menu
        mainMenuScene = Scenes.createMainMenu();
        primaryStage.setTitle("Project Interstellar");
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

