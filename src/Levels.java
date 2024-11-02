import java.util.Scanner;

public class Levels {
   
    public static void Prologue(Player player, Scanner action){
        String userAction;

       /*  String intro = Files.ReadFile("Prologue.txt", action);
        Print(intro, 50);
 */
        //Enter to first scene
        
        String scene1 = "You wake up early morning after seeing those same nightmares again.\nJaxon(mumbles): I have to find my wife...\nYou get up from the bed.";
        String scene2 = "You look around. You see your cabinet and a datapad. You can hear other people talking outside your dorm.";
        System.out.println(scene1);
        System.out.println(scene2);

        do {
            System.out.println("A) Character\nB) Take a look in the cabinet\nC) Inspect datapad\nD) Go outside");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    player.Character(action);
                    break;
                case "B":
                    System.out.println("You look in the locker and find: \n" + Items.LASER_PISTOL.getItem() + Items.SPACE_SODA.getItem());
                    player.addItem(Items.LASER_PISTOL);
                    player.addItem(Items.SPACE_SODA);
                    break;
                case "C":
                    break;
                case "D":
                    break;
                default:
                    break;
            }
        } while (true);




        
    }

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
