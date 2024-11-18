import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import java.util.ArrayList;



public class Interface extends BorderPane {

    private HBox top;
    private VBox bottom;
    private VBox left;
    private VBox right;
    private VBox center;
    
    public Interface(HBox top, VBox bottom, VBox left, VBox right, VBox center) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.center = center;

        this.setCenter(center);
        this.setTop(top);
        this.setLeft(left);
        this.setRight(right);
        this.setBottom(bottom);

        this.getStyleClass().add("interface");
        top.getStyleClass().add("horizontal-container");
        bottom.getStyleClass().add("horizontal-container");
        left.getStyleClass().add("horizontal-container");
        right.getStyleClass().add("horizontal-container");
        center.getStyleClass().add("horizontal-container");
    }
}

class HorizontalContainer extends HBox{

    private Label title;

    public HorizontalContainer(double arg0, String labelText) {
        super(arg0);
        this.title = new Label(labelText);

        this.getChildren().add(title);
        this.getStyleClass().add("horizontal-container");
        this.setAlignment(Pos.TOP_CENTER);
    }

    public void setHorizontalTitle(String labelText) {
        title.setText(labelText);
    }
}

class HorizontalPlayerActions extends HorizontalContainer{

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    public HorizontalPlayerActions(double arg0, String labelText, String b1Text, String b2Text) {
        super(arg0, labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);

        this.getChildren().addAll(button1, button2);
        this.setAlignment(Pos.CENTER);
    }

    public HorizontalPlayerActions(double arg0, String labelText, String b1Text, String b2Text, String b3Text) {
        super(arg0, labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);
        this.button3 = new Button(b3Text);

        this.getChildren().addAll(button1, button2, button3);
        this.setAlignment(Pos.CENTER);
    }

    public HorizontalPlayerActions(double arg0, String labelText, String b1Text, String b2Text, String b3Text, String b4Text) {
        super(arg0, labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);
        this.button3 = new Button(b3Text);
        this.button4 = new Button(b4Text);

        this.getChildren().addAll(button1, button2, button3, button4);
        this.setAlignment(Pos.CENTER);
    }

    public HorizontalPlayerActions(double arg0, String labelText, String b1Text, String b2Text, String b3Text, String b4Text, String b5Text) {
        super(arg0, labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);
        this.button3 = new Button(b3Text);
        this.button4 = new Button(b4Text);
        this.button5 = new Button(b5Text);

        this.getChildren().addAll(button1, button2, button3, button4, button5);
        this.setAlignment(Pos.CENTER);
    }

    public Button getFirstButton() {
        return button1;
    }

    public Button getSecondButton() {
        return button2;
    }

    public Button getThirdButton() {
        return button3;
    }

    public Button getFourthButton() {
        return button4;
    }

    public Button getFifthButton() {
        return button5;
    }
    
}

class VerticalContainer extends VBox{

    private Label title;

    public VerticalContainer(double arg0, String labelText) {
        super(arg0);
        this.title = new Label(labelText);

        this.getChildren().add(title);
        this.getStyleClass().add("vertical-container");
        this.setAlignment(Pos.CENTER);
    }

    public void setVerticalTitle(String labelText) {
        title.setText(labelText);
    }
    public Label getVerticalTitle() {
        return title;
    }

    
}

class CombatChoicesBox extends VerticalContainer{

    private ChoiceBox<String> enemies;

    public CombatChoicesBox(double arg0, String labelText) {
        super(arg0, labelText);
        this.enemies = new ChoiceBox<String>();

        this.getChildren().add(enemies);
        this.setAlignment(Pos.CENTER);
    }

    public ChoiceBox<String> getEnemiesChoiceBox() {
        return enemies;
    }

    public void updateEnemyList(ArrayList<NPC> enemyList){
        for (NPC enemy : enemyList){
            this.enemies.getItems().add(enemy.getName());
        }
    }
    
    public void deleteEnemyFromList(int indexOfEnemy){
        enemies.getItems().remove(indexOfEnemy);
    }
}

class VerticalStatus extends VerticalContainer{

    private Label levelXp;
    private Label health;
    private Label equipped;

    public VerticalStatus(double arg0, String playerName, String levelXpLabel,String healthLabel, String equippedLabel) {
        super(arg0, playerName);
        this.levelXp = new Label(levelXpLabel);
        this.health = new Label(healthLabel);
        this.equipped = new Label(equippedLabel);

        this.getChildren().addAll(levelXp, health, equipped);
        this.setAlignment(Pos.TOP_CENTER);
    }


    public VerticalStatus(double arg0, String labelText, String healthLabel) {
        super(arg0, labelText);
        this.health = new Label(healthLabel);

        this.getChildren().addAll(health);
        this.setAlignment(Pos.BOTTOM_CENTER);
    }

    public void updatePlayerStatus(String levelXPlabel, String healthStatusLabel, String equippedLabel){
        levelXp.setText(levelXPlabel);
        health.setText(healthStatusLabel);
        equipped.setText(equippedLabel);
    }

    public void updateEnemyStatus(String healthStatusLabel){
        health.setText(healthStatusLabel);
    }

}

class VerticalStoryPrint extends VerticalContainer{

    private TextArea textArea;
    private HorizontalPlayerActions playerActions;
   
    public VerticalStoryPrint(double arg0, String labelText) {
        super(arg0, labelText);
        this.textArea = new TextArea();

        this.getChildren().addAll(textArea);
        textArea.setWrapText(true);
        textArea.setEditable(false);
        this.setAlignment(Pos.CENTER);
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setText(String textToSet){
        textArea.setText(textToSet);
    }

    public void appendText(String textToAppend){
        textArea.appendText(textToAppend);
    }

    public HorizontalPlayerActions getPlayerActions() {
        return playerActions;
    }

    public void setPlayerActions(HorizontalPlayerActions playerActions) {
        this.playerActions = playerActions;
    }

}

class VerticalMainMenu extends VerticalContainer{

    private Button button1;
    private Button button2;
    private Button button3;

    public VerticalMainMenu(double arg0, String labelText, String b1Text, String b2Text, String b3Text) {
        super(arg0, labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);
        this.button3 = new Button(b3Text);

        this.getChildren().addAll(button1, button2, button3);
        this.setAlignment(Pos.CENTER);
    }

    public Button getFirstButton() {
        return button1;
    }
    public Button getSecondButton() {
        return button2;
    }
    public Button getThirdButton() {
        return button3;
    }
    
}

class VerticalSkillsMenu extends VerticalContainer{
    
    private ListView<Skills> skillsList;

    public VerticalSkillsMenu(double arg0, String labelText) {
        super(arg0, labelText);
        this.skillsList = new ListView<Skills>();

        this.getChildren().add(skillsList);
        this.setAlignment(Pos.TOP_CENTER);
    }

    public ListView<Skills> getSkillsList() {
        return skillsList;
    }

    public void setSkillsList(ListView<Skills> skillsList) {
        this.skillsList = skillsList;
    }

    public void addSkillToList(ArrayList<Skills> playerSkills){
        skillsList.getItems().setAll(playerSkills);
    }

    

    
}







