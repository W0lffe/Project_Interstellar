import java.util.Scanner;

public class Levels {
   
    public static void Prologue(Player player, Scanner action){
        String userAction;

        String intro = "WRITE VERY ATMOSPHERIC DESCRIPTION HERE";
        System.out.println(intro);

        //Enter to first scene
        String scene1 = "Around you see this and that. You can do this and that";
        System.out.println(scene1);

        do {
            System.out.println("A) Character\nB) that");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    player.Character(action);
                    break;
                case "B":
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
}
