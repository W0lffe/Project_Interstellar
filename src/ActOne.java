import java.util.ArrayList;

public class ActOne {
    
    //Files to read
    private static final String storyFile = "ActOne/ActOne.txt";
    private static final String shipConsole = "ActOne/Console1.txt";

    /**@description Arraylist to contain enemy NPC objects */
    private static ArrayList<NPC> enemyList = new ArrayList<>();

    /**@description reference to container object, used throughout this file */
    private static VerticalContainer playerActionsContainer = new VerticalContainer(10, "");
    /**@description reference to player actions object, used throughout this file */
    private static HorizontalPlayerActions playerActions;
  

    /**@description possible enemy types for this file*/
    private static final String[] enemyType = {"Ra'kra"};

    /**@description Reference to string that is set to playerActionsContainer throughout this file*/
    private static String playerChoices;

    private static boolean partOneLocker = false;
    private static boolean partOneConsole = false;

     /**
     * @description Start of Act 1
     * @param player player object
     */
    public static void Start(Player player) {

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
                                        Utility.centerContainer.getChildren().remove(playerActionsContainer);
                                        CalyraBunker.BunkerArmory(player); //Exit PartOne
                                    }
                                });
                            });
                        }
                    });
                });
            });
        });
    }
}
