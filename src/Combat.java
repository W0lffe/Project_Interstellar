import java.util.Scanner;;

public class Combat {

    private static boolean fight = false;

    public static void FightMenu(Player player, NPC enemy, Scanner action) {
        fight = true; //set fight true
        String userAction;

        do {
            //print player and enemy healths
            System.out.println(
                    player.getName() + " Health: " + player.getHealth() + "/" + player.getMaxHealth() + "       " +
                            enemy.getName() + " Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());

            System.out.println("A) Character \nB) Attack " + enemy.getName());
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    player.Character(action); //Show character
                    break;
                case "B":
                    Attack(player, enemy); //Attack enemy
                    break;
                default:
                    System.out.println(Utility.cantDoThat);
            }
        } while (fight); //break when fight is set false
    }

    private static void Attack(Player player, NPC enemy) {
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
            player.setExperience(player.getExperience() + enemy.getExperience()); //Give player experience of enemy's experience property
            fight = false; //fight is over
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
            fight = false; //fight is over
            return;
        }

    }
}
