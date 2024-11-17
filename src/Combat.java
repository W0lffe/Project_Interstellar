import java.util.ArrayList;
import java.util.Scanner;;

public class Combat {

    private static boolean fight = false;

    public static void FightMenu(Player player, ArrayList<NPC> enemy, Scanner action) {
        fight = true; //set fight true
        String userAction;

        do {
         
            //print player and enemy healths
            int enemies = enemy.size();
            if (enemy.isEmpty()) {
                fight = false;
                break;
            }
          
            if (enemies == 1) {
                    
                System.out.println(
                        player.getName() + " Health: " + player.getHealth() + "/" + player.getMaxHealth() + "       " +
                        enemy.get(0).getName() + " Health: " + enemy.get(0).getHealth() + "/" + enemy.get(0).getMaxHealth());

                System.out.println("A) Character \nB) Attack " + enemy.get(0).getName());
                userAction = Validation.UserInput(action);

                switch (userAction) {
                    case "A":
                        //player.Character(action); //Show character
                        break;
                    case "B":
                        Attack(player, enemy.get(0)); //Attack enemy
                        if(!enemy.get(0).isAlive()){
                            enemy.remove(0);
                        }
                        break;
                    default:
                        Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                        break;
                }
            }
            else if (enemies == 2) {
                    
                System.out.println(player.getName() + " Health: " + player.getHealth() + "/" + player.getMaxHealth() + "\n");
                for (NPC npc : enemy) {
                    System.out.println(npc.getName() + " Health: " + npc.getHealth() + "/" + npc.getMaxHealth());
                }

                System.out.println("\nA) Show Character" +
                                    "\nB) Attack " + enemy.get(0).getName() +
                                    "\nC) Attack " + enemy.get(1).getName());
                userAction = Validation.UserInput(action);

                switch (userAction) {
                    case "A":
                        //player.Character(action); //Show character
                        break;
                    case "B":
                        Attack(player, enemy.get(0)); //Attack enemy
                        if(!enemy.get(0).isAlive()){
                            enemy.remove(0);
                        }
                        break;
                    case "C":
                        Attack(player, enemy.get(1)); //Attack enemy
                        if(!enemy.get(1).isAlive()){
                            enemy.remove(1);
                        }
                        break;
                    default:
                        Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                        break;
                }
            }
            else if (enemies == 3) {
                    
                System.out.println(player.getName() + " Health: " + player.getHealth() + "/" + player.getMaxHealth() + "\n");
                for (NPC npc : enemy) {
                    System.out.println(npc.getName() + " Health: " + npc.getHealth() + "/" + npc.getMaxHealth());
                }

                System.out.println("\nA) Show Character" +
                                    "\nB) Attack " + enemy.get(0).getName() +
                                    "\nC) Attack " + enemy.get(1).getName() +
                                    "\nD) Attack " + enemy.get(2).getName());
                userAction = Validation.UserInput(action);

                switch (userAction) {
                    case "A":
                        //player.Character(action); //Show character
                        break;
                    case "B":
                        Attack(player, enemy.get(0)); //Attack enemy
                        if(!enemy.get(0).isAlive()){
                            enemy.remove(0);
                        }
                        break;
                    case "C":
                        Attack(player, enemy.get(1)); //Attack enemy
                        if(!enemy.get(1).isAlive()){
                            enemy.remove(1);
                        }
                        break;
                    case "D":
                        Attack(player, enemy.get(2)); //Attack enemy
                        if(!enemy.get(2).isAlive()){
                            enemy.remove(2);
                        }
                        break;
                    default:
                        Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                        break;
                }
            }
            else if (enemies == 4) {
                    
                System.out.println(player.getName() + " Health: " + player.getHealth() + "/" + player.getMaxHealth() + "\n");
                for (NPC npc : enemy) {
                    System.out.println(npc.getName() + " Health: " + npc.getHealth() + "/" + npc.getMaxHealth());
                }

                System.out.println("\nA) Show Character" +
                                    "\nB) Attack " + enemy.get(0).getName() +
                                    "\nC) Attack " + enemy.get(1).getName() +
                                    "\nD) Attack " + enemy.get(2).getName() +
                                    "\nE) Attack " + enemy.get(3).getName());
                userAction = Validation.UserInput(action);

                switch (userAction) {
                    case "A":
                        //player.Character(action); //Show character
                        break;
                    case "B":
                        Attack(player, enemy.get(0)); //Attack enemy
                        if(!enemy.get(0).isAlive()){
                            enemy.remove(0);
                        }
                        break;
                    case "C":
                        Attack(player, enemy.get(1)); //Attack enemy
                        if(!enemy.get(1).isAlive()){
                            enemy.remove(1);
                        }
                        break;
                    case "D":
                        Attack(player, enemy.get(2)); //Attack enemy
                        if(!enemy.get(2).isAlive()){
                            enemy.remove(2);
                        }
                        break;
                    case "E":
                        Attack(player, enemy.get(3)); //Attack enemy
                        if(!enemy.get(3).isAlive()){
                            enemy.remove(3);
                        }
                        break;
                    default:
                        Utility.Print(Utility.cantDoThat, Utility.ActionSpeed);
                        break;
                }
            }
        } while (fight); //break when fight is set false or when player dies
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
            player.setExperience(player.getExperience() + enemy.getExperience()); //Give player experience of enemy's experience property
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
            fight = false; //if player dies, fight is over
            return;
        }

    }
}
