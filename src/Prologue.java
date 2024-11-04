import java.util.Scanner;

public class Prologue {
   
    public static void Start(Player player, Scanner action){
        String userAction;
        boolean SCENE1 = true;
       /*  String intro = Files.ReadFile("Prologue.txt", action);
        Print(intro, 50);
 */
        //Enter to first scene
        
        String scene1 = "You wake up early morning after seeing those same nightmares again.\nJaxon(mumbles): I have to find my wife...\nYou get up from the bed." + 
        "You look around. You see your cabinet and a datapad. You can hear other people talking outside your dorm.";
        Print(scene1, 10);

        do {
            System.out.println("A) Character\nB) Take a look in the cabinet\nC) Inspect datapad\nD) Go outside");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    player.Character(action);
                    break;
                case "B":
                    System.out.println("You look in the locker and find: \n" + 
                    "-" + Items.LASER_PISTOL.getItem() + " - " + Items.LASER_PISTOL.getDescription() + "\n" +
                    "-" + Items.SPACE_SODA.getItem() + " - " + Items.SPACE_SODA.getDescription());
                    player.addItem(Items.LASER_PISTOL);
                    player.addItem(Items.SPACE_SODA);
                    break;
                case "C":
                    System.out.println("You pick up the datapad. It's an old model, slightly scratched. You wonder if it holds any important messages...");
                    String datapad = Files.ReadFile("Datapad1.txt", action);
                    Print(datapad, 20);
                    break;
                case "D":
                    System.out.println("You decide to head outside, ready to face whatever the day brings.");
                    SCENE1 = false;
                    break;
                default:
            }
        } while (SCENE1);

        boolean SCENE2 = true;

        System.out.println("You come out of the dorm room and look through the big windows. Its a sunny day out here at Veskar Station."+
        "\nYou notice fleet ships leaving the dock. Next to the windows you spot your wingmen, Jaxer and Teth." +
        "\nJaxer and Teth are having a conversation about spotted Ra'kra activity near sector nine." + 
        "");

        System.out.println("You come out of the dorm room and look through the big windows. It's a sunny day out here at Veskar Station." +
    "\nYou notice fleet ships leaving the dock, their engines humming as they ascend into the endless void." +
    "\nNext to the windows, you spot your wingmen, Jaxer and Teth, deep in conversation." +
    "\nJaxer: \"I swear, Teth, the Ra'kra have been getting bolder every day. I heard they almost breached sector nine defenses last night.\"" +
    "\nTeth nods grimly, his arms crossed. \"Yeah, and command's been keeping it quiet. They don’t want the station to panic.\"");

System.out.println("\nJaxer turns to you and grins. \"Ryker! You're up early for once.\" Teth raises an eyebrow. \"Bad dreams again?\"" +
    "\nYou nod, trying to shake off the remnants of the nightmare." +
    "\nJaxer: \"Well, at least you're awake now. We could use the extra pair of eyes out there. Rumor has it the Ra'kra scouts have been seen closer to Veskar than ever before.\"" +
    "\nTeth: \"I don't trust those scouts. They're always the start of something bigger.\"");

System.out.println("\nYou glance at the fleet ships preparing to jump into hyperspace. The sight is all too familiar." +
    "\nThe Ra'kra took everything from you, including the one person you’d give anything to find." +
    "\nThe mission to find your wife is never far from your mind, but for now, duty calls." +
    "\nJaxer claps a hand on your shoulder. \"Come on, Ryker, let's grab some fuel. We’ve got a briefing in twenty.\"" +
    "\nYou feel a surge of determination. The search isn't over, and as long as you’re breathing, there’s still hope.");


        






        
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
