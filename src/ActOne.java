import java.util.ArrayList;
import java.util.Scanner;

public class ActOne {

    private static final String file = "ActOne/ActOne.txt";
    private static final String shipConsole = "ActOne/Console1.txt";
    private static final String armoryTerminal = "ActOne/Console2.txt";

    private static String userAction;
    private static ArrayList<NPC> EnemyList = new ArrayList<>();

    public static void InstanceOne(Player player, Scanner action) {

        boolean checkedLocker = false;
        boolean readDatapad = false;

        // !!!Implement logic that this only happens first time player comes here!!!
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
                    } else {
                        Utility.Print("Locker is empty\n", Utility.ActionSpeed);
                        break;
                    }
                case "C":
                    if (!readDatapad) {
                        Utility.Print(
                                "You press the button on the console. The screen flickers to life, displaying a series of data: \n",
                                Utility.ActionSpeed);
                        String console = Files.ReadFile(shipConsole, "", "");
                        Utility.Print(console, Utility.DatapadPrintSpeed);
                        player.LoreExperience();
                        Utility.Print(
                                "Jaxon: The ship is in bad shape, but at least the life support is still operational.\n",
                                Utility.ActionSpeed);
                        readDatapad = true;
                        break;
                    } else {
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

        String ActOneScene2 = Files.ReadFile(file, "ACT1-SCENE2", "ACT1-SCENE3");
        Utility.Print(ActOneScene2, Utility.StoryPrintSpeed);

        EnemyList.add(new NPC(50, 50, "Ra'kra Scout", 35, true, Weapon.PULSE_PISTOL, null));
        EnemyList.add(new NPC(50, 50, "Rak'ra Rookie", 50, true, Weapon.PULSE_RIFLE, null));

        Combat.FightMenu(player, EnemyList, action);
        if (!player.isAlive()) {
            // Get player Alive status after Combat
            Game.gameRunning = false; // If false (not alive), gameRunning is set to false, and player is taken back
                                      // to main menu
            return;
        }

        String ActOneScene3 = Files.ReadFile(file, "ACT1-SCENE3", "ACT1-SCENE4");
        Utility.Print(ActOneScene3, Utility.StoryPrintSpeed);

        Weapon acidSpit = new Weapon("Acid Spit", "Projectile Weapon", 8, 18, "", 1);
        for (int i = 0; i < 2; i++) {
            EnemyList.add(new NPC(20, 20, "Skitter Soldier", 15, true, acidSpit, null));
        }
        EnemyList.add(new NPC(50, 50, "Skitter Guardian", 25, true, acidSpit, null));
        EnemyList.add(new NPC(100, 100, "Alpha Skitter", 50, true, acidSpit, null));

        Combat.FightMenu(player, EnemyList, action);
        if (!player.isAlive()) {
            // Get player Alive status after Combat
            Game.gameRunning = false; // If false (not alive), gameRunning is set to false, and player is taken back
                                      // to main menu
            return;
        }

        BunkerArmory(player, action);
    }

    private static void BunkerArmory(Player player,Scanner action){
        boolean cabinetLooted = false;
        boolean rackLooted = false;
        boolean terminalRead = false;

         //!!!Implement logic that this only happens first time player comes here!!!
        String ActOneScene4 = Files.ReadFile(file,"ACT1-SCENE4", "ACT1-SCENE5");
        Utility.Print(ActOneScene4, Utility.StoryPrintSpeed);

        do {
            System.out.println("A) Show Character \nB) Take a look at the cabinet \nC) Inspect old terminal \nD) Inspect the weapon rack \nE) Go back to Main Room");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    player.Character(action);
                    break;
                case "B":
                    if (player.getPlayerSkills().contains(Skills.Lockpicking) && !cabinetLooted) {
                        System.out.println("You unlock the cabinet and find: ");
                        System.out.println(Consumables.ADRENAL_SHOT);
                        System.out.println(Consumables.ADRENAL_SHOT);
                        System.out.println(Consumables.ADRENAL_SHOT);
                        System.out.println(Consumables.ADVANCED_MEDKIT);
                        player.addItem(Consumables.ADRENAL_SHOT);
                        player.addItem(Consumables.ADRENAL_SHOT);
                        player.addItem(Consumables.ADRENAL_SHOT);
                        player.addItem(Consumables.ADVANCED_MEDKIT);
                        cabinetLooted=true;
                        break;
                    }
                    else{
                        Utility.Print("Jaxon: There is no way I can open this.\n", Utility.ActionSpeed);
                        break;
                    }
                case "C":
                    if (!terminalRead) {
                        Utility.Print("You press the button on the console. The terminal flickers and hums to life, displaying a series of broken messages:\n", Utility.ActionSpeed);
                        String terminal = Files.ReadFile(armoryTerminal, null, null);
                        Utility.Print(terminal, Utility.DatapadPrintSpeed);
                        player.LoreExperience();
                        break;
                    }
                    else{
                        Utility.Print("Jaxon: I already read that.\n", Utility.ActionSpeed);
                        break;
                    }
                case "D":
                    if (!rackLooted){
                        System.out.println("In the weapon rack you find: ");
                        System.out.println(Weapon.LASER_RIFLE);
                        player.addItem(Weapon.LASER_RIFLE);
                        rackLooted=true;
                        break;
                    }
                    else{
                        Utility.Print("Only broken weapons remaining..\n", Utility.ActionSpeed);
                        break;
                    }
                case "E":
                    Utility.Print("You go back to Main Room\n", Utility.ActionSpeed);
                    //BunkerMainRoom(player, action);
                    break;
                default:
                    Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                    break;
            }
        } while (!userAction.equals("E"));
        Game.gameRunning=false;
    }
}
