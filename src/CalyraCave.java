import java.util.ArrayList;

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

    private static String playerChoices;

    public static void Cave(Player player){
        
        //Progress flag
        String caveFound = "CalyraCaveFound";

        //To check if player has been here before
        boolean firstTime = !player.flagExists(caveFound);

        //If this is players first time here
        if (firstTime){

            //Read and print story
            Utility.readFileAndPrint(storyFile, "CAVE1", "CAVE2");

            //Create weapon for NPC in this instance
            Weapon Stinger =  new Weapon("Stinger", "Creature Attack", 18, 28, "Venomous stinger", 1);

            //Create NPC
            enemyList.add(new NPC(75, 75, "Fangdweller", 100, "Creature", Stinger, null));
            enemyList.add(new NPC(75, 75, "Fangdweller", 100, "Creature", Stinger, null));
            enemyList.add(new NPC(75, 75, "Fangdweller", 100, "Creature", Stinger, null));

            //Enter combat
            Combat.FightMenu(player, enemyList, () -> {

                //If player is alive after combat
                if (player.isAlive()) {
                    
                    //Add progress flag
                    player.addProgressFlag(new ProgressFlags(caveFound, true));

                    //Read and print story
                    Utility.readFileAndPrint(storyFile, "CAVE2", "CAVE3");

                    CaveBoss(player); //Move to next part
                }
            });
        }
        else{
            CaveBoss(player); //Move to next part directly
        }
    }

    private static void CaveBoss(Player player){

        //Progress flags
        String caveCleared = "CalyraCaveClear";
        String caveStealth = "CalyraCaveStealth";

        //Set choices and actions for player
        playerActionsContainer = new VerticalContainer(10, "");
        playerChoices = "A) Character B) Attack Alpha C) Try to Sneak past it D) Leave the Cave";
        playerActions = new HorizontalPlayerActions(10, "", "Character", "Attack", "Sneak", "Leave");

        //Update player actions
        Utility.updatePlayerActions(playerActionsContainer, playerActions, playerChoices);

        playerActions.getFirstButton().setOnAction(e -> {player.toggleCharacterInfoStatus();});

        playerActions.getSecondButton().setOnAction(e -> {

            //Clear player actions before combat
            Utility.clearPlayerActions(playerActionsContainer, playerActions);

            //Create weapon for this instance
            Weapon Claws = new Weapon("Claws", "Creature Attack", 20, 28, "Sharp claws", 1);

            //Create NPC 
            enemyList.add(new NPC(300, 300, "Fangdweller Alpha", 250, "Boss", Claws, null));

            //Enter combat
            Combat.FightMenu(player, enemyList, () -> {

                //if player is alive after combat
                if (player.isAlive()) {
                    
                    //Add progress flag
                    Utility.Print("Jaxon: Alpha down. Guess he wasn't so invincible after all.\n", Utility.ActionSpeed);
                    player.addProgressFlag(new ProgressFlags(caveCleared, true));

                    //If player didnt do stealth option
                    if (!player.flagExists("CalyraCaveStealth")){

                        Utility.Print("Jaxon: Now lets see what we find", Utility.ActionSpeed);
                        Utility.Print("You take a look at the corpse and its surroundings, you find: \n", Utility.ActionSpeed);

                        //Read and print data
                        Utility.readFileAndPrint(datapadFile, "", "");
                        player.actionExperience(Utility.LoreItemEXP); //Gain experience

                        //List for loot
                        ArrayList<Items> lootFound = new ArrayList<>();
                        lootFound.add(Weapon.retrieveTieredWeapon("Medium Tier")); // 1 weapon from medium tier, add to loot

                        //Temporary list to get random lootlist
                        ArrayList<Items> temp = LootLists.retrieveRandomLootList("NPC");
                        
                        //Add temp lootlist to found loot
                        lootFound.addAll(temp);

                        //Player loots, adds to inventory
                        player.lootItems(lootFound, "corpse");

                    }

                    Utility.Print("Jaxon: Now lets get out of this smelling cave..\n", Utility.ActionSpeed);

                    //Clear player actions
                    Utility.clearPlayerActions(playerActionsContainer, playerActions);
                    Utility.centerContainer.getChildren().remove(playerActionsContainer);
                    //Exit cave
                    CalyraBunker.PathToColony(player);
                }
            });
        });

        playerActions.getThirdButton().setOnAction(e -> {

            if (player.hasSkill("Stealth") && !player.flagExists(caveStealth)) {
                
                Utility.Print("Jaxon: Here goes nothing..\nYou reach the body. \n", Utility.ActionSpeed);
                Utility.Print("Jaxon: Now lets see what we find", Utility.ActionSpeed);

                //Read and print data
                Utility.readFileAndPrint(datapadFile, "", "");
                player.actionExperience(Utility.LoreItemEXP); //Gain experience

                //List for loot
                ArrayList<Items> lootFound = new ArrayList<>();
                lootFound.add(Weapon.retrieveTieredWeapon("Medium Tier")); // 1 weapon from medium tier, add to loot

                //Temporary list to get random lootlist
                ArrayList<Items> temp = LootLists.retrieveRandomLootList("NPC");
                  
                //Add temp lootlist to found loot
                lootFound.addAll(temp);

                //Player loots, adds to inventory
                player.lootItems(lootFound, "corpse");

                //Add progress flag
                player.addProgressFlag(new ProgressFlags(caveStealth, true));
            }
            else{
                //if player has flag
                if (player.flagExists(caveStealth)) {
                    Utility.Print("Jaxon: I have already been at the corpse, only Alpha remaining...\n", Utility.ActionSpeed);
                }
                else{
                    Utility.Print("The Alpha stands tall, its piercing gaze sweeping over you, sizing you up. Cant get around.\n", Utility.ActionSpeed);
                }
            }
        });

        playerActions.getFourthButton().setOnAction(e -> {
            
            //clear player actions
            Utility.clearPlayerActions(playerActionsContainer, playerActions);
            Utility.centerContainer.getChildren().remove(playerActionsContainer);

            Utility.Print("Jaxon: A fight for another day.\n", Utility.ActionSpeed);
            //Exit cave
            CalyraBunker.PathToColony(player);
        });

    }
    
}
