public class Utility {
    
    public static String cantDoThat = "Jaxon: I cant do that right now";

    public static int StoryPrintSpeed = 15;
    public static int DatapadPrintSpeed = 20;

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

    }
}
