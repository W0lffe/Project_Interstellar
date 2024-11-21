import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import java.util.ArrayList;


/**
 * @description Borderpane with containers
 * @purpose to create root for scenes
 */
public class Interface extends BorderPane {

    private HBox top;
    private VBox bottom;
    private VBox left;
    private VBox right;
    private VBox center;
    
    /**
     * @description create Borderpane with containers
     * @param top HBox to top
     * @param bottom VBox to bottom
     * @param left VBox to left
     * @param right VBox to right
     * @param center VBox to center
     * @purpose to create root for scenes
     */
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

/**@description Simple horizontal container with 1 label 
 * @methods setHorizontalTitle()
*/
class HorizontalContainer extends HBox{

    private Label title;

    /**
     * @description Create simple horizontal container with 1 label
     * @param arg0 Spacing between elements
     * @param labelText String to label
     */
    public HorizontalContainer(double arg0, String labelText) {
        super(arg0);
        this.title = new Label(labelText);

        this.getChildren().add(title);
        this.getStyleClass().add("horizontal-container");
        this.setAlignment(Pos.TOP_CENTER);
    }

    /**
     * @description set string to label
     * @param labelText string to set
     */
    public void setHorizontalTitle(String labelText) {
        title.setText(labelText);
    }
}

/**@description horizontal player actions container with label and 2-5 buttons 
 * @methods getFirstButton(), getSecondButton(), getThirdButton(), getFourthButton(), getFifthButton()
 * setHorizontalTitle()
*/
class HorizontalPlayerActions extends HorizontalContainer{

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    /**
     * @description create horizontal player actions container with label and 2 buttons
     * @param arg0 spacing between elements
     * @param labelText String to label
     * @param b1Text String to button text
     * @param b2Text String to button text
     */
    public HorizontalPlayerActions(double arg0, String labelText, String b1Text, String b2Text) {
        super(arg0, labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);

        this.getChildren().addAll(button1, button2);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * @description create horizontal player actions container with label and 2 buttons
     * @param arg0 spacing between elements
     * @param labelText String to label
     * @param b1Text String to button text
     * @param b2Text String to button text
     * @param b3Text String to button text
     */
    public HorizontalPlayerActions(double arg0, String labelText, String b1Text, String b2Text, String b3Text) {
        super(arg0, labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);
        this.button3 = new Button(b3Text);

        this.getChildren().addAll(button1, button2, button3);
        this.setAlignment(Pos.CENTER);
    }
    
    /**
     * @description create horizontal player actions container with label and 2 buttons
     * @param arg0 spacing between elements
     * @param labelText String to label
     * @param b1Text String to button text
     * @param b2Text String to button text
     * @param b3Text String to button text
     * @param b4Text String to button text
     */
    public HorizontalPlayerActions(double arg0, String labelText, String b1Text, String b2Text, String b3Text, String b4Text) {
        super(arg0, labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);
        this.button3 = new Button(b3Text);
        this.button4 = new Button(b4Text);

        this.getChildren().addAll(button1, button2, button3, button4);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * @description create horizontal player actions container with label and 2 buttons
     * @param arg0 spacing between elements
     * @param labelText String to label
     * @param b1Text String to button text
     * @param b2Text String to button text
     * @param b3Text String to button text
     * @param b4Text String to button text
     * @param b5Text String to button text
     */
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

    /**@return first button element of horizontal player actions*/
    public Button getFirstButton() {
        return button1;
    }

    /**@return second button element of horizontal player actions*/
    public Button getSecondButton() {
        return button2;
    }

    /**@return third button element of horizontal player actions*/
    public Button getThirdButton() {
        return button3;
    }

    /**@return fourth button element of horizontal player actions*/
    public Button getFourthButton() {
        return button4;
    }

    /**@return fifth button element of horizontal player actions*/
    public Button getFifthButton() {
        return button5;
    }
    
}
/**@description Simple vertical container with label
 * @methods setVerticalTitle(), getVerticalTitle()
 */
class VerticalContainer extends VBox{

    private Label title;

    /**
     * @description create simple vertical container with label
     * @param arg0 spacing between elements
     * @param labelText string to label'
     */
    public VerticalContainer(double arg0, String labelText) {
        super(arg0);
        this.title = new Label(labelText);

        this.getChildren().add(title);
        this.getStyleClass().add("vertical-container");
        this.setAlignment(Pos.CENTER);
    }

    /**
     * @description set string to label
     * @param labelText string to be set
     */
    public void setVerticalTitle(String labelText) {
        title.setText(labelText);
    }

    /**@return label element of vertical container */
    public Label getVerticalTitle() {
        return title;
    }

}

/**@description Choicebox for enemy list in combat, with label
 * @methods updateEnemyChoices(), deleteEnemyFromChoices(), setVerticalTitle(), getVerticalTitle()
 */
class CombatChoicesBox extends VerticalContainer{

    private ChoiceBox<String> enemies;

    /**
     * @description create choices box for enemy list in combat, with label
     * @param arg0 spacing between elements
     * @param labelText string to label
     */
    public CombatChoicesBox(double arg0, String labelText) {
        super(arg0, labelText);
        this.enemies = new ChoiceBox<String>();

        this.getChildren().add(enemies);
        this.setAlignment(Pos.CENTER);
    }

    /**@return choicebox element */
    public ChoiceBox<String> getEnemiesChoiceBox() {
        return enemies;
    }

    /**
     * @description updates choices based on objects in list of enemies,
     * only lists NPC name property
     * @param enemyList ArrayList typed to NPC
     */
    public void updateEnemyChoices(ArrayList<NPC> enemyList){
        for (NPC enemy : enemyList){
            this.enemies.getItems().add(enemy.getName());
        }
    }
    
    /**
     * @description deletes enemy from choices
     * @param indexOfEnemy value of enemy index in choicebox
     */
    public void deleteEnemyFromChoices(int indexOfEnemy){
        enemies.getItems().remove(indexOfEnemy);
    }
}

/**@description Vertical container with 2-4 labels, used to display status of player and NPC 
 * @methods updatePlayerStatus(), updateEnemyStatus()
*/
class VerticalStatus extends VerticalContainer{

    private Label levelXp;
    private Label health;
    private Label equipped;

    /**
     * @description creates vertical container with 4 labels, used to display status of player
     * @param arg0 spacing between elements
     * @param playerName name of player to label
     * @param levelXpLabel level and xp of player to label
     * @param healthLabel health of player to label
     * @param equippedLabel equipped item of player to label
     */
    public VerticalStatus(double arg0, String playerName, String levelXpLabel,String healthLabel, String equippedLabel) {
        super(arg0, playerName);
        this.levelXp = new Label(levelXpLabel);
        this.health = new Label(healthLabel);
        this.equipped = new Label(equippedLabel);

        this.getChildren().addAll(levelXp, health, equipped);
        this.setAlignment(Pos.TOP_CENTER);
    }


    /**
     * @description creates vertical container with 2 labels, used to display status of enemy NPC
     * @param arg0 space between elements
     * @param npcName name of npc
     * @param healthLabel health of npc
     */
    public VerticalStatus(double arg0, String npcName, String healthLabel) {
        super(arg0, npcName);
        this.health = new Label(healthLabel);

        this.getChildren().addAll(health);
        this.setAlignment(Pos.BOTTOM_CENTER);
    }

    /**
     * @description updates status of player to status container
     * @param levelXPlabel current level and xp of player
     * @param healthStatusLabel current health of player
     * @param equippedLabel current equipped item of player
     */
    public void updatePlayerStatus(String levelXPlabel, String healthStatusLabel, String equippedLabel){
        levelXp.setText(levelXPlabel);
        health.setText(healthStatusLabel);
        equipped.setText(equippedLabel);
    }

    /**
     * @description updates status of NPC to status container
     * @param healthStatusLabel current health of NPC
     */
    public void updateEnemyStatus(String healthStatusLabel){
        health.setText(healthStatusLabel);
    }

}

/**@description Vertical container holding textarea and label,
    used for story printing
    @methods getTextArea(), setText(), textToAppend()*/
class VerticalStoryPrint extends VerticalContainer{

    private TextArea textArea;
    
    /**
     * @description creates vertical container holding textarea and label,
     * used for story printing
     * @param arg0 space between elements
     * @param labelText string to label
     */
    public VerticalStoryPrint(double arg0, String labelText) {
        super(arg0, labelText);
        this.textArea = new TextArea();

        this.getChildren().addAll(textArea);
        textArea.setWrapText(true);
        textArea.setEditable(false);
        this.setAlignment(Pos.CENTER);
    }

    /**@return textArea element of vertical story print container */
    public TextArea getTextArea() {
        return textArea;
    }

    /**
     * @description set text to TextArea
     * @param textToSet string to be set
     */
    public void setText(String textToSet){
        textArea.setText(textToSet);
    }

    /**
     * @description append text to TextArea
     * @param textToAppend string to be appended
     */
    public void appendText(String textToAppend){
        textArea.appendText(textToAppend);
    }

}

/**@description Vertical container holding label and 3 buttons, used for Main Menu
 * @methods getFirstButton(), getSecondButton(), getThirdButton()
 */
class VerticalMainMenu extends VerticalContainer{

    private Button button1;
    private Button button2;
    private Button button3;

    /**
     * @description create a vertical container holding label and 3 buttons, used for Main Menu
     * @param arg0 space between elements
     * @param labelText string to label
     * @param b1Text string to button
     * @param b2Text string to button
     * @param b3Text string to button
     */
    public VerticalMainMenu(double arg0, String labelText, String b1Text, String b2Text, String b3Text) {
        super(arg0, labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);
        this.button3 = new Button(b3Text);

        this.getChildren().addAll(button1, button2, button3);
        this.setAlignment(Pos.CENTER);
    }

    /**@return first button element of main menu container */
    public Button getFirstButton() {
        return button1;
    }
    /**@return second button element of main menu container */
    public Button getSecondButton() {
        return button2;
    }
    /**@return third button element of main menu container */
    public Button getThirdButton() {
        return button3;
    }
    
}


/**@description Vertical ListView container with label
 * @methods getSkillsList(), setSkillsList(), addSkillToList()
 */
class VerticalSkillList extends VerticalContainer{
    
    private ListView<Skills> skillsList;
    private Button button;
    /**
     * @description Create vertical container with listview and label
     * @param arg0 spacing between elements
     * @param labelText string to label
     */
    public VerticalSkillList(double arg0, String labelText) {
        super(arg0, labelText);
        this.skillsList = new ListView<Skills>();

        this.getChildren().add(skillsList);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPrefSize(Main.WINDOW_WIDTH/5, Main.WINDOW_HEIGHT/2.5);
    }

    public VerticalSkillList(double arg0, String labelText, String buttonText) {
        super(arg0, labelText);
        this.skillsList = new ListView<Skills>();
        this.button = new Button(buttonText);

        this.getChildren().addAll(skillsList, button);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPrefSize(Main.WINDOW_WIDTH/5, Main.WINDOW_HEIGHT/2.5);
    }

    public Button getButton(){
        return button;
    }

    /**@return listview element of container */
    public ListView<Skills> getSkillsList() {
        return skillsList;
    }

    /**
     * @description Adds player acquired skills to listview
     * @param playerSkills ArrayList of player acquired skills, typed to Skills
     */
    public void addSkillToList(ArrayList<Skills> playerSkills){
        if (playerSkills.isEmpty()) {
            this.setVerticalTitle("You have not acquired any skills");
        }
        else{
            this.setVerticalTitle("You have acquired skills");
            for (Skills skill : playerSkills) {
                if (!skillsList.getItems().contains(skill)) {
                    skillsList.getItems().add(skill);
                }
            }
        }
    }
}
    /**@description Vertical ListView container with label
    * @methods getInventoryList()
    */
class VerticalInventoryList extends VerticalContainer{
        
    private ListView<Items> inventoryList;
    private Button button;

   /**
     * @description Create vertical container with listview and label
     * @param arg0 spacing between elements
     * @param labelText string to label
     */
    public VerticalInventoryList(double arg0, String labelText) {
        super(arg0, labelText);
        this.inventoryList = new ListView<Items>();
        this.button = new Button();

        this.getChildren().addAll(inventoryList, button);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPrefSize(Main.WINDOW_WIDTH/5, Main.WINDOW_HEIGHT/2.5);
    }

    /**@return listview element of container */
    public ListView<Items> getInventoryList() {
        return inventoryList;
    }

    public Button getButton(){
        return button;
    }
        
}







