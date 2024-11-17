import java.util.ArrayList;

public class Prologue2 {

    private static final String storyFile = "Prologue/Prologue.txt"; //File wich from program reads story
    private static final String datapadFile = "Prologue/Datapad1.txt";
    private static ArrayList<NPC> EnemyList = new ArrayList<>();
    private static VerticalContainer character;

    private static boolean checkedLocker = false;
    private static boolean readDatapad = false;

    public static void Start(Player player){
        character = player.Character();

        String scene1 = Files.ReadFile(storyFile, "PROLOGUE", "SCENE2"); //Read file to string, starts from word PROLOGUE, ends to word SCENE2
        Utility.Print(scene1, Utility.StoryPrintSpeed); //Print story
        //Enter to first scene

        String choices = "A) Character B) Take a look in the locker C) Inspect datapad D) Go outside";
        VerticalContainer container = new VerticalContainer(10, choices);

        HorizontalPlayerActions actions = new HorizontalPlayerActions(10, "", "Character", "Locker", "Datapad", "Go outside");
        container.getChildren().addAll(actions);
        Utility.centerContainer.getChildren().addAll(container);

        //Show Inventory
        actions.getFirstButton().setOnAction(e -> {
            
            if (!Utility.leftContainer.getChildren().contains(character)) {
                Utility.leftContainer.getChildren().add(character);
            }
            else{
                Utility.leftContainer.getChildren().remove(character);
            }
        });

        //Open Locker
        actions.getSecondButton().setOnAction(e -> {
            
            if (!checkedLocker) {
                String itemsFound = "You look in the locker and find: \n" + Weapon.LASER_PISTOL + "\n" + 
                                        Consumables.SPACE_SODA.Found() + "\n" + Consumables.BASIC_MEDKIT.Found();
                Utility.Print(itemsFound, 0);

                player.addItem(Weapon.LASER_PISTOL);
                player.addItem(Consumables.SPACE_SODA);
                player.addItem(Consumables.BASIC_MEDKIT);   

                checkedLocker = true;
            }
            else{
                Utility.Print(Utility.lockerIsEmpty, Utility.ActionSpeed);
            }
        });

        //Read Datapad
        actions.getThirdButton().setOnAction(e -> {
           
            if(!readDatapad){
                Utility.Print(Utility.activateDatapad, Utility.ActionSpeed);
                
                String datapad = Files.ReadFile(datapadFile,null,null);
                Utility.Print(datapad, Utility.DatapadPrintSpeed);
                
                player.LoreExperience();
                readDatapad = true;
            }
            else{
                Utility.Print(Utility.isRead, Utility.ActionSpeed);
            }
        });

        //Continue gameflow
        actions.getFourthButton().setOnAction(e -> {
            
            if(checkedLocker){
                Utility.centerContainer.getChildren().remove(container);
                Scene2(player);
            }
            else{
                Utility.Print("Jaxon: Maybe I should take a look in my locker first.\n", Utility.ActionSpeed);
            }
        });

      }


    private static void Scene2(Player player){

        String scene2 = Files.ReadFile(storyFile, "SCENE2", "S2-OPTION1"); //Read story to string
        Utility.Print(scene2, Utility.StoryPrintSpeed);  //print story

        String choices = "A) Go with Teth and Jaxer B) Not now";
        VerticalContainer container = new VerticalContainer(10, choices);

        HorizontalPlayerActions actions = new HorizontalPlayerActions(10,"", "Go with the guys", "Take a moment for yourself");
        container.getChildren().addAll(actions);
        Utility.centerContainer.getChildren().addAll(container);

        actions.getFirstButton().setOnAction(e -> {

            String option1 = Files.ReadFile(storyFile, "S2-OPTION1", "S2-OPTION1,2");
            Utility.Print(option1, Utility.StoryPrintSpeed);  

            EnemyList.add(new NPC(5, 5, "Target", 20, true, Weapon.NONE, null));
            //Combat.FightMenu(player, EnemyList);  //Fight with NPC

            String s2o1 = Files.ReadFile(storyFile, "S2-OPTION1,2", "S2-OPTION2");
            Utility.Print(s2o1, Utility.StoryPrintSpeed);

            Utility.centerContainer.getChildren().remove(container);
            Scene2Battle(player);
        });

        actions.getSecondButton().setOnAction(e -> {

            String option2 = Files.ReadFile(storyFile, "S2-OPTION2", "SCENE3");
            Utility.Print(option2, Utility.StoryPrintSpeed);   

            Utility.centerContainer.getChildren().remove(container);
            Scene2Battle(player);
        });

       }

    private static void Scene2Battle(Player player){

         //Create 2 NPC
        EnemyList.add(new NPC(50, 50, "Rak'ra Rookie", 50, true, Weapon.PULSE_PISTOL, null));
        EnemyList.add(new NPC(60, 60, "Rak'ra Brute", 75, true, Weapon.PULSE_RIFLE, null));
        NPC brute = EnemyList.get(1);

        String choices = "A) Character B) Fight the Rak'ra C) Try to run over to Teth D) Try to run away";
        VerticalContainer container = new VerticalContainer(10, choices);

        HorizontalPlayerActions actions = new HorizontalPlayerActions(10,"","Show Character", "Fight!", "Help your friends", "Retreat!");
        container.getChildren().addAll(actions);
        Utility.centerContainer.getChildren().addAll(container);


        actions.getFirstButton().setOnAction(e ->{
            
            if (!Utility.leftContainer.getChildren().contains(character)) {
                Utility.leftContainer.getChildren().add(character);
            }
            else{
                Utility.leftContainer.getChildren().remove(character);
            }
        });

        actions.getSecondButton().setOnAction(e -> {
            
            Utility.Print("You will fight the Rak'ra\n", Utility.ActionSpeed);
            //Combat.FightMenu(player, EnemyList); //Fight with NPC
            if (!player.isAlive()) {
                return;
            }
            //Logic here
            
            Utility.centerContainer.getChildren().remove(container);
            Scene3(player);
        });

        actions.getThirdButton().setOnAction(e -> {
           
            String caseC = "You try to get over to Teth. But " + brute.getName() + " spots you and shoots at you, dealing " + 
            brute.getEquipped().getMinDamage() + " damage!\n";
            Utility.Print(caseC, Utility.ActionSpeed);

            player.takeDamage(brute.getEquipped().getMinDamage());
            Utility.Print("Forcing you back to cover\n", Utility.ActionSpeed);
        });

        actions.getFourthButton().setOnAction(e -> {
            
            Utility.Print("Jaxon: I need to help Teth and Jaxer!\n", Utility.ActionSpeed);
        });
         
    }

    private static void Scene3(Player player){

        String scene3 = Files.ReadFile(storyFile, "SCENE3", "S3-OPTION1");
        Utility.Print(scene3, Utility.StoryPrintSpeed);

        String choices = "A) Go to Jaxer and see if you can help him recover B) Help Teth";
        VerticalContainer container = new VerticalContainer(10, choices);

        HorizontalPlayerActions actions = new HorizontalPlayerActions(10,"", "Try to help Jaxer", "Help Teth");
        container.getChildren().addAll(actions);
        Utility.centerContainer.getChildren().addAll(container);

        actions.getFirstButton().setOnAction(e -> {
            
            if(player.getInventory().contains(Consumables.BASIC_MEDKIT)){
                Utility.Print("You go to Jaxer\n", Utility.ActionSpeed);
                player.addProgressFlag(new ProgressFlags("Saved Jaxer", true));

                String s3o1 = Files.ReadFile(storyFile, "S3-OPTION1", "S3-OPTION2");
                Utility.Print(s3o1, Utility.StoryPrintSpeed);

                Utility.centerContainer.getChildren().remove(container);
                Scene3Part2(player);
            }
            else{
                Utility.Print("Jaxon: I need a medkit to help Jaxer...\n", Utility.ActionSpeed);
            }
        });

        actions.getSecondButton().setOnAction(e -> {
                
            String s3o2 = Files.ReadFile(storyFile, "S3-OPTION2", "S3-PART2");
            Utility.Print(s3o2, Utility.StoryPrintSpeed);

            Utility.centerContainer.getChildren().remove(container);
            Scene3Part2(player);
        });


     }

     private static void Scene3Part2(Player player){

        String s3part2 = Files.ReadFile(storyFile, "S3-PART2", "S3P2-OPTION1");
        Utility.Print(s3part2, Utility.StoryPrintSpeed);

        String choices = "A) Kill the engineer B) Keep going";
        VerticalContainer container = new VerticalContainer(10, choices);

        HorizontalPlayerActions actions = new HorizontalPlayerActions(10,"", "Kill the Engineer", "Keep running");
        container.getChildren().addAll(actions);
        Utility.centerContainer.getChildren().addAll(container);

        actions.getFirstButton().setOnAction(e -> {
                
            NPC engineer = new NPC(5, 5, "Crew Member", 20, true, Weapon.NONE, null);
            Combat.Attack(player, engineer); //Fight with NPC

            String p2o1 = Files.ReadFile(storyFile, "S3P2-OPTION1", "S3P2-OPTION2");
            Utility.Print(p2o1, Utility.StoryPrintSpeed);

            player.addItem(Consumables.BASIC_MEDKIT); //Add medkits to inventory
            player.addItem(Consumables.BASIC_MEDKIT);

            Utility.centerContainer.getChildren().remove(container);
            Final(player);
        });
        
        actions.getSecondButton().setOnAction(e -> {
                
            String p2o2 = Files.ReadFile(storyFile, "S3P2-OPTION2", "S3P2-OPTION2P1");
            Utility.Print(p2o2, Utility.StoryPrintSpeed);

            player.addItem(Consumables.BASIC_MEDKIT); //Add medkits to inventory
            player.addItem(Consumables.BASIC_MEDKIT);

            EnemyList.add(new NPC(25, 25, "Ra'kra Scout", 35, true, Weapon.PULSE_PISTOL, null));
            EnemyList.add(new NPC(100, 100, "Rak'ra Officer", 75, true, Weapon.PULSE_RIFLE, null));
            //Combat.FightMenu(player, EnemyList); //Fight with NPC
            if (!player.isAlive()) {
                return;
            }

            String p2o3 = Files.ReadFile(storyFile, "S3P2-OPTION2P1", "FINAL");
            Utility.Print(p2o3, Utility.StoryPrintSpeed);

            Utility.centerContainer.getChildren().remove(container);
            Final(player);
        });

    }

    private static void Final(Player player){

        player.setHealth(player.getMaxHealth());
        String finalScene = Files.ReadFile(storyFile, "FINAL", "FINAL-PART2");
        Utility.Print(finalScene, Utility.StoryPrintSpeed);

        EnemyList.add(new NPC(150, 150, "Ka'tar", 200, true, Weapon.PULSE_PISTOL, null));
        NPC Boss = EnemyList.get(0);

        //Combat.FightMenu(player, EnemyList); //Fight the boss
        if (!player.isAlive()) {
            return;
        }

        String finalPart2 = Files.ReadFile(storyFile, "FINAL-PART2", "END");
        Utility.Print(finalPart2, Utility.StoryPrintSpeed);

        String choices = "A) Eliminate Ka'tar B) Leave the Station with Teth";
        VerticalContainer container = new VerticalContainer(10, choices);

        HorizontalPlayerActions actions = new HorizontalPlayerActions(10,"", "Kill Ka'tar", "Leave the Station");
        container.getChildren().addAll(actions);
        Utility.centerContainer.getChildren().addAll(container);

        actions.getFirstButton().setOnAction(e -> {
            
            Utility.Print("You eliminate Ka'tar and grab his weapon.\n", Utility.ActionSpeed);
            player.addItem(Boss.getEquipped()); //Add item that NPC Boss has equipped
            
            Utility.Print("You make your way to the ship and leave the station.\n", Utility.ActionSpeed);
            player.addProgressFlag(new ProgressFlags("Eliminated Ka'tar", true));
            Utility.centerContainer.getChildren().remove(container);
            PrologueEnd(player);

            //Ending prologue logic here
        });

        actions.getSecondButton().setOnAction(e -> {
            
            Utility.Print("You make your way to the ship and leave the station.\n", Utility.ActionSpeed);
            Utility.centerContainer.getChildren().remove(container);
            PrologueEnd(player);
        });

     }

    private static void PrologueEnd(Player player){
        String finalEnd = Files.ReadFile(storyFile, "END", "PROLOGUE-END");
        Utility.Print(finalEnd, Utility.StoryPrintSpeed);
     }
}