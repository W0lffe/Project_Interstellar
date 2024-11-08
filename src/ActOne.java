import java.util.Scanner;
public class ActOne {
    
    private static final String file = "ActOne/ActOne.txt";
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
                        checkedLocker = true;
                        break;
                    }
                    else{
                        Utility.Print("Locker is empty\n", Utility.ActionSpeed);
                        break;
                    }
                case "C":
                    if (!readDatapad) {
                        Utility.Print("You press the button on the console. The screen flickers to life, displaying a series of data: \n", Utility.ActionSpeed);
                        String console = Files.ReadFile("ActOne/Console1.txt", "" , "" );
                        Utility.Print(console, Utility.DatapadPrintSpeed);
                        player.LoreExperience();
                        Utility.Print("Jaxon: The ship is in bad shape, but at least the life support is still operational.\n", Utility.ActionSpeed);
                        readDatapad = true;
                        break;
                    }
                    else{
                        Utility.Print("Jaxon: I already read that.\n", Utility.ActionSpeed);
                        break;
                    }
                case "D":
                    Utility.Print("You exit the ship through emergency hatch.\n", Utility.ActionSpeed);
                    break;
                default:
                    Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                    break;
            }
            
        } while (!userAction.equals("D"));

        String ActOneScene2 = Files.ReadFile(file,"ACT1-SCENE2", "ACT1-SCENE3");
        Utility.Print(ActOneScene2, Utility.StoryPrintSpeed);

        NPC scout = new NPC(50, 50, "Ra'kra Scout", 35, true, Weapon.PULSE_PISTOL, null);
        NPC rookie = new NPC(50, 50, "Rak'ra Rookie", 50, true, Weapon.PULSE_RIFLE, null);

        Combat.FightMenu(player, scout, action);
        if(!player.isAlive()){
            //Get player Alive status after Combat
            Game.gameRunning=false; //If false (not alive), gameRunning is set to false, and player is taken back to main menu
            return;
        }
        Combat.FightMenu(player, rookie, action);
        if(!player.isAlive()){
            //Get player Alive status after Combat
            Game.gameRunning=false; //If false (not alive), gameRunning is set to false, and player is taken back to main menu
            return;
        }

        return;
        //String ActOneScene3 = Files.ReadFile(file,"ACT1-SCENE3", "ACT1-SCENE4");
        //Utility.Print(ActOneScene3, Utility.StoryPrintSpeed);






    }
}
