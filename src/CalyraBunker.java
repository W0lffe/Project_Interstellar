import java.util.ArrayList;

public class CalyraBunker {
    
    //Files to read
    private static final String storyFile = "ActOne/ActOne.txt";
    private static final String armoryTerminal = "ActOne/Console2.txt";

    /**@description reference to container object, used throughout this file */
    private static VerticalContainer playerActionsContainer;
    /**@description reference to player actions object, used throughout this file */
    private static HorizontalPlayerActions playerActions;

    /**@description Reference to string that is set to playerActionsContainer throughout this file*/
    private static String playerChoices;

    //PROGRESS FLAGS
    private static final String ARMORY_FIRST_TIME = "BunkerArmory";
    private static final String BUNKER_RACK_LOOTED = "ArmoryRackLooted";
    private static final String BUNKER_LOCKER_LOOTED = "ArmoryCabinetUnlocked";
    private static final String BUNKER_TERMINAL_READ = "ArmoryTerminalRead";
    private static final String BUNKER_ARMORY_CLEARED = "BunkerArmoryClear";

    private static final String BUNKER_FIRST_TIME = "BunkerMain";
    private static final String PATH_DISCOVERED = "PathToColony";


    public static void BunkerArmory(Player player){

        //Set new player choices and actions
        playerChoices = "A) Character B) Look at the locker C) Inspect terminal D) Inspect the weapon rack E) Go to Main Room";
        playerActions = new HorizontalPlayerActions(10,"", "Character", "Loot Locker", "Activate terminal", "Loot Rack", "Exit Room");
        playerActionsContainer = new VerticalContainer(10, playerChoices);
             
        //See if player is here for first time
        boolean firstTime = !player.flagExists(ARMORY_FIRST_TIME);

        //If player is here for first time, this will be executed
        if (firstTime) {
            Utility.readFileAndPrint(storyFile, "ACT1-SCENE4", "ACT1-SCENE5", () -> {
                //Add progress flag
                player.addProgressFlag(new ProgressFlags(ARMORY_FIRST_TIME, true));

                //Update player actions
                Utility.updatePlayerActions(playerActionsContainer, playerActions, playerChoices);
            });
        }
        else{
            //Update player actions
            Utility.updatePlayerActions(playerActionsContainer, playerActions, playerChoices);
        }

        //OPTION A
        playerActions.getFirstButton().setOnAction(e -> {player.toggleCharacterInfoStatus();});

        //OPTION B
        playerActions.getSecondButton().setOnAction(e -> {
            
            //String for status of cabinet
            String cabinetStatus = "Jaxon: There is no way I can open this.\n";

            //If player has skill and player does not have progress flag yet
            if (player.hasSkill("Lockpicking") && !player.flagExists(BUNKER_LOCKER_LOOTED)){
                
                //Loot items
                player.lootItems(LootLists.retrieveRandomLootList("Meds"), "locker");
                player.addProgressFlag(new ProgressFlags(BUNKER_LOCKER_LOOTED, true)); //action done
            }
            else{
                //If player has opened cabinet
                if (player.flagExists(BUNKER_LOCKER_LOOTED)) {
                    Utility.Print(Utility.isLooted, Utility.ActionSpeed, () -> {}); //is looted
                }
                else{
                    Utility.Print(cabinetStatus, Utility.ActionSpeed, () -> {}); //cant do this action yet
                }
            }
        });

        //OPTION C
        playerActions.getThirdButton().setOnAction(e -> {

            //If player does not have said progress flag
            if (!player.flagExists(BUNKER_TERMINAL_READ)){

                Utility.Print(Utility.activateConsole, Utility.ActionSpeed, () -> {

                    //Read and print terminal data
                    Utility.readFileAndPrint(armoryTerminal, null, null, () -> {

                        //Gain experience and set action done
                        player.actionExperience(Utility.LoreItemEXP);
                        player.addProgressFlag(new ProgressFlags(BUNKER_TERMINAL_READ, true));
                    });
                });
            }
            else{
                Utility.Print(Utility.isRead, Utility.ActionSpeed, () -> {});
            }
        });;

        //OPTION D
        playerActions.getFourthButton().setOnAction(e -> {

             //If player does not have said progress flag
            if (!player.flagExists(BUNKER_RACK_LOOTED)) {

                ArrayList<Items> temporaryList = new ArrayList<>();
                temporaryList.add(Weapon.retrieveTieredWeapon("Medium Tier"));
                //Loot rack -> Get random weapon from Medium Tier, set action done
                player.lootItems(temporaryList, "weapon rack");;
                player.addProgressFlag(new ProgressFlags(BUNKER_RACK_LOOTED, true));
            }
            else{
                Utility.Print(Utility.isLooted, Utility.ActionSpeed, () -> {});
            }
        });

        //OPTION E
        playerActions.getFifthButton().setOnAction(e -> {

            //clear player actions before going to next part
            Utility.clearPlayerActions(playerActionsContainer, playerActions);

            //If player has flags, get new flag "area clear"
            if (player.flagExists(ARMORY_FIRST_TIME) &&
            player.flagExists(BUNKER_LOCKER_LOOTED) &&
            player.flagExists(BUNKER_RACK_LOOTED) &&
            player.flagExists(BUNKER_TERMINAL_READ) ){
            player.addProgressFlag(new ProgressFlags(BUNKER_ARMORY_CLEARED, true));
            Utility.Print("Jaxon: I think I've found everything useful here.\n", Utility.ActionSpeed, () -> {});
            }

            Utility.Print("You go back to Main Room\n", Utility.ActionSpeed, () -> {

                //Exit function/area
                BunkerMainRoom(player);
            });
        });


    }   

    public static void BunkerMainRoom(Player player){

        //Set new player choices and actions
        playerChoices = "A) Show Character B) Rest in the cozy bunkbed C) Go to Armory D) Head out";
        playerActions = new HorizontalPlayerActions(10,"", "Character", "Rest", "Go To Armory", "Exit Bunker");
        if (playerActionsContainer == null) {
            playerActionsContainer = new VerticalContainer(10, playerChoices);
        }
      
        //See if player has been here before
        boolean firstTime = !player.flagExists(BUNKER_FIRST_TIME);
        
        //If player is here for first time
        if (firstTime) {
            //Read and print story
            Utility.readFileAndPrint(storyFile, "ACT1-SCENE5", "ACT1-SCENE6", () -> {

                //Add progress flag
                player.addProgressFlag(new ProgressFlags(BUNKER_FIRST_TIME, true));

                //Restore player health to maximun and save game
                player.setHealth(player.getMaxHealth());
                //Savegame logic here

                //update player actions
                Utility.updatePlayerActions(playerActionsContainer, playerActions, playerChoices);
            });
        }
        else{
            Utility.updatePlayerActions(playerActionsContainer, playerActions, playerChoices);
        }

        //OPTION A
        playerActions.getFirstButton().setOnAction(e -> {player.toggleCharacterInfoStatus();});


        //OPTION B
        playerActions.getSecondButton().setOnAction(e -> {
            
            Utility.Print(Utility.playerRest, Utility.ActionSpeed, () -> {});

            //SAVE GAME AND HEALTH RESTORE LOGIC HERE
        });

        //OPTION C
        playerActions.getThirdButton().setOnAction(e -> {

            //If player doesnt have said flag, goes to calls function
            if (!player.flagExists("BunkerArmoryClear")){

                Utility.clearPlayerActions(playerActionsContainer, playerActions);
                Utility.Print("You go to Armory\n", Utility.ActionSpeed, () -> {
                    BunkerArmory(player); //Go to armory
                });
            }
            else{
                Utility.Print(Utility.areaClear, Utility.ActionSpeed, () -> {});
            }
        });


        //OPTION D
        playerActions.getFourthButton().setOnAction(e -> {
            
            //String to print when leaving bunker
            String headingOut = "Jaxon: Heading out!\n";
            if (!player.flagExists(PATH_DISCOVERED)){
                headingOut = "Jaxon: Lets see what is out there\n";
            }

            //clear player options
            Utility.clearPlayerActions(playerActionsContainer, playerActions);

            Utility.Print(headingOut, Utility.ActionSpeed, () -> {
                Utility.centerContainer.getChildren().remove(playerActionsContainer);
                CalyraPath.Crossroads(player); //Exit bunker
            });
        });

    }
    
}
