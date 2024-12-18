
/**@class Utility 
 * @description Holds variables, references and utility functions(ex. printing)
*/
public class Utility {
    
    //Stored references to running game scene root

    /**@description reference to gameRunningScene root*/
    public static Interface gameRoot = (Interface)Game.gameRunningScene.getRoot();
    /**@description reference to gameRunningScene root bottom container*/
    public static VerticalContainer bottomContainer = (VerticalContainer)gameRoot.getBottom();  
    /**@description reference to gameRunningScene root left container*/
    public static VerticalContainer leftContainer = (VerticalContainer)gameRoot.getLeft();
    /**@description reference to gameRunningScene root center container*/
    public static VerticalStoryPrint centerContainer = (VerticalStoryPrint)gameRoot.getCenter();
    /**@description reference to gameRunningScene root right container*/
    public static VerticalContainer rightContainer = (VerticalContainer)gameRoot.getRight();
    /**@description reference to gameRunningScene root top container*/
    public static HorizontalContainer topContainer = (HorizontalContainer)gameRoot.getTop();

    //Frequently used strings for player actions

    /**@contains string for player dialog, if action cant be done*/
    public static String cantDoThat = "Jaxon: I cant do that right now\n"; //For Switch case defaults
    /**@contains string for player dialog, if area is cleared*/
    public static String areaClear = "Jaxon: I have cleared that area\n";
    /**@contains string for player action, locker is checked*/
    public static String lockerIsEmpty = "Locker is empty.\n";
    /**@contains string for player dialog, has read datapad/console*/
    public static String isRead = "Jaxon: I already read that.\n";
    /**@contains string for player action, activates console*/
    public static String activateConsole = "You press the button on the console. The terminal flickers and hums to life, displaying a series of messages:\n";
    /**@contains string for player action, activates datapad*/
    public static String activateDatapad = "You pick up the datapad. It's an old model, slightly scratched. You wonder if it holds any important messages...\n";

    //Speed parameters for Utility.Print function, sets speed how fast to "type" text

    /**@description speed to print the story in function Print, CURRENTLY 0, TBD*/
    public static int StoryPrintSpeed = 0; 
    /**@description speed to print the story in function Print, 20ms*/
    public static int DataPrintSpeed = 20; 
    /**@description speed to print the story in function Print, 15ms*/
    public static int ActionSpeed = 15;

    //Experience points got from "LoreItems"

    /**@description experience gained from lore item: 25xp*/
    public static final int LoreItemEXP = 25;
    /**@description experience gained from looting an item: 5xp*/
    public static final int LootItemEXP = 5;


    /**
     * @description Reads file to string, sends to printing function ->
     * appends text to textarea
     * @param fileName name of file
     * @param startWord word in the file, where to start reading
     * @param endWord word in the file, where to stop reading
     */
    public static void readFileAndPrint(String fileName, String startWord, String endWord){

        String textToPrint = Files.ReadFile(fileName, startWord, endWord);
        int speed;

        if (fileName.contains("Datapad") || fileName.contains("Console")) {
            speed = DataPrintSpeed;
        }
        else{
            speed = StoryPrintSpeed;
        }

        Print(textToPrint, speed);
    }

    /**
     * @description Function prints given string with delay, delay given as parameter
     * @param toPrint value as string
     * @param speed value as integer, sets speed for delay
     * @IMPLEMENT Char-by-char printing!!!!
     */
    public static void Print(String toPrint, int speed){
        
        char[] toPrintArray = toPrint.toCharArray();

        for (int i = 0; i < toPrint.length(); i++) {
            centerContainer.appendText(Character.toString(toPrintArray[i]));

            if (i == toPrint.length() - 1) {
                centerContainer.appendText("\n");
            }
        }
    }

}

