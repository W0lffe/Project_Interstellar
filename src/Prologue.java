import java.util.Scanner;

public class Prologue {
    
    private static String userAction;
    private static final String file = "Prologue.txt";

    public static void Start(Player player, Scanner action){
        boolean SCENE1 = true;
        String scene1 = Files.ReadFile(file, "PROLOGUE", "SCENE2");
        Utility.Print(scene1, Utility.StoryPrintSpeed);
 
        //Enter to first scene

        do {
            System.out.println("A) Character\nB) Take a look in the locker\nC) Inspect datapad\nD) Go outside\n");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    player.Character(action);
                    break;
                case "B":
                    System.out.println("You look in the locker and find: \n" + 
                    "[" + Weapon.LASER_PISTOL.getItem() + " - " + Weapon.LASER_PISTOL.getDescription() + "]\n" +
                    "[" + Consumables.SPACE_SODA.getItem() + " - " + Consumables.SPACE_SODA.getDescription()+ "]\n" +
                    "[" + Consumables.BASIC_MEDKIT.getItem() + " - " + Consumables.BASIC_MEDKIT.getDescription() + "]\n");
                    player.addItem(Weapon.LASER_PISTOL);
                    player.addItem(Consumables.SPACE_SODA);
                    player.addItem(Consumables.BASIC_MEDKIT);
                    break;
                case "C":
                    System.out.println("You pick up the datapad. It's an old model, slightly scratched. You wonder if it holds any important messages...");
                    String datapad = Files.ReadFile("Datapad1.txt",null,null);
                    Utility.Print(datapad, Utility.DatapadPrintSpeed);
                    break;
                case "D":
                    if(player.getInventory().contains(Weapon.LASER_PISTOL)) {
                        System.out.println("You decide to head outside, ready to face whatever the day brings.");
                        SCENE1 = false;
                        break;
                    }
                    else{
                        System.out.println("Jaxon: Maybe I should take a look in my locker first.");
                        break;
                    }
                default:
                    System.out.println(Utility.cantDoThat);
            }
        } while (SCENE1);

        //Enter to second scene
        Scene2(player, action);
    }

    private static void Scene2(Player player, Scanner action){
        
        NPC target = new NPC(5, 5, "Target", 20, true, null, null);

        boolean SCENE2 = true;
        String scene2 = Files.ReadFile(file, "SCENE2", "S2-OPTION1");
        Utility.Print(scene2, Utility.StoryPrintSpeed);    

        do {
            System.out.println("A) Go with Teth and Jaxer \nB) Not now");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    String option1 = Files.ReadFile(file, "S2-OPTION1", "S2-OPTION1,2");
                    Utility.Print(option1, Utility.StoryPrintSpeed);  
                    Combat.FightMenu(player, target, action);  
                    String s2o1 = Files.ReadFile(file, "S2-OPTION1,2", "S2-OPTION2");
                    Utility.Print(s2o1, Utility.StoryPrintSpeed);
                    SCENE2 = false;
                    break;
                case "B":
                    String option2 = Files.ReadFile(file, "S2-OPTION2", "SCENE3");
                    Utility.Print(option2, Utility.StoryPrintSpeed);    
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

        NPC rookie = new NPC(50, 50, "Rak'ra Rookie", 50, true, Weapon.PULSE_PISTOL, null);
        NPC brute = new NPC(60, 60, "Rak'ra Brute", 75, true, Weapon.PULSE_RIFLE, null);
        boolean SCENE3 = true;
      
        do {
            System.out.println("A) Character\nB) Fight the Rak'ra \nC) Try to run over to Teth \nD) Try to run away");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    player.Character(action);
                    break;
                case "B":
                    System.out.println("You will fight the Rak'ra");
                    Combat.FightMenu(player, rookie, action);
                    if(!player.isAlive()){
                        Game.gameRunning=false;
                        return;
                    }
                    Combat.FightMenu(player, brute, action);
                    if(!player.isAlive()){
                        Game.gameRunning=false;
                        return;
                    }
                    break;
                case "C":
                    System.out.println("You try to get over to Teth. But");
                    System.out.println(brute.getName() + " spots you and shoots at you, dealing " + brute.getEquipped().getMinDamage() + " damage!");
                    player.takeDamage(brute.getEquipped().getMinDamage());
                    System.out.println("Forcing you back to cover\n");
                    break;
                default:
                    System.out.println("Jaxon: I need to help Teth and Jaxer!");
                    break;
            }
        } while (!userAction.equals("B"));

        String scene3 = Files.ReadFile(file, "SCENE3", "S3-OPTION1");
        Utility.Print(scene3, Utility.StoryPrintSpeed);

        do {
            System.out.println("A) Go to Jaxer and see if you can help him recover \nB) Help Teth");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    if(player.getInventory().contains(Consumables.BASIC_MEDKIT)){
                        System.out.println("You go to Jaxer");
                        String s3o1 = Files.ReadFile(file, "S3-OPTION1", "S3-OPTION2");
                        Utility.Print(s3o1, Utility.StoryPrintSpeed);
                        userAction = "B";
                        break;
                    }
                    else{
                        System.out.println("Jaxon: I need a medkit to help Jaxer...");
                        break;
                    }
                case "B":
                    String s3o2 = Files.ReadFile(file, "S3-OPTION2", "S3-PART2");
                    Utility.Print(s3o2, Utility.StoryPrintSpeed);
                    break;
                default:
                    System.out.println(Utility.cantDoThat);
                    break;
            }
        } while (!userAction.equals("B"));


        String s3part2 = Files.ReadFile(file, "S3-PART2", "S3P2-OPTION1");
        Utility.Print(s3part2, Utility.StoryPrintSpeed);

        NPC engineer = new NPC(5, 5, "Crew Member", 20, true, null, null);
        NPC scout = new NPC(25, 25, "Ra'kra Scout", 35, true, Weapon.PULSE_PISTOL, null);
        NPC officer = new NPC(100, 100, "Rak'ra Officer", 75, true, Weapon.PULSE_RIFLE, null);
    
        do {
            System.out.println("A) Kill the engineer \nB) Keep going");
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    Combat.FightMenu(player, engineer, action);
                    String p2o1 = Files.ReadFile(file, "S3P2-OPTION1", "S3P2-OPTION2,2");
                    Utility.Print(p2o1, Utility.StoryPrintSpeed);
                    player.addItem(Consumables.BASIC_MEDKIT);
                    player.addItem(Consumables.BASIC_MEDKIT);
                    SCENE3 = false;
                    break;
                case "B":
                    String p2o2 = Files.ReadFile(file, "S3P2-OPTION2,2", "FINAL");
                    Utility.Print(p2o2, Utility.StoryPrintSpeed);
                    player.addItem(Consumables.BASIC_MEDKIT);
                    player.addItem(Consumables.BASIC_MEDKIT);
                    Combat.FightMenu(player, scout, action);
                    if(!player.isAlive()){
                        Game.gameRunning=false;
                        return;
                    }
                    Combat.FightMenu(player, officer, action);
                    if(!player.isAlive()){
                        Game.gameRunning=false;
                        return;
                    }
                    SCENE3 = false;
                    break;
                default:
                    System.out.println(Utility.cantDoThat);
                    break;
            }
        } while (SCENE3);

        Final(player, action);
    }

    private static void Final(Player player, Scanner action){
        boolean FinalScene = true;

        player.setHealth(player.getMaxHealth());
        String finalScene = Files.ReadFile(file, "FINAL", "FINAL-PART2");
        Utility.Print(finalScene, Utility.StoryPrintSpeed);

        NPC Boss = new NPC(200, 200, "Ka'tar", 200, true, Weapon.PULSE_PISTOL, null);

        Combat.FightMenu(player, Boss, action);
        if(!player.isAlive()){
            Game.gameRunning=false;
            return;
        }

        String finalPart2 = Files.ReadFile(file, "FINAL-PART2", "END");
        Utility.Print(finalPart2, Utility.StoryPrintSpeed);

        do {
            System.out.println("A) Eliminate Ka'tar \nB) Leave the Station with Teth");
            userAction = Validation.UserInput(action);
            switch (userAction) {
                case "A":
                    System.out.println("You eliminate Ka'tar. You grab his weapon.");
                    player.addItem(Boss.getEquipped());
                    FinalScene = false;
                    System.out.println("You make your way to the ship and leave the station.");
                    break;
                case "B":
                    System.out.println("You make your way to the ship and leave the station.");
                    FinalScene = false;
                    break;
                default:
                    System.out.println(Utility.cantDoThat);
                    break;
            }
        } while (FinalScene);

        String finalEnd = Files.ReadFile(file, "END", "PROLOGUE-END");
        Utility.Print(finalEnd, Utility.StoryPrintSpeed);

    }

    //END OF PROLOGUE
}
