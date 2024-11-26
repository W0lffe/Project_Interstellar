import java.util.ArrayList;

public class CalyraPath {

    //Files to read
    private static final String storyFile = "ActOne/ActOne.txt";

    //Progress flag
    private static final String PATH_DISCOVERED = "PathToColony";

    /**@description reference to container object, used throughout this file */
    private static VerticalContainer playerActionsContainer = new VerticalContainer(10, "");
    /**@description reference to player actions object, used throughout this file */
    private static HorizontalPlayerActions playerActions;
 
    /**@description Arraylist to contain enemy NPC objects */
    private static ArrayList<NPC> enemyList = new ArrayList<>();

    /**@description possible enemy types for this file*/
    private static final String[] enemyType = {"Ra'kra", "Bandit"};

    /**@description Reference to string that is set to playerActionsContainer throughout this file*/
    private static String playerChoices;

    public static void Crossroads(Player player){

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

        //OPTION A
        playerActions.getFirstButton().setOnAction(e -> {player.toggleCharacterInfoStatus();});

        //OPTION B
        playerActions.getSecondButton().setOnAction(e -> {
            
            Utility.Print("Jaxon: Lets find ourselves a ship.\n", Utility.ActionSpeed, () -> {});
                    // Go west SPACEPORT
        });

        //OPTION C
        playerActions.getThirdButton().setOnAction(e -> {
            
            Utility.Print("Jaxon: Lets see if I find any answers.\n", Utility.ActionSpeed, () -> {});
                    // Go south ENEMY BASE
        });

        //OPTION D
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
    
        //OPTION E
        playerActions.getFifthButton().setOnAction(e -> {

            //clear player options
            Utility.clearPlayerActions(playerActionsContainer, playerActions);

            Utility.Print("You go back to Bunker\n", Utility.ActionSpeed, () -> {
                CalyraBunker.BunkerMainRoom(player); //Go back to previous function
            });
        });
    }

}
