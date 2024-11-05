import java.util.Scanner;

public class Prologue {
    
    private static String userAction;
    private static final String file = "Prologue.txt";

    public static void Start(Player player, Scanner action){
        boolean SCENE1 = true;
        String scene1 = Files.ReadFile(file, "PROLOGUE", "S2");
        Utility.Print(scene1, 1);
 
        //Enter to first scene

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
                    String datapad = Files.ReadFile("Datapad1.txt",null,null);
                    Utility.Print(datapad, 20);
                    break;
                case "D":
                    System.out.println("You decide to head outside, ready to face whatever the day brings.");
                    SCENE1 = false;
                    break;
                default:
                    System.out.println(Utility.cantDoThat);
            }
        } while (SCENE1);

        //Enter to second scene
        Scene2(player, action);
    }

    private static void Scene2(Player player, Scanner action){
        
        boolean SCENE2 = true;
        String scene2 = Files.ReadFile(file, "S2", "S2O1");
        Utility.Print(scene2, 1);    

        do {
            System.out.println("A) Go with Teth and Jaxer \nB) Not now");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    NPC target = new NPC(5, 5, "Target", 20, true, null);
                    String option1 = Files.ReadFile(file, "S2O1", "S2O1,1");
                    Utility.Print(option1, 1);  
                    Combat.FightMenu(player, target, action);  
                    String s2o1 = Files.ReadFile(file, "S2O1,1", "S2O2");
                    Utility.Print(s2o1, 1);
                    SCENE2 = false;
                    break;
                case "B":
                    String option2 = Files.ReadFile(file, "S2O2", "S3");
                    Utility.Print(option2, 1);    
                    SCENE2 = false;
                    break;
                default:
                    System.out.println(Utility.cantDoThat);
            }
        } while (SCENE2);
        
        //Enter to third scene
        Scene3(player, action);
    }

    private static void Scene3(Player player, Scanner action){

    }





}
