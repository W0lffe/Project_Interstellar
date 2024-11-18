import java.util.ArrayList;

import javafx.geometry.Pos;

public class Player {
    
    private String name;
    private int maxHealth;
    private int health;
    private ArrayList<Items> Inventory;
    private ArrayList<Skills> PlayerSkills;
    private ArrayList<ProgressFlags> ProgressFlags;
    private Weapon equipped;
    private int level;
    private int experience;
    private int expNeeded;
    private boolean alive;
    private VerticalStatus statusContainer;
    private VerticalContainer characterInfo;

    
    //CONSTRUCTOR FOR PLAYER OBJECT
    public Player(String name, int health, int maxHealth, ArrayList<Items> inventory, ArrayList<Skills> playerSkills,
            Weapon equipped, int level, int experience, int expNeeded, boolean alive, ArrayList<ProgressFlags> flags) {

        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.Inventory = inventory;
        this.PlayerSkills = playerSkills;
        this.equipped = equipped;
        this.level = level;
        this.experience = experience;
        this.expNeeded = expNeeded;
        this.alive = alive;
        this.ProgressFlags = flags;
    }

    //GETTERS AND SETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public ArrayList<Items> getInventory() {
        return Inventory;
    }
    public void setInventory(ArrayList<Items> inventory) {
        Inventory = inventory;
    }
    public ArrayList<Skills> getPlayerSkills() {
        return PlayerSkills;
    }
    public void setPlayerSkills(ArrayList<Skills> playerSkills) {
        PlayerSkills = playerSkills;
    }
    public ArrayList<ProgressFlags> getProgressFlags() {
        return ProgressFlags;
    }
    public void setProgressFlags(ArrayList<ProgressFlags> progressFlags) {
        ProgressFlags = progressFlags;
    }
    public Weapon getEquipped() {
        return equipped;
    }
    public void setEquipped(Weapon equipped) {
        this.equipped = equipped;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
        characterStatusUpdate();
    }
    public int getExpNeeded() {
        return expNeeded;
    }
    public void setExpNeeded(int expNeeded) {
        this.expNeeded = expNeeded;
    }
    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public VerticalStatus getStatusContainer() {
        return statusContainer;
    }
    public void setStatusContainer(VerticalStatus statusContainer) {
        this.statusContainer = statusContainer;
    }
     public VerticalContainer getCharacterInfo() {
        return characterInfo;
    }

    public void setCharacterInfo(VerticalContainer characterInfo) {
        this.characterInfo = characterInfo;
    }


    //PLAYER METHODS
    public VerticalContainer Character() {   

        VerticalContainer characterContainer = new VerticalContainer(20, "Character");
        characterContainer.setAlignment(Pos.CENTER);

        VerticalContainer skillsContainer = new VerticalContainer(10, "Skills");
        skillsContainer.setAlignment(Pos.CENTER);

        VerticalContainer levelUpContainer = new VerticalContainer(10, "Level");
        levelUpContainer.setAlignment(Pos.CENTER);

        VerticalContainer inventoryContainer = new VerticalContainer(10, "Inventory");
        inventoryContainer.setAlignment(Pos.CENTER);

        characterContainer.getChildren().addAll(skillsContainer, levelUpContainer, inventoryContainer);

        VerticalSkillsMenu playerSkillsMenu = new VerticalSkillsMenu(10, "Your acquired skills");
        playerSkillsMenu.addSkillToList(PlayerSkills);

        skillsContainer.setOnMouseClicked(e -> {
            if (!skillsContainer.getChildren().contains(playerSkillsMenu)) {
                skillsContainer.getChildren().add(playerSkillsMenu);
            }
            else{
                skillsContainer.getChildren().remove(playerSkillsMenu);
            }
        });

        
        //MAKE MOUSECLICKED ACTION HERE

        return characterContainer;
    }

    public void characterStatusUpdate() {
        String isEquipped = "";

        if (equipped == null) {
            isEquipped = "None";
        } else {
            isEquipped = equipped.getItem();
        }

        String levelXp = "Level: " + level + " | XP: " + experience + "/" + expNeeded;
        String healthStatus = "Health: " + health + "/" + maxHealth;
        String equipped = "Equipped: " + isEquipped;
       
        statusContainer.updatePlayerStatus(levelXp, healthStatus, equipped);
    }
    
    //Reduce taken damage from player health
    public void takeDamage(int damage){
        health -= damage;

        if (health <= 0) {
            setAlive(false);
        }

        characterStatusUpdate();
    }

    //Function adds items to player inventory with parameter object
    public void addItemToInventory(Items itemToAdd){
        
        for (Items items : Inventory) {
            if (items.getItem().equals(itemToAdd.getItem())) {
                items.setQuantity(itemToAdd.getQuantity() + 1);
                String itemAdded = itemToAdd.getItem() + " added to your inventory\n";
                actionExperience(Utility.LootItemEXP);
                Utility.Print(itemAdded, Utility.ActionSpeed);
                return;
            }
        }
        //Add item to invetory
        Inventory.add(itemToAdd);
        String itemAdded = itemToAdd.getItem() + " added to your inventory\n";
        Utility.Print(itemAdded, Utility.ActionSpeed);
        actionExperience(Utility.LootItemEXP);
    }

    public void actionExperience(int experienceGained){

        Utility.Print("You gained " + experienceGained + " experience.\n", Utility.ActionSpeed);
        setExperience(experience + experienceGained);
    }   

    public void addProgressFlag(ProgressFlags flag){
        ProgressFlags.add(flag);
    }
}
