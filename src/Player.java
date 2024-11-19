import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

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
    private VerticalListView levelUpMenu;
    private VerticalContainer levelUpContainer;

    /**
     * @description Constructor of Player Object
     * @param name name for player
     * @param health current health for player
     * @param maxHealth maximunm health for player
     * @param inventory inventory for player
     * @param playerSkills list of skills player has acquired
     * @param equipped weapon object player equips
     * @param level current level of player
     * @param experience current experience points of player
     * @param expNeeded experience needed for next level
     * @param alive is player alive
     * @param flags list of progress flags player has acquired
     */
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

    /**@return name of player as String */
    public String getName() {
        return name;
    }
    /**
     * @description set name for player
     * @param name string to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**@return maximum health of player as Integer*/
    public int getMaxHealth() {
        return maxHealth;
    }
    /**
     * @description set maximum health for player
     * @param maxHealth value to set
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    /**@return current health of player as integer*/
    public int getHealth() {
        return health;
    }
    /**
     * @description set current health for player
     * @param health value to set
     */
    public void setHealth(int health) {
        this.health = health;
    }
    /**@return inventory of player as ArrayList typed to Items*/
    public ArrayList<Items> getInventory() {
        return Inventory;
    }
    /**
     * @description set inventory for player
     * @param inventory ArrayList typed to Items
     */
    public void setInventory(ArrayList<Items> inventory) {
        Inventory = inventory;
    }
    /**@return acquired skills of player as ArrayList typed to Skills */
    public ArrayList<Skills> getPlayerSkills() {
        return PlayerSkills;
    }
    /**
     * @description set list of acquired skills for player
     * @param playerSkills ArrayList typed to Skills
     */
    public void setPlayerSkills(ArrayList<Skills> playerSkills) {
        PlayerSkills = playerSkills;
    }
    /**@return acquired progress flags of player as ArrayList typed to ProgressFlags */
    public ArrayList<ProgressFlags> getProgressFlags() {
        return ProgressFlags;
    }
    /**
     * @description set list of acquired progression flags for player
     * @param progressFlags ArrayList typed to ProgressFlags
     */
    public void setProgressFlags(ArrayList<ProgressFlags> progressFlags) {
        ProgressFlags = progressFlags;
    }
    /**@return equipped weapon object of player */
    public Weapon getEquipped() {
        return equipped;
    }
    /**
     * @description set Weapon object for player to equip
     * @param equipped Weapon Object to equip
     */
    public void setEquipped(Weapon equipped) {
        this.equipped = equipped;
    }
    /**@return current level of player as Integer */
    public int getLevel() {
        return level;
    }
    /**
     * @description set current level for player
     * @param level value to set
     */
    public void setLevel(int level) {
        this.level = level;
        characterStatusUpdate();
    }
    /**@return current experience points of player as Integer */
    public int getExperience() {
        return experience;
    }
    /**
     * @description sets current experience for player
     * @param experience value to set
     * @additional calls characterStatusUpdate() 
     */
    public void setExperience(int experience) {
        this.experience = experience;
        characterStatusUpdate();
    }
    /**@return needed experience points of player as Integer*/
    public int getExpNeeded() {
        return expNeeded;
    }
    /**
     * @description set experience needed for level for player
     * @param expNeeded value to set
     */
    public void setExpNeeded(int expNeeded) {
        this.expNeeded = expNeeded;
    }
    /**@return if player is alive, true or false */
    public boolean isAlive() {
        return alive;
    }
    /**
     * @description set player alive status
     * @param alive true or false
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    /**@return status container of player */
    public VerticalStatus getStatusContainer() {
        return statusContainer;
    }
    /**
     * @description set status container for player
     * @param statusContainer container object to set
     */
    public void setStatusContainer(VerticalStatus statusContainer) {
        this.statusContainer = statusContainer;
    }
    /**@return vertical container containing character info of player */
     public VerticalContainer getCharacterInfo() {
        return characterInfo;
    }
    /**
     * @description set character info container for player
     * @param characterInfo container object to set
     */
    public void setCharacterInfo(VerticalContainer characterInfo) {
        this.characterInfo = characterInfo;
    }


    /**
     * @description create a Vertical Container containing all the info of character,
     * skill menu, level menu, inventory menu
     * @return Character Info Container object
     * @WorkInProgress
     */
    public VerticalContainer Character() {   

        //Create container for character info
        VerticalContainer characterContainer = new VerticalContainer(20, "Character");
        characterContainer.setAlignment(Pos.CENTER);

        //Create container for skills menu
        VerticalContainer skillsContainer = new VerticalContainer(10, "Skills");
        skillsContainer.setAlignment(Pos.CENTER);

        //Create container for level menu
        this.levelUpContainer = new VerticalContainer(10, "Level");
        levelUpContainer.setAlignment(Pos.CENTER);

        //Create container for inventory menu
        VerticalContainer inventoryContainer = new VerticalContainer(10, "Inventory");
        inventoryContainer.setAlignment(Pos.CENTER);

        //append containers
        characterContainer.getChildren().addAll(skillsContainer, levelUpContainer, inventoryContainer);

        //Create skills menu
        VerticalListView playerSkillsMenu = new VerticalListView(10, "");

        skillsContainer.setOnMouseClicked(e -> {
            if (!skillsContainer.getChildren().contains(playerSkillsMenu)) {
                skillsContainer.getChildren().add(playerSkillsMenu);
                playerSkillsMenu.addSkillToList(PlayerSkills);
            }
            else{
                skillsContainer.getChildren().remove(playerSkillsMenu);
            }
        });

        levelUpContainer.setOnMouseClicked(e -> {
            if (!levelUpContainer.getChildren().contains(levelUpMenu)) {
                this.levelUpMenu = levelUp();
                levelUpContainer.getChildren().add(levelUpMenu);
            }
            else{
                levelUpContainer.getChildren().remove(levelUpMenu);
            }
        });

        
        //MAKE MOUSECLICKED ACTION HERE

        return characterContainer;
    }

    /**@description Toggles character info and status containers in root containers */
    public void toggleCharacterInfoStatus(){
        
        //If container doesnt contain info and status containers, they will be added, otherwise removed
        if (!Utility.leftContainer.getChildren().contains(this.characterInfo) && !Utility.rightContainer.getChildren().contains(this.statusContainer)) {
            Utility.leftContainer.getChildren().add(this.characterInfo);
            Utility.rightContainer.getChildren().add(this.statusContainer);
        }
        else{
            Utility.leftContainer.getChildren().remove(this.characterInfo);
            Utility.rightContainer.getChildren().remove(this.statusContainer);
        }
    }

    /**@description updates character status container with player properties*/
    public void characterStatusUpdate() {
        String isEquipped = "";

        if (equipped == null) {
            isEquipped = "None";
        } else {
            isEquipped = equipped.getItem();
        }

        //Create strings of player properties
        String levelXp = "Level: " + level + " | XP: " + experience + "/" + expNeeded;
        String healthStatus = "Health: " + health + "/" + maxHealth;
        String equipped = "Equipped: " + isEquipped;
        
        //update containers with new details
        this.statusContainer.updatePlayerStatus(levelXp, healthStatus, equipped);
    }
    
    /**
     * @description Reduce taken damage from player health
     * @param damage value to reduce
     * @additional calls characterStatusUpdate()
     */
    public void takeDamage(int damage){
        health -= damage;

        if (health <= 0) {
            setAlive(false);
        }
        //update player status container
        characterStatusUpdate();
    }

   /**
    * @description Adds items to player inventory
    * @param itemToAdd Item object
    * @additional calls actionExperience()
    * @NeedToBeReworked
    */
    public void addItemToInventory(Items itemToAdd){
        
        //Loop items in inventory
        for (Items items : Inventory) {
            //If item in inventory equals item to add
            if (items.getItem().equals(itemToAdd.getItem())) {

                //THIS NEEDS RECHECKING
                items.setQuantity(itemToAdd.getQuantity() + 1);
                String itemAdded = itemToAdd.getItem() + " added to your inventory\n";

                //Get experience
                actionExperience(Utility.LootItemEXP);
                Utility.Print(itemAdded, Utility.ActionSpeed);
                return;
            }
        }
        //Add item to invetory
        Inventory.add(itemToAdd);
        String itemAdded = itemToAdd.getItem() + " added to your inventory\n";
        Utility.Print(itemAdded, Utility.ActionSpeed);
        
        //Get experience
        actionExperience(Utility.LootItemEXP);
    }

    /**
     * @description Handles players experience gain
     * @param experienceGained value of experience to gain
     */
    public void actionExperience(int experienceGained){

        Utility.Print("You gained " + experienceGained + " experience.\n", Utility.ActionSpeed);
        setExperience(experience + experienceGained);
    }   


    /**
     * @description Adds progress flags to players ArrayList typed to progressFlags
     * @param flag object progressFlag
     */
    public void addProgressFlag(ProgressFlags flag){
        ProgressFlags.add(flag);
    }


    /**
     * @description creates list view container for level up menu
     * @return ListView levelUpMenu
     */
    public VerticalListView levelUp(){
        
        VerticalListView temporary;
        //If player has required amount of experience
        if (experience >= expNeeded) {
            
            //Create list view container
            temporary = new VerticalListView(10, "Select a new skill");

            //Loop skills in initialized arraylist
            for (Skills skill : Skills.SkillList) {

                //add skills to list view
                temporary.getSkillsList().getItems().add(skill);
            }

            Button button = new Button("Level Up!");
            temporary.getChildren().add(button);

            button.setOnAction(e -> {
                //When button is clicked, store selected skill
                Skills selectedSkill = temporary.getSkillsList().getSelectionModel().getSelectedItem();
                
                //If selection is not null
                if (selectedSkill != null) {

                    //Add skill to player skill list
                    PlayerSkills.add(selectedSkill); 

                    //Remove from initialized skill list
                    Skills.SkillList.remove(selectedSkill);
                    
                    setLevel(getLevel() + 1); //Gets current level and adds 1, sets to player level property
                    int remainingExp = experience - expNeeded; //checks how much exp was leftover
                    setExperience(remainingExp); //Set leftover as current experience amount
                    setMaxHealth(getMaxHealth() + 10); //Adds 10 points to max health 
                    setHealth(getMaxHealth()); //Restores character's max health
                    setExpNeeded(getExpNeeded() + 250); //Increases required experience for next level
                    this.levelUpContainer.getChildren().remove(this.levelUpMenu); //Remove level up menu from container
                    characterStatusUpdate(); //Update character status container
                }
                else{
                    temporary.setVerticalTitle("Please select a skill!");
                }
            });
        }
        else{
            temporary = new VerticalListView(10, "Not enough experience for level up!");
        }
        return temporary;
    }
}
