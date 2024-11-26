import java.util.ArrayList;

/**@class holds functionality for part of the game "Calyra Cave" */
public class CalyraCave {

    /**@description Arraylist to contain enemy NPC objects */
    private static ArrayList<NPC> enemyList = new ArrayList<>();

    //Files to read
    private static final String storyFile = "ActOne/Cave.txt";
    private static final String datapadFile = "ActOne/Datapad1.txt";

    /**@description reference to container object, used throughout this file */
    private static VerticalContainer playerActionsContainer;
    /**@description reference to player actions object, used throughout this file */
    private static HorizontalPlayerActions playerActions;

    //Used throughout this file, reference for player action choices
    private static String playerChoices;

    //PROGRESS FLAGS
    private static final String CAVE_FOUND_FLAG = "CalyraCaveFound";
    private static final String CAVE_CLEARED_FLAG = "CalyraCaveClear";
    private static final String CAVE_STEALTH_FLAG = "CalyraCaveStealth";

    //References
    private static final String SKILL_STEALTH = "Stealth";
    private static final String LOOT_NPC = "NPC";


    /**
     * @description functionality for Calyra Cave first part
     * @param player player object
     */
    public static void Cave(Player player){
        
        //To check if player has been here before
        boolean firstTime = !player.flagExists(CAVE_FOUND_FLAG);

        //If this is players first time here
        if (firstTime){

            //Read and print story
            Utility.readFileAndPrint(storyFile, "CAVE1", "CAVE2", () -> {

                createCaveNPC(1);

                //Enter combat
                Combat.FightMenu(player, enemyList, () -> {

                    //If player is alive after combat
                    if (player.isAlive()) {
                        
                        //Add progress flag
                        player.addProgressFlag(new ProgressFlags(CAVE_FOUND_FLAG, true));

                        //Read and print story
                        Utility.readFileAndPrint(storyFile, "CAVE2", "CAVE3", () -> {
                            CaveBoss(player); //Move to next part
                        });

                    }
                });
            });
        }    
        else{
            CaveBoss(player); //Move to next part directly
        }
    }

    /**
     * @description Handles Calyra Cave boss instance
     * @param player object player
     */
    private static void CaveBoss(Player player){

        //Set choices and actions for player
        playerActionsContainer = new VerticalContainer(10, "");
        playerChoices = "A) Character B) Attack Alpha C) Try to Sneak past it D) Leave the Cave";
        playerActions = new HorizontalPlayerActions(10, "", "Character", "Attack", "Sneak", "Leave");

        //Update player actions
        Utility.updatePlayerActions(playerActionsContainer, playerActions, playerChoices);

        //OPTION A
        playerActions.getFirstButton().setOnAction(e -> {player.toggleCharacterInfoStatus();});

        //OPTION B
        playerActions.getSecondButton().setOnAction(e -> {

            //Clear player actions before combat
            Utility.clearPlayerActions(playerActionsContainer, playerActions);

            createCaveNPC(2);

            //Enter combat
            Combat.FightMenu(player, enemyList, () -> {

                //if player is alive after combat
                if (player.isAlive()) {
                    
                    //Add progress flag
                    Utility.Print("Jaxon: Alpha down. Guess he wasn't so invincible after all.\n", Utility.ActionSpeed, () -> {});
                    player.addProgressFlag(new ProgressFlags(CAVE_CLEARED_FLAG, true));

                    //If player didnt do stealth option
                    if (!player.flagExists(CAVE_STEALTH_FLAG)){

                        Utility.Print("Jaxon: Now lets see what we find", Utility.ActionSpeed, () -> {

                            Utility.Print("You take a look at the corpse and its surroundings, you find: \n", Utility.ActionSpeed, () -> {
                                    //Read and print data
                                Utility.readFileAndPrint(datapadFile, "", "", () -> {

                                player.actionExperience(Utility.LoreItemEXP); //Gain experience

                                //List for loot
                                ArrayList<Items> lootFound = new ArrayList<>();
                                lootFound.add(Weapon.retrieveTieredWeapon("Medium Tier")); // 1 weapon from medium tier, add to loot

                                //Temporary list to get random lootlist
                                ArrayList<Items> temp = LootLists.retrieveRandomLootList(LOOT_NPC);
                                
                                //Add temp lootlist to found loot
                                lootFound.addAll(temp);

                                //Player loots, adds to inventory
                                player.lootItems(lootFound, "corpse");

                                });
                            });
                        });
                    }

                    Utility.Print("Jaxon: Now lets get out of this smelling cave..\n", Utility.ActionSpeed, () -> {
                        //Clear player actions
                        Utility.clearPlayerActions(playerActionsContainer, playerActions);
                        Utility.centerContainer.getChildren().remove(playerActionsContainer);
                        //Exit cave
                        CalyraPath.Crossroads(player);
                    });
                }
            });
        });

        //OPTION C
        playerActions.getThirdButton().setOnAction(e -> {

            if (player.hasSkill(SKILL_STEALTH) && !player.flagExists(CAVE_STEALTH_FLAG)) {
                
                Utility.Print("Jaxon: Here goes nothing..\nYou reach the body. \n", Utility.ActionSpeed, () -> {

                    Utility.Print("Jaxon: Now lets see what we find", Utility.ActionSpeed, () -> {

                        //Read and print data
                        Utility.readFileAndPrint(datapadFile, "", "", () ->{
                        player.actionExperience(Utility.LoreItemEXP); //Gain experience
    
                        //List for loot
                        ArrayList<Items> lootFound = new ArrayList<>();
                        lootFound.add(Weapon.retrieveTieredWeapon("Medium Tier")); // 1 weapon from medium tier, add to loot
        
                        //Temporary list to get random lootlist
                        ArrayList<Items> temp = LootLists.retrieveRandomLootList(LOOT_NPC);
                          
                        //Add temp lootlist to found loot
                        lootFound.addAll(temp);
        
                        //Player loots, adds to inventory
                        player.lootItems(lootFound, "corpse");
        
                        //Add progress flag
                        player.addProgressFlag(new ProgressFlags(CAVE_STEALTH_FLAG, true));
    
                        });
                    });
                });
            }
            else{
                //if player has flag
                if (player.flagExists(CAVE_STEALTH_FLAG)) {
                    Utility.Print("Jaxon: I have already been at the corpse, only Alpha remaining...\n", Utility.ActionSpeed, () -> {});
                }
                else{
                    Utility.Print("The Alpha stands tall, its piercing gaze sweeping over you, sizing you up. Cant get around.\n", Utility.ActionSpeed, () -> {});
                }
            }
        });

        //OPTION D
        playerActions.getFourthButton().setOnAction(e -> {
            
            //clear player actions
            Utility.clearPlayerActions(playerActionsContainer, playerActions);
            Utility.centerContainer.getChildren().remove(playerActionsContainer);

            Utility.Print("Jaxon: A fight for another day.\n", Utility.ActionSpeed, () -> {
                //Exit cave
                CalyraPath.Crossroads(player);
            });
        });
    }

    /**
     * @description Creates specific NPC for this instance
     * @param phase Wich phase player is in when this function is called
     */
    private static void createCaveNPC(int phase){

        switch (phase) {
            case 1:
                //Create weapon for NPC in this instance
                Weapon Stinger =  new Weapon("Stinger", "Creature Attack", 18, 28, "Venomous stinger", 1);

                //Create NPC
                enemyList.add(new NPC(75, 75, "Fangdweller", 100, "Creature", Stinger, null));
                enemyList.add(new NPC(75, 75, "Fangdweller", 100, "Creature", Stinger, null));
                enemyList.add(new NPC(75, 75, "Fangdweller", 100, "Creature", Stinger, null));
                break;
            case 2:
                //Create weapon for this instance
                Weapon Claws = new Weapon("Claws", "Creature Attack", 20, 28, "Sharp claws", 1);

                //Create NPC 
                enemyList.add(new NPC(300, 300, "Fangdweller Alpha", 250, "Boss", Claws, null));
                break;
        }
    }
    
}
