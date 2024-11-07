import java.util.Scanner;
public class ActOne {
    
    private static final String file = "ActOne.txt";
    private static String userAction;

    public static void InstanceOne(Player player, Scanner action){
        boolean checkedLocker = false;
        boolean readDatapad = false;

        //!!!Implement logic that this only happens first time player comes here!!!
        String actOneOpening = Files.ReadFile(file, "ACT1-SCENE1", "ACT1-SCENE2");
        Utility.Print(actOneOpening, Utility.StoryPrintSpeed);

        do {
            System.out.println("A) Show Character \nB) Take a look in the medical locker " + 
                                " \nC) Inspect the flashing console \nD) Exit the ship");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    player.Character(action);
                    break;
                case "B":
                    if (!checkedLocker) {
                        System.out.println("You open the medical locker and find: ");
                        System.out.println(Consumables.BASIC_MEDKIT);
                        player.addItem(Consumables.BASIC_MEDKIT);
                        player.LootExperience();
                        checkedLocker = true;
                        break;
                    }
                    else{
                        System.out.println("Locker is empty");
                        break;
                    }
                case "C":
                    if (!readDatapad) {
                        System.out.println("You press the button on the console. The screen flickers to life, displaying a series of data: ");
                        String console = Files.ReadFile("Console1.txt", "" , "" );
                        Utility.Print(console, Utility.DatapadPrintSpeed);
                        player.LoreExperience();
                        System.out.println("Jaxon: The ship is in bad shape, but at least the life support is still operational.");
                        readDatapad = true;
                        break;
                    }
                    else{
                        System.out.println("Jaxon: I already read that.");
                        break;
                    }
                  
                default:
                    System.out.println("You exit the ship through emergency hatch.");
                    break;
            }
            
        } while (!userAction.equals("D"));



    }
}
