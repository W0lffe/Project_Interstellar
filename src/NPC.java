import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class NPC {
    
    private int maxHealth;
    private int health;
    private String name;
    private int experience;
    private String type;
    private Weapon equipped;
    private ArrayList<Items> inventory;
    private VerticalStatus statusContainer;
    private static ArrayList<NPC> rakraList = new ArrayList<>();
    private static ArrayList<NPC> banditList = new ArrayList<>();

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
    public NPC(int maxHealth, int health, String name, int experience, String type, Weapon equipped, ArrayList<Items> inventory) {
        this.maxHealth = maxHealth;
        this.health = health;
        this.name = name;
        this.experience = experience;
        this.type = type;
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
     * @return type of NPC
     */
    public String getType() {
        return type;
    }

    /**
     * @description set type for NPC
     * @param typeOfNPC type of NPC as String
     */
    public void setAlive(String typeOfNPC) {
        this.type = typeOfNPC;
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

        enemyStatusUpdate();
    }

    /**
     * @description function updates NPC status containers in combat, converts NPC properties to string
     */
    public void enemyStatusUpdate(){
        String enemyStatus = name + " (" + type + ") | Health: " + health + "/"+ maxHealth;
        statusContainer.updateEnemyStatus(enemyStatus);        
    }

    /**@description Creates 2 preset enemy npc lists */
    public static void initEnemyLists(int playerLevel){

        initRakraList(playerLevel);

        initBanditList(playerLevel);
        
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
                Collections.shuffle(rakraList);
                temporary.add(rakraList.getFirst());
            }
        }
        else if(enemyType.equals("Bandit")){

            for (int i = 0; i < maxAmount; i++) {
                Collections.shuffle(banditList);
                temporary.add(banditList.getFirst());
            }
        }

        return temporary;
   } 

   private static void initRakraList(int playerLevel){
        int numberOfNormals;
        int numberOfStrongs;
        int healthModifier;
        int expModifier;
        ArrayList<Weapon> retrievedWeaponList;
        
        if (playerLevel > 0 && playerLevel <= 5) {
            numberOfNormals = 6;
            numberOfStrongs = 4;
            healthModifier = 0;
            expModifier = 0;
            retrievedWeaponList = Weapon.retrieveWeaponList("Low Tier");
        }

        for (int i = 0; i < numberOfNormals/2; i++) {
            rakraList.add(new NPC(60+healthModifier, 60+healthModifier, "Ra'kra Scout", 20+expModifier, "Normal", Weapon.PULSE_PISTOL, ItemLootLists.randomLootForNPC()));
            rakraList.add(new NPC(75+healthModifier, 75+healthModifier, "Ra'kra Rookie", 40+expModifier, "Normal", Weapon.PULSE_PISTOL, ItemLootLists.randomLootForNPC()));
        }
        for (int i = 0; i < numberOfStrongs; i++) {
            rakraList.add(new NPC(100+healthModifier, 100+healthModifier, "Ra'kra Brute", 50+expModifier, "Strong", Weapon.SENTRY_CARBINE, ItemLootLists.randomLootForNPC()));
            rakraList.add(new NPC(125+healthModifier, 125+healthModifier, "Ra'kra Officer", 75+expModifier, "Strong", Weapon.PULSE_RIFLE, ItemLootLists.randomLootForNPC()));
        }

   }

   private static void initBanditList(int playerLevel){

        NPC bandit = new NPC(50, 50, "Bandit", 45, "Normal", Weapon.LASER_PISTOL, ItemLootLists.randomLootForNPC());
        NPC bandit2 = new NPC(75, 75, "Bandit", 50, "Normal", Weapon.STINGER_PISTOL, ItemLootLists.randomLootForNPC());
        NPC banditLeader = new NPC(100, 100, "Bandit Leader", 75, "Strong", Weapon.SENTRY_CARBINE, ItemLootLists.randomLootForNPC());
        banditList.addAll(Arrays.asList(bandit, bandit, bandit, bandit2, banditLeader));
   }
  





}
