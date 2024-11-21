import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class NPC {
    
    private int maxHealth;
    private int health;
    private String name;
    private int experience;
    private boolean alive;
    private Weapon equipped;
    private ArrayList<Items> inventory;
    private VerticalStatus statusContainer;
    private static ArrayList<NPC> predefinedRakra = new ArrayList<>();
    private static ArrayList<NPC> predefinedBandits = new ArrayList<>();

    /**
     * @description Constructor for NPC object
     * @param maxHealth maximun health of NPC
     * @param health current health of NPC
     * @param name Name of NPC
     * @param experience How much experience does this NPC give
     * @param alive Is NPC alive
     * @param equipped What NPC equips
     * @param inventory Inventory of NPC
     */
    public NPC(int maxHealth, int health, String name, int experience, boolean alive, Weapon equipped, ArrayList<Items> inventory) {
        this.maxHealth = maxHealth;
        this.health = health;
        this.name = name;
        this.experience = experience;
        this.alive = alive;
        this.equipped = equipped;
        this.inventory = inventory;
    }

    /**
     * 
     * @return maximum health of NPC as Integer
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * @description sets max health to NPC
     * @param maxHealth value to be set as max health
     */ 
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * 
     * @return current health of NPC as Integer
     */
    public int getHealth() {
        return health;
    }

    /**
     * @description set current health of NPC
     * @param health value to be set as current health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * 
     * @return name of NPC as string
     */
    public String getName() {
        return name;
    }
    /**
     * @description set name for NPC
     * @param name value to be set as name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return experience that NPC gives as Integer
     */
    public int getExperience() {
        return experience;
    }

    /**
     * @description set experience amount to NPC
     * @param experience value to be set as experience amount
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }
    /**
     * 
     * @return true or false if NPC is alive or not
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @description set alive status for NPC
     * @param alive true or false
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    /**
     * 
     * @return Weapon object that NPC equips
     */
    public Weapon getEquipped() {
        return equipped;
    }

    /**
     * @description set Weapon object for NPC to be equipped
     * @param equipped Weapon object to be set
     */
    public void setEquipped(Weapon equipped) {
        this.equipped = equipped;
    }
    /**
     * 
     * @return inventory of NPC as ArrayList typed Items
     */
    public ArrayList<Items> getInventory() {
        return inventory;
    }

    /**
     * @description set inventory for NPC
     * @param inventory ArrayList typed to Items
     */
    public void setInventory(ArrayList<Items> inventory) {
        this.inventory = inventory;
    }
    /**
     * @return set NPC status container
     */
    public VerticalStatus getStatusContainer() {
        return statusContainer;
    }
    /**
     * @description: set status container for NPC
     * @param statusContainer object created in combat
     */
    public void setStatusContainer(VerticalStatus statusContainer) {
        this.statusContainer = statusContainer;
    }

    /**
     * @param damage taken damage in combat
     * @description function reduces taken damage from npc health
     */
    public void takeDamage(int damage){
        health -= damage;

        if (health <= 0) {
            setAlive(false);
        }

        enemyStatusUpdate();
    }

    /**
     * @description function updates NPC status containers in combat, converts NPC properties to string
     */
    public void enemyStatusUpdate(){
        String enemyStatus = name + " | Health: " + health + "/"+ maxHealth;
        statusContainer.updateEnemyStatus(enemyStatus);        
    }

    /**@description Creates 2 preset enemy npc lists */
    public static void initEnemyLists(){
        NPC rakraScout = new NPC(40, 40, "Ra'kra Scout", 20, true, Weapon.PULSE_PISTOL, ItemLootLists.randomLootForNPC());
        NPC rakraRookie = new NPC(50, 50, "Ra'kra Rookie", 40, true, Weapon.PULSE_PISTOL, ItemLootLists.randomLootForNPC());
        NPC rakraBrute = new NPC(75, 75, "Ra'kra Brute", 50, true, Weapon.SENTRY_CARBINE, ItemLootLists.randomLootForNPC());
        NPC rakraOfficer = new NPC(100, 100, "Ra'kra Officer", 75, true, Weapon.PULSE_RIFLE, ItemLootLists.randomLootForNPC());
        predefinedRakra.addAll(Arrays.asList(rakraBrute, rakraOfficer, rakraRookie, rakraScout, rakraScout, rakraScout));

        NPC bandit = new NPC(50, 50, "Bandit", 75, true, Weapon.LASER_PISTOL, ItemLootLists.randomLootForNPC());
        NPC bandit2 = new NPC(75, 75, "Bandit", 75, true, Weapon.STINGER_PISTOL, ItemLootLists.randomLootForNPC());
        NPC banditLeader = new NPC(100, 100, "Bandit Leader", 75, true, Weapon.SENTRY_CARBINE, ItemLootLists.randomLootForNPC());
        predefinedBandits.addAll(Arrays.asList(bandit, bandit, bandit, bandit2, banditLeader));
    }

    /**
     * @description Creates arraylist filled with NPC, shuffles preset list and adds to returnable list
     * @param maxAmount Value of maximum NPC count
     * @param enemyType Value of enemy type, "Ra'kra"/"Bandit" available
     * @return ArrayList typed to NPC
     */
    public static ArrayList<NPC> createEnemyList(int maxAmount, String enemyType){
        ArrayList<NPC> temporary = new ArrayList<>();

        if (enemyType.equals("Ra'kra")){
            
            for (int i = 0; i < maxAmount; i++) {
                Collections.shuffle(predefinedRakra);
                temporary.add(predefinedRakra.getFirst());
            }
        }
        else if(enemyType.equals("Bandit")){

            for (int i = 0; i < maxAmount; i++) {
                Collections.shuffle(predefinedBandits);
                temporary.add(predefinedBandits.getFirst());
            }
        }

        return temporary;
   } 
  





}
