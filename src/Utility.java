public class Utility {
    
    public static String cantDoThat = "Jaxon: I cant do that right now\n"; //For Switch case defaults

    //Speed parameters for Utility.Print function, sets speed how fast to "type" text
    public static int StoryPrintSpeed = 5; 
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
