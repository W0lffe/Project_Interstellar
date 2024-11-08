import java.util.Scanner;;

public class Combat {
    
    private static boolean fight = false;

    public static void FightMenu(Player player, NPC enemy, Scanner action){
        fight = true;
        String userAction;

        do {
            System.out.println(player.getName() + " Health: " + player.getHealth() + "/" + player.getMaxHealth() + "       " +
                               enemy.getName() + " Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
            
            System.out.println("A) Character \nB) Attack " + enemy.getName());
            userAction = Validation.UserInput(action);

            switch (userAction) {
                case "A":
                    player.Character(action);
                    break;
                case "B":
                    Attack(player, enemy);
                    break;
                default:
                    System.out.println(Utility.cantDoThat);
            }
        } while (fight);
    }

    private static void Attack(Player player, NPC enemy){

        Items playerEquipped = player.getEquipped();
        Items enemyEquipped = enemy.getEquipped();

        int damage = (int)(Math.random()*(playerEquipped.getMaxDamage() - playerEquipped.getMinDamage() + 1) + playerEquipped.getMinDamage());
        System.out.println("You strike with " + playerEquipped.getItem() + ", dealing " + damage + " damage!");
        enemy.takeDamage(damage);

        if(enemy.getHealth() <= 0){
            enemy.setAlive(false);
            fight = false;
            System.out.printf("You eliminated %s, gaining %d experience!\n", enemy.getName(), enemy.getExperience());
            player.setExperience(player.getExperience() + enemy.getExperience());
            return;
        }
      
        damage = (int)(Math.random()*(enemyEquipped.getMaxDamage() - enemyEquipped.getMinDamage() + 1) + enemyEquipped.getMinDamage());
        System.out.println(enemy.getName() + " counters, dealing " + damage + " damage!");
        player.takeDamage(damage);

        if(player.getHealth() <= 0){
            player.setAlive(false);
            fight = false;
            return;
        }
    

    }
}
