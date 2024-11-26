import java.util.ArrayList;

public class CalyraBunker {
    
    //Files to read
    private static final String storyFile = "ActOne/ActOne.txt";
    private static final String shipConsole = "ActOne/Console1.txt";
    private static final String armoryTerminal = "ActOne/Console2.txt";

    /**@description Arraylist to contain enemy NPC objects */
    private static ArrayList<NPC> enemyList = new ArrayList<>();

    /**@description reference to container object, used throughout this file */
    private static VerticalContainer playerActionsContainer = new VerticalContainer(10, "");
    /**@description reference to player actions object, used throughout this file */
    private static HorizontalPlayerActions playerActions;

    /**@description possible enemy types for this file*/
    private static final String[] enemyType = {"Ra'kra", "Bandit"};

    /**@description Reference to string that is set to playerActionsContainer throughout this file*/
    private static String playerChoices;

    private static boolean partOneLocker = false;
    private static boolean partOneConsole = false;

    //PROGRESS FLAGS
    private static final String ARMORY_FIRST_TIME = "BunkerArmory";
    private static final String BUNKER_RACK_LOOTED = "ArmoryRackLooted";
    private static final String BUNKER_LOCKER_LOOTED = "ArmoryCabinetUnlocked";
    private static final String BUNKER_TERMINAL_READ = "ArmoryTerminalRead";
    private static final String BUNKER_ARMORY_CLEARED = "BunkerArmoryClear";

    private static final String BUNKER_FIRST_TIME = "BunkerMain";
    private static final String PATH_DISCOVERED = "PathToColony";


    /**
     * @description Start of Act 1
     * @param player player object
     */
    public static void ActOne(Player player) {

        //Possible choices for player
        playerChoices = "A) Show Character B) Loot Locker  C) Activate Console D) Exit";
        playerActions = new HorizontalPlayerActions(10, "", "Character", "Loot Locker", "Activate Console", "Exit");

        //Print story to textarea
        Utility.readFileAndPrint(storyFile, "ACT1-SCENE1", "ACT1-SCENE2", () -> {
            //Update player actions
            Utility.updatePlayerActions(playerActionsContainer, playerActions, playerChoices);
        });

        playerActions.getFirstButton().setOnAction(e -> {player.toggleCharacterInfoStatus(); });

        playerActions.getSecondButton().setOnAction(e -> {

            //If player has not opened locker yet
            if (!partOneLocker) {
                player.lootItems(LootLists.retrieveRandomLootList("Meds"), "locker");
                partOneLocker = true; //has opened
            }
            else{
                Utility.Print(Utility.isLooted, Utility.ActionSpeed, () -> {});
            }

        });
        
        playerActions.getThirdButton().setOnAction(e -> {

            //If player has not activated console yet
            if (!partOneConsole) {
                Utility.Print(Utility.activateConsole ,Utility.ActionSpeed, () -> {

                    Utility.readFileAndPrint(shipConsole, "", "", () -> {
                    
                        Utility.Print("Jaxon: The ship is in bad shape, but at least the life support is still operational.\n",Utility.ActionSpeed, () -> {
                            player.actionExperience(Utility.LoreItemEXP); //Gain experience
                            partOneConsole = true; //has activated
                        });
                    });
                });
            }
            else{
                Utility.Print(Utility.isRead, Utility.ActionSpeed, () -> {});
            }
        });

        playerActions.getFourthButton().setOnAction(e -> {

            //Clear player actions before combat
            Utility.clearPlayerActions(playerActionsContainer, playerActions);

            Utility.Print("You exit the ship through emergency hatch.\n", Utility.ActionSpeed, () -> {

                //Read and print story
                Utility.readFileAndPrint(storyFile, "ACT1-SCENE2", "ACT1-SCENE3", () -> {

                //Create enemylist
                enemyList = NPC.createEnemyList(2, enemyType[0]);

                    //Enter combat
                    Combat.FightMenu(player, enemyList, () -> {

                        //If player is alive after combat
                        if (player.isAlive()) {

                            //Read and print story
                            Utility.readFileAndPrint(storyFile, "ACT1-SCENE3", "ACT1-SCENE4", () -> {

                                //Init new weapon object to be used in this instance
                                Weapon acidSpit = new Weapon("Acid Spit", "Projectile Weapon", 8, 18, "", 1);

                                //Create NPC objects
                                enemyList.add(new NPC(20, 20, "Skitter Soldier", 15, "Creature", acidSpit, null));
                                enemyList.add(new NPC(20, 20, "Skitter Soldier", 15, "Creature", acidSpit, null));
                                enemyList.add(new NPC(50, 50, "Skitter Guardian", 25, "Creature", acidSpit, null));
                                enemyList.add(new NPC(100, 100, "Alpha Skitter", 50, "Creature", acidSpit, null));

                                //Enter combat
                                Combat.FightMenu(player, enemyList, () -> {
                                    
                                    //If player is alive after combat, exit this function/part
                                    if (player.isAlive()) {
                                        BunkerArmory(player); //Exit PartOne
                                    }
                                });
                            });
                        }
                    });
                });
            });
        });
    }


    private static void BunkerArmory(Player player){

        //Set new player choices and actions
        playerChoices = "A) Character B) Look at the locker C) Inspect terminal D) Inspect the weapon rack E) Go to Main Room";
        playerActions = new HorizontalPlayerActions(10,"", "Character", "Loot Locker", "Activate terminal", "Loot Rack", "Exit Room");
             
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

        playerActions.getFirstButton().setOnAction(e -> {player.toggleCharacterInfoStatus();});

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

        
        playerActions.getFirstButton().setOnAction(e -> {player.toggleCharacterInfoStatus();});

        playerActions.getSecondButton().setOnAction(e -> {
            
            Utility.Print(Utility.playerRest, Utility.ActionSpeed, () -> {});

            //SAVE GAME AND HEALTH RESTORE LOGIC HERE
        });

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

        playerActions.getFourthButton().setOnAction(e -> {
           
            //clear player options
            Utility.clearPlayerActions(playerActionsContainer, playerActions);
            
            //If player does not have said flag
            if (!player.flagExists(PATH_DISCOVERED)) {
                Utility.Print("Jaxon: Lets see what is out there\n", Utility.ActionSpeed, () -> {
                    PathToColony(player); //Exit bunker
                });
            }
            else{
                Utility.Print("Jaxon: Heading out!\n", Utility.ActionSpeed, () -> {
                    PathToColony(player); //Exit bunker

                });
            }
        });

    }

    public static void PathToColony(Player player){

        //Set new player choices and actions
        playerChoices = "A) Character B) Go West C) Go South D) Go North E) Go back to Bunker";
        playerActions = new HorizontalPlayerActions(10,"", "Character", "West", "South", "North", "Back to Bunker");
 
        //See if player has been here before
        boolean firstTime = !player.flagExists(PATH_DISCOVERED);

        //If player is here for first time
        if (firstTime) {

            //Read and print story
            Utility.readFileAndPrint(storyFile, "ACT1-SCENE6", "ACT1-SCENE7", () -> {

                //Create NPC list
                enemyList = NPC.createEnemyList(2, enemyType[1]);
                
                //Enter combat
                Combat.FightMenu(player, enemyList, () -> {
                    
                    //If player is alive after combat
                    if(player.isAlive()){

                        //Add progress flag
                        player.addProgressFlag(new ProgressFlags(PATH_DISCOVERED, true));

                        //Read and print story
                        Utility.readFileAndPrint(storyFile, "ACT1-SCENE7", "ACT1-SCENE8", () -> {
                            
                            //Update player actions
                            Utility.updatePlayerActions(playerActionsContainer, playerActions, playerChoices);
                        });
                    }
                });
            });
        }
        else{
            //Update player actions
            Utility.updatePlayerActions(playerActionsContainer, playerActions, playerChoices);
        }

        playerActions.getFirstButton().setOnAction(e -> {player.toggleCharacterInfoStatus();});

        playerActions.getSecondButton().setOnAction(e -> {
            
            Utility.Print("Jaxon: Lets find ourselves a ship.\n", Utility.ActionSpeed, () -> {});
                    // Go west SPACEPORT
        });

        playerActions.getThirdButton().setOnAction(e -> {
            
            Utility.Print("Jaxon: Lets see if I find any answers.\n", Utility.ActionSpeed, () -> {});
                    // Go south ENEMY BASE
        });

        playerActions.getFourthButton().setOnAction(e -> {
            
            //if player does not have flag
            if (!player.flagExists("CalyraCaveClear")) {
                
               //clear player options
                Utility.clearPlayerActions(playerActionsContainer, playerActions);

                //Based on if flag exists, print string
                if (!player.flagExists("CalyraCaveFound")) {
                    Utility.Print("Jaxon: I wonder what is there.\n", Utility.ActionSpeed, () -> {});
                }
                else{
                    Utility.Print("Jaxon: To the nasty cave I go!\n", Utility.ActionSpeed, () -> {});
                }

                Utility.centerContainer.getChildren().remove(playerActionsContainer);
                CalyraCave.Cave(player); //To next area, exit this function
            }
            else{
                Utility.Print(Utility.areaClear, Utility.ActionSpeed, () -> {});
            }
        });
    
        playerActions.getFifthButton().setOnAction(e -> {

            //clear player options
            Utility.clearPlayerActions(playerActionsContainer, playerActions);

            Utility.Print("You go back to Bunker\n", Utility.ActionSpeed, () -> {
                BunkerMainRoom(player); //Go back to previous function
            });
        });
    }

}
