import java.util.ArrayList;
import javafx.scene.Scene;

public class Combat {

    public static Scene combatScene;


    public static void FightMenu(Player player, ArrayList<NPC> enemyList, Runnable onCombatEnd) {
        ArrayList<VerticalStatus> enemyStatusList = new ArrayList<>();

        for (NPC npc : enemyList) {
            if (npc.getStatusContainer() == null) {
                VerticalStatus enemy = new VerticalStatus(5, npc.getName() , "");
                npc.setStatusContainer(enemy);
                npc.enemyStatusUpdate();
                enemyStatusList.add(enemy);

                if (Utility.rightContainer.getChildren().contains(player.getStatusContainer())) {
                    Utility.rightContainer.getChildren().addAll(enemy);
                }
            }
        }

        Utility.centerContainer.getChildren().remove(Utility.centerContainer.getPlayerActions());

        HorizontalPlayerActions playerActions = new HorizontalPlayerActions(10, "", "Show Character", "Attack");
        CombatChoicesBox enemies = new CombatChoicesBox(5, "Select opponent");
        enemies.updateEnemyList(enemyList);
        playerActions.getChildren().add(enemies);

        Utility.centerContainer.getChildren().addAll(playerActions);

        playerActions.getFirstButton().setOnAction(e -> {
            for (VerticalStatus verticalStatus : enemyStatusList) {
                if (!Utility.rightContainer.getChildren().contains(verticalStatus)) {
                        Utility.rightContainer.getChildren().add(verticalStatus);
                }
                else{
                    Utility.rightContainer.getChildren().remove(verticalStatus);
                }
            }

            if (!Utility.leftContainer.getChildren().contains(player.getCharacterInfo()) && 
                !Utility.rightContainer.getChildren().contains(player.getStatusContainer())) {

                Utility.leftContainer.getChildren().add(player.getCharacterInfo());
                Utility.rightContainer.getChildren().add(player.getStatusContainer());
                
            }
            else{
                Utility.leftContainer.getChildren().remove(player.getCharacterInfo());
                Utility.rightContainer.getChildren().remove(player.getStatusContainer());
            }
        });

        playerActions.getSecondButton().setOnAction(e -> {
            String selectedEnemy = enemies.getEnemiesChoiceBox().getValue();

            if (selectedEnemy != null) {
                int selectedEnemyIndex = enemies.getEnemiesChoiceBox().getItems().indexOf(selectedEnemy);
                NPC enemy = enemyList.get(selectedEnemyIndex);
                Attack(player, enemy);

                    if(enemy.getHealth() <= 0){
                        Utility.rightContainer.getChildren().remove(enemy.getStatusContainer());
                        enemy.setAlive(false);
                        enemyStatusList.remove(enemy.getStatusContainer());
                        enemyList.remove(enemy);
                        enemies.deleteEnemyFromList(selectedEnemyIndex);
                    }

                    if (enemyList.isEmpty() || !player.isAlive()) {
                        Utility.centerContainer.getChildren().remove(playerActions);
                        onCombatEnd.run();
                    }
            }
            else{
                Utility.Print("Select opponent first!\n", Utility.ActionSpeed);
            }
        });

    }

       

    public static void Attack(Player player, NPC enemy) {
        //get Weapon objects that player and enemy equips
        Weapon playerEquipped = player.getEquipped();
        Weapon enemyEquipped = enemy.getEquipped();

        //minimum and maximum damages of said objects
        int playerMaxDmg = playerEquipped.getMaxDamage();
        int playerMinDmg = playerEquipped.getMinDamage();
        int enemyMinDmg = enemyEquipped.getMinDamage();
        int enemyMaxDmg = enemyEquipped.getMaxDamage();

        //if player has Weapons skill, increase damage
        if (player.getPlayerSkills().contains(Skills.Weapons)){
            playerMaxDmg += 5;
            playerMinDmg += 5;
        }

        //Get random damage from equipped item range
        int damage = (int) (Math.random() * ((playerMaxDmg - playerMinDmg) + 1) + playerMinDmg);
        String damageDealt = "You strike with " + playerEquipped.getItem() + ", dealing " + damage + " damage!\n";
        Utility.Print(damageDealt, Utility.ActionSpeed);
        enemy.takeDamage(damage); //reduce enemy health

        //if enemy health is 0 or less
        if (enemy.getHealth() <= 0) {
            enemy.setAlive(false); //enemy is not alive
            String eliminated = "You eliminated " + enemy.getName() + ", gaining " + enemy.getExperience() + " experience!\n";
            Utility.Print(eliminated, Utility.ActionSpeed);
            player.actionExperience(enemy.getExperience()); //Give player experience of enemy's experience property
            return;
        }

        damage = (int) (Math.random() * ((enemyMaxDmg - enemyMinDmg) + 1) + enemyMinDmg);
        damageDealt = enemy.getName() + " counters, dealing " + damage + " damage!\n";
        Utility.Print(damageDealt, Utility.ActionSpeed);
        player.takeDamage(damage); //Reduce player health

        //if player health is 0 or less
        if (player.getHealth() <= 0) {
            String eliminated = "You have been eliminated... Returning to Main Menu.\n";
            Utility.Print(eliminated, Utility.ActionSpeed);
            player.setAlive(false); //player is not alive
            return;
        }

    }
}

