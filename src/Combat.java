import java.util.ArrayList;
/**@class Combat
 * @description Holds all combat related mechanics of game
 */
public class Combat {

    /**
     * @description creates fight menu to screen by updating player actions and status containers
     * @param player player object
     * @param enemyList ArrayList of enemis typed to NPC
     * @param onCombatEnd Runnable function after combat ending
     */
    public static void FightMenu(Player player, ArrayList<NPC> enemyList, Runnable onCombatEnd) {

        //Create new actions for player
        HorizontalPlayerActions playerActions = new HorizontalPlayerActions(10, "", "Character", "Attack");

        //Create choice box for enemies, update it with list of enemies, add to container
        CombatChoicesBox enemies = new CombatChoicesBox(5, "Select opponent");
        enemies.updateEnemyChoices(enemyList);
        playerActions.getChildren().add(enemies);

        //Append player actions container to root
        Utility.centerContainer.getChildren().addAll(playerActions);

        //Init list of status containers of enemies
        ArrayList<VerticalStatus> enemyStatusList = initList(player, enemyList);

        playerActions.getFirstButton().setOnAction(e -> { 
            
            //toggle player character info and status containers
            player.toggleCharacterInfoStatus(); 
            
            //Loop vertical status containers in list
            for (VerticalStatus verticalStatus : enemyStatusList) {

                //if containers are not in root, then append, else remove
                if (!Utility.rightContainer.getChildren().contains(verticalStatus)) {
                        Utility.rightContainer.getChildren().add(verticalStatus);
                }
                else{
                    Utility.rightContainer.getChildren().remove(verticalStatus);
                }
            }
        });

        playerActions.getSecondButton().setOnAction(e -> {

            //When button is pressed, selected value of choicebox is placed to this variable
            String selectedEnemy = enemies.getEnemiesChoiceBox().getValue();

            //if value is not null
            if (selectedEnemy != null) {

                //Get index of selected value from choicebox
                int selectedEnemyIndex = enemies.getEnemiesChoiceBox().getItems().indexOf(selectedEnemy);

                //Place selected enemy to object by selecting the enemy from arraylist with index
                NPC enemy = enemyList.get(selectedEnemyIndex);

                //Attack enemy
                Attack(player, enemy, () -> {

                    //If enemy health is 0 or less
                    if(enemy.getHealth() <= 0){

                        //If enemy object has inventory, player loots inventory
                        if (enemy.getInventory() != null) {
                            player.lootItems(enemy.getInventory(), enemy.getName());
                        }

                        //Remove status container of enemy from root
                        Utility.rightContainer.getChildren().remove(enemy.getStatusContainer());

                        //Remove status container from list
                        enemyStatusList.remove(enemy.getStatusContainer());

                        //Remove enemy NPC from list
                        enemyList.remove(enemy);

                        //Remove choice from choicebox
                        enemies.deleteEnemyFromChoices(selectedEnemyIndex);
                        
                        //If list of enemies is not empty, choicebox value will be set to first value
                        if (!enemyList.isEmpty()) {
                            enemies.getEnemiesChoiceBox().setValue(enemies.getEnemiesChoiceBox().getItems().getFirst());
                        }
                    }
                    //If enemy list is empty or player is not alive, player actions container will be removed and combat ends
                    if(enemyList.isEmpty() || !player.isAlive()){
                        Utility.centerContainer.getChildren().remove(playerActions);
                        onCombatEnd.run();
                    }

                });
/* 
                 //If enemy health is 0 or less
                 if(enemy.getHealth() <= 0){

                    //If enemy object has inventory, player loots inventory
                    if (enemy.getInventory() != null) {
                        player.lootItems(enemy.getInventory(), enemy.getName());
                    }

                    //Remove status container of enemy from root
                    Utility.rightContainer.getChildren().remove(enemy.getStatusContainer());

                    //Remove status container from list
                    enemyStatusList.remove(enemy.getStatusContainer());

                    //Remove enemy NPC from list
                    enemyList.remove(enemy);

                    //Remove choice from choicebox
                    enemies.deleteEnemyFromChoices(selectedEnemyIndex);
                    
                    //If list of enemies is not empty, choicebox value will be set to first value
                    if (!enemyList.isEmpty()) {
                        enemies.getEnemiesChoiceBox().setValue(enemies.getEnemiesChoiceBox().getItems().getFirst());
                    }
                }

                //If enemy list is empty or player is not alive, player actions container will be removed and combat ends
                if (enemyList.isEmpty() || !player.isAlive()) {
                    Utility.centerContainer.getChildren().remove(playerActions);
                    onCombatEnd.run();
                } */
            }
            else{
                Utility.Print("Select opponent first!\n", Utility.ActionSpeed, () -> {});
            }
        });

    }

    /**
     * @description Function initiates arraylist with status containers of NPC objects in list of enemies
     * @param player player object
     * @param enemyList ArrayList of enemies typed to NPC
     * @return ArrayList of status containers
     */
    private static ArrayList<VerticalStatus> initList(Player player, ArrayList<NPC> enemyList){
        //Create temporary arraylist
        ArrayList<VerticalStatus> temporary = new ArrayList<>();

        //Loop NPC in enemyList
        for (NPC npc : enemyList) {
            //If NPC doesnt have status container
            if (npc.getStatusContainer() == null) {
                //Create container initialized with NPC name
                VerticalStatus enemy = new VerticalStatus(5, npc.getName() , "");
                //Set status container for NPC
                npc.setStatusContainer(enemy);
                //Update status container
                npc.enemyStatusUpdate();
                //Add to temporary list
                temporary.add(enemy);

                //If root container has players status container, enemy containers will be appended
                if (Utility.rightContainer.getChildren().contains(player.getStatusContainer())) {
                    Utility.rightContainer.getChildren().addAll(enemy);
                }
            }
        }

        //Return arraylist of status containers
        return temporary;
    }

       

    /**
     * @description Handles player vs npc combat
     * @param player player object
     * @param enemy npc object
     */
    public static void Attack(Player player, NPC enemy, Runnable attackFinished) {

        playerAttack(player, enemy, () -> {

            //if enemy health is 0 or less
            if (enemy.getHealth() <= 0) {
                String eliminated = "You eliminated " + enemy.getName() + "\n";
                Utility.Print(eliminated, Utility.ActionSpeed, () -> {
                    player.actionExperience(enemy.getExperience()); //Give player experience of enemy's experience property
                    attackFinished.run();
                });
            }
            else{
                npcAttack(player, enemy, () -> {

                    //if player health is 0 or less
                    if (player.getHealth() <= 0) {
                        String eliminated = "You have been eliminated...\n";
                        Utility.Print(eliminated, Utility.ActionSpeed, () -> {
                            player.setAlive(false); //player is not alive
                            attackFinished.run();
                        });
                    }
                });
            }
        });
    }

    private static void playerAttack(Player player, NPC enemy, Runnable afterAttack){
        
        //get Weapon object that player equips
        Weapon playerEquipped = player.getEquipped();

        //minimum and maximum damages of said objects
        int playerMaxDmg = playerEquipped.getMaxDamage();
        int playerMinDmg = playerEquipped.getMinDamage();

        //if player has Weapons skill, increase damage
        for (Skills skill : player.getPlayerAcquiredSkills()) {
              
            if (skill.getSkill().equals("Weapons")){
                  playerMaxDmg += 5;
                  playerMinDmg += 5;
              }
          }


        //Get random damage from equipped item range
        int damage = (int) (Math.random() * ((playerMaxDmg - playerMinDmg) + 1) + playerMinDmg);
        String damageDealt = "You strike with " + playerEquipped.getItem() + ", dealing " + damage + " damage!\n";
        enemy.takeDamage(damage); //reduce enemy health
        Utility.Print(damageDealt, Utility.ActionSpeed, () -> {
          afterAttack.run();; 
        });
    }

    private static void npcAttack(Player player, NPC enemy, Runnable afterAttack){

        //get Weapon object that enemy equips
        Weapon enemyEquipped = enemy.getEquipped();      
        
        //minimum and maximum damages of said objects
        int enemyMinDmg = enemyEquipped.getMinDamage();
        int enemyMaxDmg = enemyEquipped.getMaxDamage();

        //Get random damage from equipped item range
        int damage = (int) (Math.random() * ((enemyMaxDmg - enemyMinDmg) + 1) + enemyMinDmg);
        String damageDealt = enemy.getName() + " counters with " + enemy.getEquipped().getItem() + ", dealing " + damage + " damage!\n";
        player.takeDamage(damage); //Reduce player health
        Utility.Print(damageDealt, Utility.ActionSpeed, () -> {
            afterAttack.run();
        });
    }
}



