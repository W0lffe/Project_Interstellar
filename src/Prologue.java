import java.util.ArrayList;

/**@class Prologue
 * @description Class contains functions for Prologue of story
 */
public class Prologue {
    
    //Paths to files

    /**@description file for story to be read in function */
    private static final String storyFile = "Prologue/Prologue.txt";
    /**@description file for datapad to be read in function */
    private static final String datapadFile = "Prologue/Datapad1.txt";
    
    /**@description Arraylist to contain enemy NPC objects */
    private static ArrayList<NPC> EnemyList = new ArrayList<>();
    
    /**@description reference to container object, used throughout Prologue */
    private static VerticalContainer playerActionsContainer;
    /**@description reference to player actions object, used throughout Prologue */
    private static HorizontalPlayerActions playerActions;
   
    //Booleans to check player actions
    private static boolean checkedLocker = false;
    private static boolean readDatapad = false;

    /**@description Reference to string that is set to playerActionsContainer throughout Prologue */
    private static String playerChoices;


    /**
     * @description part one of prologue
     * @param player player object
     */
    public static void PartOne(Player player){

        //Read file to string, starts from word PROLOGUE, ends to word SCENE2
        String partOne = Files.ReadFile(storyFile, "PROLOGUE", "SCENE2"); 
        Utility.Print(partOne, Utility.StoryPrintSpeed); //Print story

        //Possible choices for player
        playerChoices = "A) Character B) Take a look in the locker C) Inspect datapad D) Go outside";
        
        //Create container to hold player actions
        playerActionsContainer =  new VerticalContainer(10, playerChoices);
        playerActions = new HorizontalPlayerActions(10, "", "Character", "Locker", "Datapad", "Go outside");

        //Append player actions to container
        playerActionsContainer.getChildren().add(playerActions);

        //Append to Interface
        Utility.centerContainer.getChildren().add(playerActionsContainer);

        //Toggle character status and info
        playerActions.getFirstButton().setOnAction(e -> { player.toggleCharacterInfoStatus(); });

        //Open Locker
        playerActions.getSecondButton().setOnAction(e -> {
            
            //if player has not checked locker, player is able to do so
            if (!checkedLocker) {
                String itemsFound = "You look in the locker and find: \n" + Weapon.LASER_PISTOL + "\n" + 
                                        Consumables.SPACE_SODA.Found() + "\n" + Consumables.BASIC_MEDKIT.Found();
                Utility.Print(itemsFound, 0);

                //Add items to player inventory, !!!IMPLEMENT: NEW METHOD HERE TO MAKE IT MORE DYNAMIC!!!
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
            
            //If player has not read datapad, player is able to do so
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
            
            //Player cant proceed if has not checked locker
            if(checkedLocker){
                playerActionsContainer.getChildren().remove(playerActions); //remove player actions before going to next part
                playerActionsContainer.setVerticalTitle("");
                PartTwo(player); //Exit PartOne
            }
            else{
                Utility.Print("Jaxon: Maybe I should take a look in my locker first.\n", Utility.ActionSpeed);
            }
        });

    }

    /**
     * @description Part two of Prologue
     * @param player player object
     */
    private static void PartTwo(Player player){
        
        //Read story to string
        String partTwo = Files.ReadFile(storyFile, "SCENE2", "S2-OPTION1"); 
        Utility.Print(partTwo, Utility.StoryPrintSpeed);  //print story

        //Set new player choices
        playerChoices = "A) Go with Teth and Jaxer B) Not now";
        playerActionsContainer.setVerticalTitle(playerChoices);

        //Set new player actions
        playerActions = new HorizontalPlayerActions(10,"", "Go with the guys", "Take a moment for yourself");
        playerActionsContainer.getChildren().add(playerActions);

        playerActions.getFirstButton().setOnAction(e -> {

            //remove player actions before going to combat
            playerActionsContainer.getChildren().remove(playerActions); 
            playerActionsContainer.setVerticalTitle("");
            
            String option1 = Files.ReadFile(storyFile, "S2-OPTION1", "S2-OPTION1,2");
            Utility.Print(option1, Utility.StoryPrintSpeed);  

            //Create object NPC, add to list
            EnemyList.add(new NPC(5, 5, "Target", 20, true, Weapon.NONE, null));

            //Enter combat, sending player object and list containing enemy objects
            Combat.FightMenu(player, EnemyList, () -> {

                //After combat run this: if player is alive --> read story and move to next part
                if (player.isAlive()) {
                    String option1o1 = Files.ReadFile(storyFile, "S2-OPTION1,2", "S2-OPTION2");
                    Utility.Print(option1o1, Utility.StoryPrintSpeed);
                    
                    PartThree(player); //exit PartTwo
                }
            });
            
        });

        playerActions.getSecondButton().setOnAction(e -> {

            //remove player actions before going to next part
            playerActionsContainer.getChildren().remove(playerActions);
            playerActionsContainer.setVerticalTitle("");

            String option2 = Files.ReadFile(storyFile, "S2-OPTION2", "SCENE3");
            Utility.Print(option2, Utility.StoryPrintSpeed);   

            PartThree(player); //exit PartTwo
        });

    }

    /**
     * @description part three of prologue
     * @param player player object
     */
    private static void PartThree(Player player){


        //Create NPC objects and add to List
        EnemyList.add(new NPC(50, 50, "Rak'ra Rookie", 50, true, Weapon.PULSE_PISTOL, null));
        EnemyList.add(new NPC(60, 60, "Rak'ra Brute", 75, true, Weapon.PULSE_RIFLE, null));

        //reference to NPC object in list
        NPC brute = EnemyList.get(1);

        //Set new player choices
        playerChoices = "A) Character B) Fight the Rak'ra C) Try to run over to Teth D) Try to run away";
        playerActionsContainer.setVerticalTitle(playerChoices);

        //Set new player actions
        playerActions = new HorizontalPlayerActions(10,"","Show Character", "Fight!", "Help your friends", "Retreat!");
        playerActionsContainer.getChildren().add(playerActions);

        //Toggle player info and status
        playerActions.getFirstButton().setOnAction(e -> { player.toggleCharacterInfoStatus(); });

        playerActions.getSecondButton().setOnAction(e -> {

            //remove player actions before going to combat
            playerActionsContainer.getChildren().remove(playerActions);
            playerActionsContainer.setVerticalTitle("");

            Utility.Print("You will fight the Rak'ra\n", Utility.ActionSpeed);

            //Enter combat, sending player object and list containing enemy objects
            Combat.FightMenu(player, EnemyList, () -> {
                
                //After combat run this: if player is alive --> read story and move to next part
                if (player.isAlive()) {
                    PartFour(player); //exit PartThree
                }
                //Else game over !!!IMPLEMENT: NEW METHOD THAT HANDLES PLAYER DEATH!!!
                else{
                    Main.primaryStage.setScene(Main.mainMenuScene);
                }
            }); 
        });

        playerActions.getThirdButton().setOnAction(e -> {
           
            String takeDamage = "You try to get over to Teth. But " + brute.getName() + " spots you and shoots at you, dealing " + 
            brute.getEquipped().getMinDamage() + " damage!\n";
            Utility.Print(takeDamage, Utility.ActionSpeed);

            player.takeDamage(brute.getEquipped().getMinDamage());
            Utility.Print("Forcing you back to cover\n", Utility.ActionSpeed);
        });

        playerActions.getFourthButton().setOnAction(e -> {
            Utility.Print("Jaxon: I need to help Teth and Jaxer!\n", Utility.ActionSpeed);
        });
    }

    /**
     * @description part four of prologue
     * @param player player object
     */
    private static void PartFour(Player player){
        
        //Read story to string and print
        String scene3 = Files.ReadFile(storyFile, "SCENE3", "S3-OPTION1");
        Utility.Print(scene3, Utility.StoryPrintSpeed);

        //Set new player choices
        playerChoices = "A) Go to Jaxer and see if you can help him recover B) Help Teth";
        playerActionsContainer.setVerticalTitle(playerChoices);

        //Set new player actions
        playerActions = new HorizontalPlayerActions(10,"", "Try to help Jaxer", "Help Teth");
        playerActionsContainer.getChildren().addAll(playerActions);
        
        playerActions.getFirstButton().setOnAction(e -> {
            
            //if player has object in inventory
            if(player.getPlayerInventory().contains(Consumables.BASIC_MEDKIT)){

                //remove player actions before going to next part
                playerActionsContainer.getChildren().remove(playerActions);
                playerActionsContainer.setVerticalTitle("");

                Utility.Print("You go to Jaxer\n", Utility.ActionSpeed);
                player.addProgressFlag(new ProgressFlags("Saved Jaxer", true));

                String s3o1 = Files.ReadFile(storyFile, "S3-OPTION1", "S3-OPTION2");
                Utility.Print(s3o1, Utility.StoryPrintSpeed);

                PartFive(player); //Exit PartFour
            }
            else{
                Utility.Print("Jaxon: I need a medkit to help Jaxer...\n", Utility.ActionSpeed);
            }
        });

        playerActions.getSecondButton().setOnAction(e -> {
            
            //remove player actions before going to next part
            playerActionsContainer.getChildren().remove(playerActions);
            playerActionsContainer.setVerticalTitle("");

            String s3o2 = Files.ReadFile(storyFile, "S3-OPTION2", "S3-PART2");
            Utility.Print(s3o2, Utility.StoryPrintSpeed);

            PartFive(player); //exit PartFour
        });

    }

    /**
     * @description part five of prologue
     * @param player player object
     */
    private static void PartFive(Player player){

        //Read story to String and print
        String s3part2 = Files.ReadFile(storyFile, "S3-PART2", "S3P2-OPTION1");
        Utility.Print(s3part2, Utility.StoryPrintSpeed);

        //Set new player choices
        playerChoices = "A) Kill the engineer B) Keep going";
        playerActionsContainer.setVerticalTitle(playerChoices);

        //Set new player actions
        playerActions = new HorizontalPlayerActions(10,"", "Kill the Engineer", "Keep running");
        playerActionsContainer.getChildren().add(playerActions);


        playerActions.getFirstButton().setOnAction(e -> {
            
            //remove player actions before going to combat
            playerActionsContainer.getChildren().remove(playerActions);
            playerActionsContainer.setVerticalTitle("");

            //Create NPC object and add to enemy list
            EnemyList.add(new NPC(5, 5, "Crew Member", 20, true, Weapon.NONE, null));

            //Enter combat with player object and list of enemies
            Combat.FightMenu(player, EnemyList, () -> {

                String p2o1 = Files.ReadFile(storyFile, "S3P2-OPTION1", "S3P2-OPTION2");
                Utility.Print(p2o1, Utility.StoryPrintSpeed);
                
                //Add consumable objects to player inventory
                player.addItemToInventory(Consumables.BASIC_MEDKIT); 
                player.addItemToInventory(Consumables.BASIC_MEDKIT);
    
                Final(player); //exit PartFive
            }); 
        });

        playerActions.getSecondButton().setOnAction(e -> {
             
            //remove player actions before going to combat
            playerActionsContainer.getChildren().remove(playerActions);
            playerActionsContainer.setVerticalTitle("");

            String p2o2 = Files.ReadFile(storyFile, "S3P2-OPTION2", "S3P2-OPTION2P1");
            Utility.Print(p2o2, Utility.StoryPrintSpeed);

            //Add consumable objects to player inventory
            player.addItemToInventory(Consumables.BASIC_MEDKIT); //Add medkits to inventory
            player.addItemToInventory(Consumables.BASIC_MEDKIT);

            //Create NPC objects and add to enemy list
            EnemyList.add(new NPC(25, 25, "Ra'kra Scout", 35, true, Weapon.PULSE_PISTOL, null));
            EnemyList.add(new NPC(100, 100, "Rak'ra Officer", 75, true, Weapon.PULSE_RIFLE, null));

            //Enter combat with player object and enemylist
            Combat.FightMenu(player, EnemyList, () -> {

                //after combat, if player is alive: read story and print, exit PartFive
                if (player.isAlive()) {
                    String p2o3 = Files.ReadFile(storyFile, "S3P2-OPTION2P1", "FINAL");
                    Utility.Print(p2o3, Utility.StoryPrintSpeed);

                    Final(player); //Exit PartFive
                }
                //else game over
                else{
                    Main.primaryStage.setScene(Main.mainMenuScene);
                }
            }); 
        });

    }
    /**
     * @description Final of Prologue
     * @param player player object
     */
    private static void Final(Player player){

        //Set player health to maximum health
        player.setHealth(player.getMaxHealth());

        //Read story to string and print
        String finalScene = Files.ReadFile(storyFile, "FINAL", "FINAL-PART2");
        Utility.Print(finalScene, Utility.StoryPrintSpeed);

        //Create NPC object and add to list
        EnemyList.add(new NPC(150, 150, "Ka'tar", 200, true, Weapon.PULSE_PISTOL, null));

        //Reference to created npc
        NPC Boss = EnemyList.get(0);

        //Enter combat with player and enemylist
        Combat.FightMenu(player, EnemyList, () -> {
            
            //if player is alive after combat
            if (player.isAlive()) {

                //Read story to string and print
                String finalPart2 = Files.ReadFile(storyFile, "FINAL-PART2", "END");
                Utility.Print(finalPart2, Utility.StoryPrintSpeed);

                //Set new player chocies
                playerChoices = "A) Eliminate Ka'tar B) Leave the Station with Teth";
                playerActionsContainer.setVerticalTitle(playerChoices);

                //Set new player actions
                playerActions = new HorizontalPlayerActions(10,"", "Kill Ka'tar", "Leave the Station");
                playerActionsContainer.getChildren().add(playerActions);

                playerActions.getFirstButton().setOnAction(e -> {
            
                    Utility.Print("You eliminate Ka'tar and grab his weapon.\n", Utility.ActionSpeed);
                    player.addItemToInventory(Boss.getEquipped()); //Add item that NPC Boss has equipped
                    
                    Utility.Print("You make your way to the ship and leave the station.\n", Utility.ActionSpeed);
                    player.addProgressFlag(new ProgressFlags("Eliminated Ka'tar", true));

                    //Remove prologue player actions container from Interface
                    Utility.centerContainer.getChildren().remove(playerActionsContainer);
                    PrologueEnd(player); //exit prologue final
        
                });

                playerActions.getSecondButton().setOnAction(e -> {
            
                    Utility.Print("You make your way to the ship and leave the station.\n", Utility.ActionSpeed);

                    //Remove prologue player actions container from Interface
                    Utility.centerContainer.getChildren().remove(playerActionsContainer);

                    PrologueEnd(player); //exit prologue final
                });
            }
        });
    } 

    /**
     * @description finish prologue
     * @param player player object
     */
    private static void PrologueEnd(Player player){

        //Read prologue ending story to string and Print
        String finalEnd = Files.ReadFile(storyFile, "END", "PROLOGUE-END");
        Utility.Print(finalEnd, Utility.StoryPrintSpeed);

        //TO NEXT ACT
     }
}
