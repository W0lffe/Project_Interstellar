import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;


public class Interface extends BorderPane {

    private HBox top;
    private HBox bottom;
    private VBox left;
    private VBox right;
    private VBox center;
    
    public Interface(HBox top, HBox bottom, VBox left, VBox right, VBox center) {
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
    }
}

class HorizontalContainer extends HBox{

    private Label title;

    public HorizontalContainer(String labelText) {
        this.title = new Label(labelText);

        this.getChildren().add(title);
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

    public HorizontalPlayerActions(String labelText, String b1Text, String b2Text) {
        super(labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);

        this.getChildren().addAll(button1, button2);
    }

    public HorizontalPlayerActions(String labelText, String b1Text, String b2Text, String b3Text) {
        super(labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);
        this.button3 = new Button(b3Text);

        this.getChildren().addAll(button1, button2, button3);
    }

    public HorizontalPlayerActions(String labelText, String b1Text, String b2Text, String b3Text, String b4Text) {
        super(labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);
        this.button3 = new Button(b3Text);
        this.button4 = new Button(b4Text);

        this.getChildren().addAll(button1, button2, button3, button4);
    }

    public HorizontalPlayerActions(String labelText, String b1Text, String b2Text, String b3Text, String b4Text, String b5Text) {
        super(labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);
        this.button3 = new Button(b3Text);
        this.button4 = new Button(b4Text);
        this.button5 = new Button(b5Text);

        this.getChildren().addAll(button1, button2, button3, button4, button5);
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

    public VerticalContainer(String labelText) {
        this.title = new Label(labelText);

        this.getChildren().add(title);
    }

    public void setVerticalTitle(String labelText) {
        title.setText(labelText);
    }
}

class VerticalStoryPrint extends VerticalContainer{

    private TextArea textArea;
   
    public VerticalStoryPrint(String labelText) {
        super(labelText);
        this.textArea = new TextArea();

        this.getChildren().addAll(textArea);
        textArea.setWrapText(true);
        textArea.setEditable(false);
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

class VerticalStatus extends VerticalContainer{

    private Label health;
    private Label equipped;

    public VerticalStatus(String labelText, String labelHealthText, String labelEquippedText) {
        super(labelText);
        this.health = new Label(labelHealthText);
        this.equipped = new Label(labelHealthText);

        this.getChildren().addAll(health,equipped);
    }

    public void setHealthLabel(String labelHealth) {
        health.setText(labelHealth);
    }
    
    public void setEquippedLabel(String labelEquipped) {
        equipped.setText(labelEquipped);
    }

}

class VerticalMainMenu extends VerticalContainer{

    private Button button1;
    private Button button2;
    private Button button3;

    public VerticalMainMenu(String labelText, String b1Text, String b2Text, String b3Text) {
        super(labelText);
        this.button1 = new Button(b1Text);
        this.button2 = new Button(b2Text);
        this.button3 = new Button(b3Text);

        this.getChildren().addAll(button1, button2, button3);
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







