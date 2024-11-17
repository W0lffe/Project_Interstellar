import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;



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

class VerticalPlayerStatus extends VerticalContainer{

    private Label levelXp;
    private Label levelUp;
    private Label health;
    private Label equipped;
    
    public VerticalPlayerStatus(double arg0, String playerName, String levelXpLabel, String levelUpLabel, String healthLabel, String equippedLabel) {
        super(arg0, playerName);
        this.levelXp = new Label(levelXpLabel);
        this.levelUp = new Label(levelUpLabel);
        this.health = new Label(healthLabel);
        this.equipped = new Label(equippedLabel);

        this.getChildren().addAll(levelUp, levelXp, health, equipped);
        this.setAlignment(Pos.TOP_CENTER);
    }

    public void updateStatus(String levelXPlabel, String levelUpLabel, String healthStatusLabel, String equippedLabel){
        levelXp.setText(levelXPlabel);
        levelUp.setText(levelUpLabel);
        health.setText(healthStatusLabel);
        equipped.setText(equippedLabel);
    }
}

class VerticalStoryPrint extends VerticalContainer{

    private TextArea textArea;
   
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







