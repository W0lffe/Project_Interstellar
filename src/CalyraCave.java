import java.util.ArrayList;
import java.util.Scanner;

public class CalyraCave {
    
    private static String userAction;
    private static ArrayList<NPC> EnemyList = new ArrayList<>();
    private static final String file = "ActOne/Cave.txt";
    private static final String datapadFile = "ActOne/Datapad1.txt";


    public static void Cave(Player player, Scanner action){
        String cave = "CalyraCaveFound";
        boolean firstTime = !player.flagExists(cave);
        String stealthStatus = "The Alpha stands tall, its piercing gaze sweeping over you, sizing you up. Cant get around.\n";

        Weapon claws = new Weapon("Claws", "Creature Attack", 20, 24, "Sharp claws", 1);

        if (firstTime) {
            String caveIntro = Files.ReadFile(file, "CAVE1", "CAVE2");
            Utility.Print(caveIntro, Utility.StoryPrintSpeed);

            Weapon Stinger = new Weapon("Stinger", "Creature Attack", 18, 28, "Venomous stinger", 1);
            EnemyList.add(new NPC(75, 75, "Fangdweller", 100, true, Stinger, null));
            EnemyList.add(new NPC(75, 75, "Fangdweller", 100, true, claws, null));
            EnemyList.add(new NPC(75, 75, "Fangdweller", 100, true, claws, null));

            Combat.FightMenu(player, EnemyList, action);
            if (!player.isAlive()) {
                return;
            }

            player.addProgressFlag(new ProgressFlags(cave, true));

            String cave2 = Files.ReadFile(file, "CAVE2", "CAVE3");
            Utility.Print(cave2, Utility.StoryPrintSpeed);
        }


        do {

            if (player.flagExists("CalyraCaveStealth")) {
                stealthStatus = "Jaxon: I have already been at the corpse, only Alpha remaining...\n";
            }
            
            System.out.println("A) Show Character \nB) Attack Alpha \nC) Try to Sneak past it \nD) Leave the Cave");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    //player.Character(action);
                    break;
                case "B":
                    EnemyList.add(new NPC(300, 300, "Fangdweller Alpha", 250, true, claws, null));
                    Combat.FightMenu(player, EnemyList, action);
                    if (!player.isAlive()) {
                        return;
                    }
                    Utility.Print("Jaxon: Alpha down. Guess he wasn't so invincible after all.\n", Utility.ActionSpeed);
                    player.addProgressFlag(new ProgressFlags("CalyraCaveClear", true));

                    if (!player.flagExists("CalyraCaveStealth")) {
                        Utility.Print("Jaxon: Now lets see what we find", Utility.ActionSpeed);
                        System.out.println("You take a look at the corpse and its surroundings, you find: \n");
    
                        String datapad = Files.ReadFile(datapadFile, null, null);
                        Utility.Print(datapad, Utility.DatapadPrintSpeed);
                        player.LoreExperience();
                        
                        System.out.println(Weapon.HYPERSONIC_REPEATER);
                        player.addItem(Weapon.HYPERSONIC_REPEATER);
    
                        System.out.println(Consumables.ADVANCED_MEDKIT);
                        player.addItem(Consumables.ADVANCED_MEDKIT);
    
                        System.out.println(Consumables.BASIC_MEDKIT);
                        player.addItem(Consumables.BASIC_MEDKIT);
                    }
                  
                    Utility.Print("Jaxon: Now lets get out of this smelling cave..\n", Utility.ActionSpeed);
                    return;
                case "C":
                    if (player.getPlayerSkills().contains(Skills.Stealth) && !player.flagExists("CalyraCaveStealth")) {
                        Utility.Print("Jaxon: Here goes nothing..\nYou reach the body. \n", Utility.ActionSpeed);
                        Utility.Print("Jaxon: Now lets see what we find", Utility.ActionSpeed);
                        System.out.println("You take a look at the corpse and its surroundings, you find: ");
    
                        String datapadfile = Files.ReadFile(datapadFile, null, null);
                        Utility.Print(datapadfile, Utility.DatapadPrintSpeed);
                        player.LoreExperience();
                        
                        System.out.println(Weapon.HYPERSONIC_REPEATER);
                        player.addItem(Weapon.HYPERSONIC_REPEATER);
    
                        System.out.println(Consumables.ADVANCED_MEDKIT);
                        player.addItem(Consumables.ADVANCED_MEDKIT);
    
                        System.out.println(Consumables.BASIC_MEDKIT);
                        player.addItem(Consumables.BASIC_MEDKIT);

                        player.addProgressFlag(new ProgressFlags("CalyraCaveStealth", true));
                        Utility.Print("Jaxon: Now lets get out of this smelling cave..\n", Utility.ActionSpeed);
                        return;
                    }
                    else{
                        Utility.Print(stealthStatus, Utility.ActionSpeed);
                        break;
                    }
                case "D":
                    Utility.Print("Jaxon: A fight for another day.\n", Utility.ActionSpeed);
                    return;
                default:
                    Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                    break;
            }
        } while (player.isAlive());


    }
}
