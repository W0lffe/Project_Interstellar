import java.util.ArrayList;

public class Prologue {
    
    private static final String storyFile = "Prologue/Prologue.txt"; //File wich from program reads story
    private static final String datapadFile = "Prologue/Datapad1.txt";
    private static ArrayList<NPC> EnemyList = new ArrayList<>();
    
    private static VerticalContainer character;
    private static VerticalStatus playerStatus;
    private static VerticalContainer playerActionsContainer;
    private static HorizontalPlayerActions playerActions;
   
    private static boolean checkedLocker = false;
    private static boolean readDatapad = false;
    private static String playerChoices;

    public static void PartOne(Player player){
        //Init container for player information
        character = player.getCharacterInfo();
        playerStatus = player.getStatusContainer();

        //Read file to string, starts from word PROLOGUE, ends to word SCENE2
        String partOne = Files.ReadFile(storyFile, "PROLOGUE", "SCENE2"); 
        Utility.Print(partOne, Utility.StoryPrintSpeed); //Print story

        //Possible choices for player
        playerChoices = "A) Character B) Take a look in the locker C) Inspect datapad D) Go outside";
        
        //Create container to hold player actions container
        playerActionsContainer =  new VerticalContainer(10, playerChoices);
        playerActions = new HorizontalPlayerActions(10, "", "Character", "Locker", "Datapad", "Go outside");
        Utility.centerContainer.setPlayerActions(playerActions);
        playerActionsContainer.getChildren().add(playerActions);

        //Append to Interface
        Utility.centerContainer.getChildren().add(playerActionsContainer);

        //Show character
        playerActions.getFirstButton().setOnAction(e -> {
            
            if (!Utility.leftContainer.getChildren().contains(character) && !Utility.rightContainer.getChildren().contains(playerStatus)) {
                Utility.leftContainer.getChildren().add(character);
                Utility.rightContainer.getChildren().add(playerStatus);
            }
            else{
                Utility.leftContainer.getChildren().remove(character);
                Utility.rightContainer.getChildren().remove(playerStatus);
            }
        });

        //Open Locker
        playerActions.getSecondButton().setOnAction(e -> {
            
            if (!checkedLocker) {
                String itemsFound = "You look in the locker and find: \n" + Weapon.LASER_PISTOL + "\n" + 
                                        Consumables.SPACE_SODA.Found() + "\n" + Consumables.BASIC_MEDKIT.Found();
                Utility.Print(itemsFound, 0);

                player.addItemToInventory(Weapon.LASER_PISTOL);
                player.addItemToInventory(Consumables.SPACE_SODA);
                player.addItemToInventory(Consumables.BASIC_MEDKIT);    

                checkedLocker = true;
            }
            else{
                Utility.Print(Utility.lockerIsEmpty, Utility.ActionSpeed);
            }
        });

        //Read Datapad
        playerActions.getThirdButton().setOnAction(e -> {
           
            if(!readDatapad){
                Utility.Print(Utility.activateDatapad, Utility.ActionSpeed);
                
                String datapad = Files.ReadFile(datapadFile,null,null);
                Utility.Print(datapad, Utility.DatapadPrintSpeed);
                
                player.actionExperience(Utility.LoreItemEXP);
                readDatapad = true;
            }
            else{
                Utility.Print(Utility.isRead, Utility.ActionSpeed);
            }
        });

        //Continue gameflow
        playerActions.getFourthButton().setOnAction(e -> {
            
            if(checkedLocker){
                playerActionsContainer.getChildren().remove(playerActions);
                PartTwo(player); //exit part one
            }
            else{
                Utility.Print("Jaxon: Maybe I should take a look in my locker first.\n", Utility.ActionSpeed);
            }
        });

    }

    private static void PartTwo(Player player){
        
        //Read story to string
        String partTwo = Files.ReadFile(storyFile, "SCENE2", "S2-OPTION1"); 
        Utility.Print(partTwo, Utility.StoryPrintSpeed);  //print story

        playerChoices = "A) Go with Teth and Jaxer B) Not now";
        playerActionsContainer.setVerticalTitle(playerChoices);

        playerActions = new HorizontalPlayerActions(10,"", "Go with the guys", "Take a moment for yourself");
        Utility.centerContainer.setPlayerActions(playerActions);
        playerActionsContainer.getChildren().add(playerActions);

        playerActions.getFirstButton().setOnAction(e -> {
            playerActionsContainer.getChildren().remove(playerActions);

            String option1 = Files.ReadFile(storyFile, "S2-OPTION1", "S2-OPTION1,2");
            Utility.Print(option1, Utility.StoryPrintSpeed);  

            EnemyList.add(new NPC(5, 5, "Target", 20, true, Weapon.NONE, null));

            Combat.FightMenu(player, EnemyList, () -> {
                if (player.isAlive()) {
                    String option1o1 = Files.ReadFile(storyFile, "S2-OPTION1,2", "S2-OPTION2");
                    Utility.Print(option1o1, Utility.StoryPrintSpeed);
                    
                    PartThree(player);
                }
            });
            
        });

        playerActions.getSecondButton().setOnAction(e -> {
            playerActionsContainer.getChildren().remove(playerActions);

            String option2 = Files.ReadFile(storyFile, "S2-OPTION2", "SCENE3");
            Utility.Print(option2, Utility.StoryPrintSpeed);   

            PartThree(player);
        });

    }

    private static void PartThree(Player player){

        EnemyList.add(new NPC(50, 50, "Rak'ra Rookie", 50, true, Weapon.PULSE_PISTOL, null));
        EnemyList.add(new NPC(60, 60, "Rak'ra Brute", 75, true, Weapon.PULSE_RIFLE, null));
        NPC brute = EnemyList.get(1);

        playerChoices = "A) Character B) Fight the Rak'ra C) Try to run over to Teth D) Try to run away";
        playerActionsContainer.setVerticalTitle(playerChoices);

        playerActions = new HorizontalPlayerActions(10,"","Show Character", "Fight!", "Help your friends", "Retreat!");
        Utility.centerContainer.setPlayerActions(playerActions);
        playerActionsContainer.getChildren().add(playerActions);

        playerActions.getFirstButton().setOnAction(e ->{
            
            if (!Utility.leftContainer.getChildren().contains(character) && !Utility.rightContainer.getChildren().contains(playerStatus)) {
                Utility.leftContainer.getChildren().add(character);
                Utility.rightContainer.getChildren().add(playerStatus);
            }
            else{
                Utility.leftContainer.getChildren().remove(character);
                Utility.rightContainer.getChildren().remove(playerStatus);
            }
        });

        playerActions.getSecondButton().setOnAction(e -> {
            playerActionsContainer.getChildren().remove(playerActions);

            Utility.Print("You will fight the Rak'ra\n", Utility.ActionSpeed);
            Combat.FightMenu(player, EnemyList, () -> {
                
                if (player.isAlive()) {
                    PartFour(player);
                }
                else{
                    Main.primaryStage.setScene(Main.mainMenuScene);
                }
            }); 
        });

        playerActions.getThirdButton().setOnAction(e -> {
           
            String caseC = "You try to get over to Teth. But " + brute.getName() + " spots you and shoots at you, dealing " + 
            brute.getEquipped().getMinDamage() + " damage!\n";
            Utility.Print(caseC, Utility.ActionSpeed);

            player.takeDamage(brute.getEquipped().getMinDamage());
            Utility.Print("Forcing you back to cover\n", Utility.ActionSpeed);
        });

        playerActions.getFourthButton().setOnAction(e -> {
            Utility.Print("Jaxon: I need to help Teth and Jaxer!\n", Utility.ActionSpeed);
        });
    }

    private static void PartFour(Player player){

        String scene3 = Files.ReadFile(storyFile, "SCENE3", "S3-OPTION1");
        Utility.Print(scene3, Utility.StoryPrintSpeed);

        playerChoices = "A) Go to Jaxer and see if you can help him recover B) Help Teth";
        playerActionsContainer.setVerticalTitle(playerChoices);

        playerActions = new HorizontalPlayerActions(10,"", "Try to help Jaxer", "Help Teth");
        playerActionsContainer.getChildren().addAll(playerActions);
        Utility.centerContainer.setPlayerActions(playerActions);

        
        playerActions.getFirstButton().setOnAction(e -> {
            
            if(player.getInventory().contains(Consumables.BASIC_MEDKIT)){

                playerActionsContainer.getChildren().remove(playerActions);

                Utility.Print("You go to Jaxer\n", Utility.ActionSpeed);
                player.addProgressFlag(new ProgressFlags("Saved Jaxer", true));

                String s3o1 = Files.ReadFile(storyFile, "S3-OPTION1", "S3-OPTION2");
                Utility.Print(s3o1, Utility.StoryPrintSpeed);

                PartFive(player);
            }
            else{
                Utility.Print("Jaxon: I need a medkit to help Jaxer...\n", Utility.ActionSpeed);
            }
        });

        playerActions.getSecondButton().setOnAction(e -> {
            
            playerActionsContainer.getChildren().remove(playerActions);

            String s3o2 = Files.ReadFile(storyFile, "S3-OPTION2", "S3-PART2");
            Utility.Print(s3o2, Utility.StoryPrintSpeed);

            PartFive(player);
        });

    }

    private static void PartFive(Player player){

        String s3part2 = Files.ReadFile(storyFile, "S3-PART2", "S3P2-OPTION1");
        Utility.Print(s3part2, Utility.StoryPrintSpeed);

        playerChoices = "A) Kill the engineer B) Keep going";
        playerActionsContainer.setVerticalTitle(playerChoices);

        playerActions = new HorizontalPlayerActions(10,"", "Kill the Engineer", "Keep running");
        playerActionsContainer.getChildren().add(playerActions);
        Utility.centerContainer.setPlayerActions(playerActions);

        playerActions.getFirstButton().setOnAction(e -> {
            playerActionsContainer.getChildren().remove(playerActions);

            EnemyList.add(new NPC(5, 5, "Crew Member", 20, true, Weapon.NONE, null));
            Combat.FightMenu(player, EnemyList, () -> {

                String p2o1 = Files.ReadFile(storyFile, "S3P2-OPTION1", "S3P2-OPTION2");
                Utility.Print(p2o1, Utility.StoryPrintSpeed);
    
                player.addItemToInventory(Consumables.BASIC_MEDKIT); //Add medkits to inventory
                player.addItemToInventory(Consumables.BASIC_MEDKIT);
    
                Final(player);
            }); 
        });

        playerActions.getSecondButton().setOnAction(e -> {
            playerActionsContainer.getChildren().remove(playerActions);

            String p2o2 = Files.ReadFile(storyFile, "S3P2-OPTION2", "S3P2-OPTION2P1");
            Utility.Print(p2o2, Utility.StoryPrintSpeed);

            player.addItemToInventory(Consumables.BASIC_MEDKIT); //Add medkits to inventory
            player.addItemToInventory(Consumables.BASIC_MEDKIT);

            EnemyList.add(new NPC(25, 25, "Ra'kra Scout", 35, true, Weapon.PULSE_PISTOL, null));
            EnemyList.add(new NPC(100, 100, "Rak'ra Officer", 75, true, Weapon.PULSE_RIFLE, null));
            Combat.FightMenu(player, EnemyList, () -> {
                if (player.isAlive()) {
                    String p2o3 = Files.ReadFile(storyFile, "S3P2-OPTION2P1", "FINAL");
                    Utility.Print(p2o3, Utility.StoryPrintSpeed);

                    Final(player);
                }
                else{
                    Main.primaryStage.setScene(Main.mainMenuScene);
                }
            }); 
        });

    }

    private static void Final(Player player){

        player.setHealth(player.getMaxHealth());
        String finalScene = Files.ReadFile(storyFile, "FINAL", "FINAL-PART2");
        Utility.Print(finalScene, Utility.StoryPrintSpeed);

        EnemyList.add(new NPC(150, 150, "Ka'tar", 200, true, Weapon.PULSE_PISTOL, null));
        NPC Boss = EnemyList.get(0);

        Combat.FightMenu(player, EnemyList, () -> {
            
            if (player.isAlive()) {

                String finalPart2 = Files.ReadFile(storyFile, "FINAL-PART2", "END");
                Utility.Print(finalPart2, Utility.StoryPrintSpeed);

                playerChoices = "A) Eliminate Ka'tar B) Leave the Station with Teth";
                playerActionsContainer.setVerticalTitle(playerChoices);

                playerActions = new HorizontalPlayerActions(10,"", "Kill Ka'tar", "Leave the Station");
                playerActionsContainer.getChildren().add(playerActions);
                Utility.centerContainer.setPlayerActions(playerActions);

                playerActions.getFirstButton().setOnAction(e -> {
            
                    Utility.Print("You eliminate Ka'tar and grab his weapon.\n", Utility.ActionSpeed);
                    player.addItemToInventory(Boss.getEquipped()); //Add item that NPC Boss has equipped
                    
                    Utility.Print("You make your way to the ship and leave the station.\n", Utility.ActionSpeed);
                    player.addProgressFlag(new ProgressFlags("Eliminated Ka'tar", true));

                    playerActionsContainer.getChildren().remove(playerActions);
                    PrologueEnd(player);
        
                });

                playerActions.getSecondButton().setOnAction(e -> {
            
                    Utility.Print("You make your way to the ship and leave the station.\n", Utility.ActionSpeed);
                    playerActionsContainer.getChildren().remove(playerActions);
                    PrologueEnd(player);
                });
            }
        });
    } 

    private static void PrologueEnd(Player player){
        String finalEnd = Files.ReadFile(storyFile, "END", "PROLOGUE-END");
        Utility.Print(finalEnd, Utility.StoryPrintSpeed);

        //TO NEXT ACT
     }
}
