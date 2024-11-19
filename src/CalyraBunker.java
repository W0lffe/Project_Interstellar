/* import java.util.ArrayList;
import java.util.Scanner;

public class CalyraBunker {

    private static final String file = "ActOne/ActOne.txt";
    private static final String shipConsole = "ActOne/Console1.txt";
    private static final String armoryTerminal = "ActOne/Console2.txt";

    private static String userAction;
    private static ArrayList<NPC> EnemyList = new ArrayList<>();

    public static void InstanceOne(Player player, Scanner action) {
        boolean instance = true;

        boolean checkedLocker = false;
        boolean readConsole = false;

        String ActOneScene1 = Files.ReadFile(file, "ACT1-SCENE1", "ACT1-SCENE2");
        Utility.Print(ActOneScene1, Utility.StoryPrintSpeed);

        do {
            System.out.println("A) Show Character \nB) Take a look in the medical locker " +
                    " \nC) Inspect the flashing console \nD) Exit the ship");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    //player.Character(action);
                    break;
                case "B":
                    if (!checkedLocker) {
                        System.out.println("You open the medical locker and find: ");
                        System.out.println(Consumables.BASIC_MEDKIT.Found());

                        player.addItem(Consumables.BASIC_MEDKIT);
                        checkedLocker = true;
                        break;
                    } else {
                        Utility.Print(Utility.lockerIsEmpty, Utility.ActionSpeed);
                        break;
                    }
                case "C":
                    if (!readConsole) {
                        Utility.Print(Utility.activateConsole ,Utility.ActionSpeed);
                        String console = Files.ReadFile(shipConsole, "", "");
                        Utility.Print(console, Utility.DatapadPrintSpeed);

                        player.LoreExperience();
                        Utility.Print("Jaxon: The ship is in bad shape, but at least the life support is still operational.\n",Utility.ActionSpeed);
                        readConsole = true;
                        break;
                    } else {
                        Utility.Print(Utility.isRead, Utility.ActionSpeed);
                        break;
                    }
                case "D":
                    Utility.Print("You exit the ship through emergency hatch.\n", Utility.ActionSpeed);
                    instance = false;
                    break;
                default:
                    Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                    break;
            }

        } while (instance);

        String ActOneScene2 = Files.ReadFile(file, "ACT1-SCENE2", "ACT1-SCENE3");
        Utility.Print(ActOneScene2, Utility.StoryPrintSpeed);

        EnemyList.add(new NPC(50, 50, "Ra'kra Scout", 35, true, Weapon.PULSE_PISTOL, null));
        EnemyList.add(new NPC(50, 50, "Rak'ra Rookie", 50, true, Weapon.PULSE_RIFLE, null));

        Combat.FightMenu(player, EnemyList);
        if (!player.isAlive()) {
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

        Combat.FightMenu(player, EnemyList);
        if (!player.isAlive()) {
            return;
        }

        BunkerArmory(player, action);
    }

    private static void BunkerArmory(Player player, Scanner action) {
        boolean instance = true;
        //progress flags
        String firstTimeFlag = "BunkerArmory";
        String rackLooted = "ArmoryRackLooted";
        String cabinetUnlocked = "ArmoryCabinetUnlocked";
        String terminalRead = "ArmoryTerminalRead";
        String cabinetStatus = "Jaxon: There is no way I can open this.\n";

        boolean firstTime = !player.flagExists(firstTimeFlag);

        if (firstTime) {
            String ActOneScene4 = Files.ReadFile(file, "ACT1-SCENE4", "ACT1-SCENE5");
            Utility.Print(ActOneScene4, Utility.StoryPrintSpeed);
            player.addProgressFlag(new ProgressFlags("BunkerArmory", true));
        }

        do {
            System.out.println(
                    "A) Show Character \nB) Take a look at the cabinet \nC) Inspect old terminal \nD) Inspect the weapon rack \nE) Go back to Main Room");
            userAction = Validation.UserInput(action);

            if (player.flagExists(cabinetUnlocked)) {
                cabinetStatus = Utility.lockerIsEmpty;
            }

            switch (userAction) {
                case "A":
                    //player.Character(action);
                    break;
                case "B":
                    if (player.getPlayerSkills().contains(Skills.Lockpicking) && !player.flagExists(cabinetUnlocked)) {
                        System.out.println("You unlock the cabinet and find: ");
                        System.out.println(Consumables.ADRENAL_SHOT.Found());
                        System.out.println(Consumables.ADRENAL_SHOT.Found());
                        System.out.println(Consumables.ADRENAL_SHOT.Found());
                        System.out.println(Consumables.ADVANCED_MEDKIT.Found());

                        player.addItem(Consumables.ADRENAL_SHOT);
                        player.addItem(Consumables.ADRENAL_SHOT);
                        player.addItem(Consumables.ADRENAL_SHOT);
                        player.addItem(Consumables.ADVANCED_MEDKIT);
                        player.addProgressFlag(new ProgressFlags(cabinetUnlocked, true));
                        break;
                    } else {
                        Utility.Print(cabinetStatus, Utility.ActionSpeed);
                        break;
                    }
                case "C":
                    if (!player.flagExists(terminalRead)) {
                        Utility.Print("You press the button on the console. The terminal flickers and hums to life, displaying a series of messages:\n", Utility.ActionSpeed);
                        String terminal = Files.ReadFile(armoryTerminal, null, null);
                        Utility.Print(terminal, Utility.DatapadPrintSpeed);

                        player.LoreExperience();
                        player.addProgressFlag(new ProgressFlags(terminalRead, true));
                        break;
                    } else {
                        Utility.Print(Utility.isRead, Utility.ActionSpeed);
                        break;
                    }
                case "D":
                    if (!player.flagExists(rackLooted)) {
                        System.out.println("In the weapon rack you find: ");
                        System.out.println(Weapon.LASER_RIFLE);
                        player.addItem(Weapon.LASER_RIFLE);

                        player.addProgressFlag(new ProgressFlags(rackLooted, true));
                        break;
                    } else {
                        Utility.Print("Only broken weapons remaining..\n", Utility.ActionSpeed);
                        break;
                    }
                case "E":
                    Utility.Print("You go back to Main Room\n", Utility.ActionSpeed);

                    if (player.flagExists(firstTimeFlag) &&
                        player.flagExists(cabinetUnlocked) &&
                        player.flagExists(terminalRead) &&
                        player.flagExists(rackLooted) ){
                            player.addProgressFlag(new ProgressFlags("BunkerArmoryClear", true));
                            Utility.Print("Jaxon: I think I've found everything useful here.\n", Utility.ActionSpeed);
                    }

                    instance = false;
                    BunkerMainRoom(player, action);
                    break;
                default:
                    Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                    break;
            }
        } while (instance);
    }

    public static void BunkerMainRoom(Player player, Scanner action) {
        boolean instance = true;

        String BunkerMain = "BunkerMain";
        boolean firstTime = !player.flagExists(BunkerMain); // if player flag doesnt exist, set false, if found set true
        String goingOut = "Jaxon: Heading out!\n";

        if (!player.flagExists("PathToColony")) {
            goingOut = "Jaxon: Lets see what is out there\n";
        }

        if (firstTime) {
            String ActOneScene5 = Files.ReadFile(file, "ACT1-SCENE5", "ACT1-SCENE6");
            Utility.Print(ActOneScene5, Utility.StoryPrintSpeed);
            player.addProgressFlag(new ProgressFlags(BunkerMain, true));
            //player.setHealth(player.getMaxHealth());
            // RestoreHealth and Savegame logic here
        }

        do {
            System.out.println("A) Show Character \nB) Rest in the cozy bunkbed \nC) Head out \nD) Go to Armory");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    //player.Character(action);
                    break;
                case "B":
                    Utility.Print("Jaxon: Im gonna rest a while\n", Utility.ActionSpeed);
                    // save and health restore logic here
                    break;
                case "C":
                    instance = false;
                    Utility.Print(goingOut, Utility.ActionSpeed);
                    PathToColony(player, action);
                    break;
                case "D":
                    if (!player.flagExists("BunkerArmoryClear")) {
                        Utility.Print("You go to Armory\n", Utility.ActionSpeed);
                        BunkerArmory(player, action);
                        break;
                    }
                    else{
                        Utility.Print(Utility.areaClear, Utility.ActionSpeed);
                        break;
                    }
                default:
                    Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                    break;
            }

        } while (instance && player.isAlive());
    }

    public static void PathToColony(Player player, Scanner action) {
        boolean instance = true;

        String PathToColony = "PathToColony";
        boolean firstTime = !player.flagExists(PathToColony);

        if (firstTime) {
            String ActOneScene6 = Files.ReadFile(file, "ACT1-SCENE6", "ACT1-SCENE7");
            Utility.Print(ActOneScene6, Utility.StoryPrintSpeed);

            EnemyList.add(new NPC(50, 50, "Bandit", 25, true, Weapon.LASER_PISTOL, null));
            EnemyList.add(new NPC(75, 75, "Bandit Leader", 40, true, Weapon.PULSE_RIFLE, null));
            Combat.FightMenu(player, EnemyList);
            if (!player.isAlive()) {
                return;
            }
            player.addProgressFlag(new ProgressFlags(PathToColony, true));

            String ActOneScene7 = Files.ReadFile(file, "ACT1-SCENE7", "ACT1-SCENE8");
            Utility.Print(ActOneScene7, Utility.StoryPrintSpeed);
        }
       
        do {
            System.out.println("A) Show Character \nB) Go West \nC) Go South \nD) Go North \nE) Go back to Bunker");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    //player.Character(action);
                    break;
                case "B":
                    Utility.Print("Jaxon: Lets find ourselves a ship.\n", Utility.ActionSpeed);
                    // Go west SPACEPORT
                    break;
                case "C":
                    Utility.Print("Jaxon: Lets see if I find any answers.\n", Utility.ActionSpeed);
                    // Go south ENEMY BASE
                    break;
                case "D":
                    if (!player.flagExists("CalyraCaveClear")) {
                        
                        if (!player.flagExists("CalyraCaveFound")) {
                            Utility.Print("Jaxon: I wonder what is there.\n", Utility.ActionSpeed);
                        }
                        else{
                            Utility.Print("Jaxon: To the nasty cave I go!\n", Utility.ActionSpeed);
                        }
                        CalyraCave.Cave(player, action);
                        break;
                    }
                    else{
                        Utility.Print(Utility.areaClear, Utility.ActionSpeed);
                        break;
                    }
                case "E":
                    Utility.Print("You go back to Bunker\n", Utility.ActionSpeed);
                    return;
                default:
                    Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                    break;
            }
        } while (instance && player.isAlive());

    }
}


 */