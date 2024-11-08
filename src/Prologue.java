import java.util.ArrayList;
import java.util.Scanner;

public class Prologue {
    
    private static String userAction;
    private static final String storyFile = "Prologue/Prologue.txt"; //File wich from program reads story
    private static ArrayList<NPC> EnemyList = new ArrayList<>();

    public static void Start(Player player, Scanner action){
        boolean SCENE1 = true;
        boolean checkedLocker = false;
        boolean readDatapad = false;
        String scene1 = Files.ReadFile(storyFile, "PROLOGUE", "SCENE2"); //Read file to string, starts from word PROLOGUE, ends to word SCENE2
        Utility.Print(scene1, Utility.StoryPrintSpeed); //Print story
 
        //Enter to first scene

        do {
            System.out.println("A) Character\nB) Take a look in the locker\nC) Inspect datapad\nD) Go outside");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    player.Character(action); //Show character info
                    break;
                case "B":
                    //Add items to inventory
                    if (!checkedLocker) {
                        System.out.println("You look in the locker and find: \n" + Weapon.LASER_PISTOL + "\n" + 
                                        Consumables.SPACE_SODA + "\n" + Consumables.BASIC_MEDKIT);
                        player.addItem(Weapon.LASER_PISTOL);
                        player.addItem(Consumables.SPACE_SODA);
                        player.addItem(Consumables.BASIC_MEDKIT);
                        checkedLocker = true;
                        break;
                    }
                    else{
                        Utility.Print("Locker is empty.\n", Utility.ActionSpeed);
                        break;
                    }
                case "C":
                    //Read datapad and get experience
                    if(!readDatapad){
                        Utility.Print("You pick up the datapad. It's an old model, slightly scratched. You wonder if it holds any important messages...\n", Utility.ActionSpeed);
                        String datapad = Files.ReadFile("Prologue/Datapad1.txt",null,null);
                        Utility.Print(datapad, Utility.DatapadPrintSpeed);
                        player.LoreExperience();
                        readDatapad = true;
                        break;
                    }
                    else{
                        Utility.Print("Jaxon: I already read that.\n", Utility.ActionSpeed);
                        break;
                    }
                case "D":
                    //Cant proceed before player has looked in the locker
                    if(checkedLocker) {
                        SCENE1 = false;
                        break;
                    }
                    else{
                        Utility.Print("Jaxon: Maybe I should take a look in my locker first.\n", Utility.ActionSpeed);
                        break;
                    }
                default:
                    Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                    break;
            }
        } while (SCENE1);

        //Enter to second scene
        Scene2(player, action);
    }

    private static void Scene2(Player player, Scanner action){
        
        //Create NPC target
        EnemyList.add(new NPC(5, 5, "Target", 20, true, Weapon.NONE, null));

        boolean SCENE2 = true;
        String scene2 = Files.ReadFile(storyFile, "SCENE2", "S2-OPTION1"); //Read story to string
        Utility.Print(scene2, Utility.StoryPrintSpeed);  //print story

        do {
            System.out.println("A) Go with Teth and Jaxer \nB) Not now");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    String option1 = Files.ReadFile(storyFile, "S2-OPTION1", "S2-OPTION1,2");
                    Utility.Print(option1, Utility.StoryPrintSpeed);  
                    Combat.FightMenu(player, EnemyList, action);  //Fight with NPC
                    String s2o1 = Files.ReadFile(storyFile, "S2-OPTION1,2", "S2-OPTION2");
                    Utility.Print(s2o1, Utility.StoryPrintSpeed);
                    SCENE2 = false;
                    break;
                case "B":
                    String option2 = Files.ReadFile(storyFile, "S2-OPTION2", "SCENE3");
                    Utility.Print(option2, Utility.StoryPrintSpeed);    
                    SCENE2 = false;
                    break;
                default:
                    Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
            }
        } while (SCENE2);
        
        //Enter to third scene
        Scene3(player, action);
    }

    private static void Scene3(Player player, Scanner action){

        //Create 2 NPC
        EnemyList.add(new NPC(50, 50, "Rak'ra Rookie", 50, true, Weapon.PULSE_PISTOL, null));
        EnemyList.add(new NPC(60, 60, "Rak'ra Brute", 75, true, Weapon.PULSE_RIFLE, null));
        NPC brute = EnemyList.get(1);
        boolean SCENE3 = true;
      
        do {
            System.out.println("A) Character\nB) Fight the Rak'ra \nC) Try to run over to Teth \nD) Try to run away");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    player.Character(action); //Show Character info
                    break;
                case "B":
                    Utility.Print("You will fight the Rak'ra\n", Utility.ActionSpeed);
                    Combat.FightMenu(player, EnemyList, action); //Fight with NPC
                    if(!player.isAlive()){
                        //Get player Alive status after Combat
                        Game.gameRunning=false; //If false (not alive), gameRunning is set to false, and player is taken back to main menu
                        return;
                    }
                    break;
                case "C":
                    String caseC = "You try to get over to Teth. But " + brute.getName() + " spots you and shoots at you, dealing " + 
                                        brute.getEquipped().getMinDamage() + " damage!\n";
                    Utility.Print(caseC, Utility.ActionSpeed);
                    player.takeDamage(brute.getEquipped().getMinDamage());
                    Utility.Print("Forcing you back to cover\n", Utility.ActionSpeed);
                    break;
                default:
                    Utility.Print("Jaxon: I need to help Teth and Jaxer!\n", Utility.ActionSpeed);
                    break;
            }
        } while (!userAction.equals("B"));

        String scene3 = Files.ReadFile(storyFile, "SCENE3", "S3-OPTION1");
        Utility.Print(scene3, Utility.StoryPrintSpeed);

        do {
            System.out.println("A) Go to Jaxer and see if you can help him recover \nB) Help Teth");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    //This CASE is not available if player inventory doesnt contain Consumable medkit
                    if(player.getInventory().contains(Consumables.BASIC_MEDKIT)){
                        Utility.Print("You go to Jaxer\n", Utility.ActionSpeed);
                        String s3o1 = Files.ReadFile(storyFile, "S3-OPTION1", "S3-OPTION2");
                        Utility.Print(s3o1, Utility.StoryPrintSpeed);
                        userAction = "B";
                        break;
                    }
                    else{
                        Utility.Print("Jaxon: I need a medkit to help Jaxer...\n", Utility.ActionSpeed);
                        break;
                    }
                case "B":
                    String s3o2 = Files.ReadFile(storyFile, "S3-OPTION2", "S3-PART2");
                    Utility.Print(s3o2, Utility.StoryPrintSpeed);
                    break;
                default:
                    Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                    break;
            }
        } while (!userAction.equals("B"));


        String s3part2 = Files.ReadFile(storyFile, "S3-PART2", "S3P2-OPTION1");
        Utility.Print(s3part2, Utility.StoryPrintSpeed);

        do {
            System.out.println("A) Kill the engineer \nB) Keep going");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    NPC engineer = new NPC(5, 5, "Crew Member", 20, true, Weapon.NONE, null);
                    Combat.Attack(player, engineer); //Fight with NPC
                    String p2o1 = Files.ReadFile(storyFile, "S3P2-OPTION1", "S3P2-OPTION2");
                    Utility.Print(p2o1, Utility.StoryPrintSpeed);
                    player.addItem(Consumables.BASIC_MEDKIT); //Add medkits to inventory
                    player.addItem(Consumables.BASIC_MEDKIT);
                    SCENE3 = false;
                    break;
                case "B":
                    EnemyList.add(new NPC(25, 25, "Ra'kra Scout", 35, true, Weapon.PULSE_PISTOL, null));
                    EnemyList.add(new NPC(100, 100, "Rak'ra Officer", 75, true, Weapon.PULSE_RIFLE, null));
                    String p2o2 = Files.ReadFile(storyFile, "S3P2-OPTION2", "FINAL");
                    Utility.Print(p2o2, Utility.StoryPrintSpeed);
                    player.addItem(Consumables.BASIC_MEDKIT); //Add medkits to inventory
                    player.addItem(Consumables.BASIC_MEDKIT);
                    Combat.FightMenu(player, EnemyList, action); //Fight with NPC
                    if(!player.isAlive()){
                        Game.gameRunning=false; //If false (not alive), gameRunning is set to false, and player is taken back to main menu
                        return;
                    }
                    SCENE3 = false;
                    break;
                default:
                    Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                    break;
            }
        } while (SCENE3);

        Final(player, action);
    }

    private static void Final(Player player, Scanner action){
        boolean FinalScene = true;

        //Set player health to max
        player.setHealth(player.getMaxHealth());
        String finalScene = Files.ReadFile(storyFile, "FINAL", "FINAL-PART2");
        Utility.Print(finalScene, Utility.StoryPrintSpeed);

        //Create NPC
        EnemyList.add(new NPC(200, 200, "Ka'tar", 200, true, Weapon.PULSE_PISTOL, null));
        NPC Boss = EnemyList.get(0);

        Combat.FightMenu(player, EnemyList, action); //Fight the boss
        if(!player.isAlive()){
            Game.gameRunning=false; //If false (not alive), gameRunning is set to false, and player is taken back to main menu
            return;
        }

        String finalPart2 = Files.ReadFile(storyFile, "FINAL-PART2", "END");
        Utility.Print(finalPart2, Utility.StoryPrintSpeed);

        do {
            System.out.println("A) Eliminate Ka'tar \nB) Leave the Station with Teth");
            userAction = Validation.UserInput(action);
            switch (userAction) {
                case "A":
                    Utility.Print("You eliminate Ka'tar and grab his weapon.\n", Utility.ActionSpeed);
                    player.addItem(Boss.getEquipped()); //Add item that NPC Boss has equipped
                    Utility.Print("You make your way to the ship and leave the station.\n", Utility.ActionSpeed);
                    FinalScene = false;
                    break;
                case "B":
                    Utility.Print("You make your way to the ship and leave the station.\n", Utility.ActionSpeed);
                    FinalScene = false;
                    break;
                default:
                    Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                    break;
            }
        } while (FinalScene);

        String finalEnd = Files.ReadFile(storyFile, "END", "PROLOGUE-END");
        Utility.Print(finalEnd, Utility.StoryPrintSpeed);

    }

    //END OF PROLOGUE
}
