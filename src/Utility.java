public class Utility {
    
    public static String cantDoThat = "Jaxon: I cant do that right now\n"; //For Switch case defaults
    public static String areaClear = "Jaxon: I have cleared that area\n";
    public static String lockerIsEmpty = "Locker is empty.\n";
    public static String isRead = "Jaxon: I already read that.\n";
    public static String activateConsole = "You press the button on the console. The terminal flickers and hums to life, displaying a series of messages:\n";
    public static String activateDatapad = "You pick up the datapad. It's an old model, slightly scratched. You wonder if it holds any important messages...\n";




    //Speed parameters for Utility.Print function, sets speed how fast to "type" text
    public static int StoryPrintSpeed = 0; 
    public static int DatapadPrintSpeed = 20; 
    public static int ActionSpeed = 15;


    //Experience points got from "LoreItems"
    public static final int LoreItemEXP = 25;
    public static final int LootItemEXP = 5;


    //Function that sets parameter String to character array, prints characters with delay, parameter speed
    public static void Print(String toPrint, int speed){

        char[] toPrintArray = toPrint.toCharArray();
        for (int i = 0; i < toPrint.length(); i++) {
            System.out.print(toPrintArray[i]);

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Delays program after printing text 500ms
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
