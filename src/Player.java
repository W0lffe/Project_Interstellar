import java.util.ArrayList;
import javafx.scene.control.Button;

public class Player {
    
    private String name;
    private int maxHealth;
    private int health;
    private ArrayList<Items> playerInventory;
    private ArrayList<Skills> playerAcquiredSkills;
    private ArrayList<ProgressFlags> playerAcquiredProgressFlags;
    private Weapon equipped;
    private int level;
    private int experience;
    private int expNeeded;
    private boolean alive;
    
    //Containers related to player character info and status
    private VerticalStatus statusContainer;
    private VerticalContainer characterInfo;
    private VerticalContainer levelUpContainer;
    private VerticalSkillList levelUpMenu;
    private VerticalInventoryList playerInventoryMenu;

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
        this.playerInventory = inventory;
        this.playerAcquiredSkills = playerSkills;
        this.equipped = equipped;
        this.level = level;
        this.experience = experience;
        this.expNeeded = expNeeded;
        this.alive = alive;
        this.playerAcquiredProgressFlags = flags;
    }


    /***************************************PLAYER OBJECT GETTER & SETTERS**********************************************************/

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
        characterStatusUpdate();
    }

    /**@return inventory of player as ArrayList typed to Items*/
    public ArrayList<Items> getPlayerInventory() {
        return playerInventory;
    }

    /**
     * @description set inventory for player
     * @param inventory ArrayList typed to Items
     */
    public void setPlayerInventory(ArrayList<Items> inventory) {
        playerInventory = inventory;
    }

    /**@return acquired skills of player as ArrayList typed to Skills */
    public ArrayList<Skills> getPlayerAcquiredSkills() {
        return playerAcquiredSkills;
    }

    /**
     * @description set list of acquired skills for player
     * @param playerSkills ArrayList typed to Skills
     */
    public void setPlayerAcquiredSkills(ArrayList<Skills> playerSkills) {
        playerAcquiredSkills = playerSkills;
    }

    /**@return acquired progress flags of player as ArrayList typed to ProgressFlags */
    public ArrayList<ProgressFlags> getPlayerAcquiredProgressFlags() {
        return playerAcquiredProgressFlags;
    }

    /**
     * @description set list of acquired progression flags for player
     * @param progressFlags ArrayList typed to ProgressFlags
     */
    public void setPlayerAcquiredProgressFlags(ArrayList<ProgressFlags> progressFlags) {
        playerAcquiredProgressFlags = progressFlags;
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


/***************************************PLAYER RELATED CONTAINER GETTER & SETTERS**********************************************************/
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
     * @description set level up container for player
     * @param characterInfo container object to set
     */
    public void setLevelUpContainer(VerticalContainer levelUpContainer) {
        this.levelUpContainer = levelUpContainer;
    }
    /**@return level up menu of player */
    public VerticalSkillList getLevelUpMenu() {
        return levelUpMenu;
    }

     /**
     * @description set level up menu for player
     * @param characterInfo container object to set
     */
    public void setLevelUpMenu(VerticalSkillList levelUpMenu) {
        this.levelUpMenu = levelUpMenu;
    }

    /**@return player inventory menu  */
    public VerticalInventoryList getPlayerInventoryMenu() {
        return playerInventoryMenu;
    }

     /**
     * @description set player inventory menu for player
     * @param characterInfo container object to set
     */
    public void setPlayerInventoryMenu(VerticalInventoryList playerInventoryMenu) {
        this.playerInventoryMenu = playerInventoryMenu;
    }


/***************************************PLAYER RELATED METHODS**********************************************************/


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
     * @description Loops through items in itemlist, adds to inventory
     * @param itemList ArrayList typed to Items, itemlist
     * @param foundWhere Where player found items
     */
   public void lootItems(ArrayList<Items> itemList, String foundWhere){

        String itemsFound = "You loot the " + foundWhere + " and find: \n";

        for (Items item : itemList) {
            itemsFound += item.toString() + "\n";
        }
        Utility.Print(itemsFound, 0);

        for (Items item : itemList) {
            addItemToInventory(item);
        }
    } 


   /**
    * @description Adds items to player inventory
    * @param itemToAdd Item object
    * @additional calls actionExperience()
    * @NeedToBeReworked
    */
    public void addItemToInventory(Items itemToAdd){
        
        //Loop items in inventory
        for (Items items : playerInventory) {
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
        playerInventory.add(itemToAdd);
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
        playerAcquiredProgressFlags.add(flag);
    }


    /**
     * @description Use or equip item to player
     * if item is Weapon class, it will be equipped, 
     * if item is Consumables class it will be used
     * (restores health)
     * @param item Item object
     */
    public void useOrEquipItem(Items item){

        //If item is weapon
        if (item instanceof Weapon) {
            Weapon itemToEquip = (Weapon)item;

            //Return if item is already equipped
            if (equipped.equals(itemToEquip)) {
                return;
            }

            //Set item as equipped weapon
            setEquipped(itemToEquip);
            String equipped = "Equipped weapon: " + itemToEquip.getItem() + "\n";
            Utility.Print(equipped, Utility.ActionSpeed);
        }
        else {
            Consumables itemToUse = (Consumables)item;
            
            //Decreases 1 from selected item quantity
            itemToUse.setQuantity(itemToUse.getQuantity()-1);
            
            //Item quantity goes to 0, remove from player inventory
            if (itemToUse.getQuantity() < 1) {
                playerInventory.remove(itemToUse);
            }

            //Restore health = current health + selected item heal points
            setHealth(health + itemToUse.getHealPoints());
            String healed = itemToUse.getItem() + " healed you " + itemToUse.getHealPoints() + " points!\n";
            Utility.Print(healed, Utility.ActionSpeed);

            //If health is over maximum, set current to maximum
            if (health > maxHealth) {
                setHealth(maxHealth);
            }
        }

        //update character status container
        characterStatusUpdate();
    }


    /**
     * @description creates list view container for level up menu
     * @return ListView levelUpMenu
     */
    public VerticalSkillList createLevelUpMenu(){
        
        VerticalSkillList temporary;
        //If player has required amount of experience
        if (experience >= expNeeded) {
            
            //Create list view container
            temporary = new VerticalSkillList(10, "Select a new skill");

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
                    playerAcquiredSkills.add(selectedSkill); 

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
            temporary = new VerticalSkillList(10, "Not enough experience for level up!");
        }
        return temporary;
    }

    /**
     * @description create player inventory listview container
     * @return listview container
     */
    public VerticalInventoryList createPlayerInventory(){

        //Init temporary viewlist container
        VerticalInventoryList temporary;

        //if player inventory is not empty
        if (!playerInventory.isEmpty()) {
            
            temporary = new VerticalInventoryList(10, "");

            //Create button to list and append
            Button button = new Button("Use Item");
            temporary.getChildren().addAll(button);

            //Loop items in player invetory, add to list
            for (Items item : playerInventory) {
                temporary.getInventoryList().getItems().add(item);
            }

            //If user clicks item in list
            temporary.getInventoryList().setOnMouseClicked(e -> {

                //Get clicked item
                Items selectedItem = temporary.getInventoryList().getSelectionModel().getSelectedItem();

                if (selectedItem != null) {
                    
                    //Sets button text value based on class of item
                    if (selectedItem instanceof Weapon) {
                        button.setText("Equip Weapon");
                    }
                    else {
                        button.setText("Use Item");
                    }
                }
            });
            
            button.setOnAction(e -> {

                //Select item with button click
                Items selectedItem = temporary.getInventoryList().getSelectionModel().getSelectedItem();

                //If selected item is not null
                if (selectedItem != null) {

                    //Call function with item parameter
                    useOrEquipItem(selectedItem);
                    
                    //Clear inventory list
                    temporary.getInventoryList().getItems().clear();

                    //Loop items in inventory again to have current status
                    for (Items item : playerInventory) {
                        temporary.getInventoryList().getItems().add(item);
                    }
                }
                else{
                    button.setText("Select item first!");
                }
            });

            //Return filled list
            return temporary;
        }
        else{
            //If player inventory is empty, list is empty
           return temporary = new VerticalInventoryList(10, "Inventory is empty");
        }
    }
}
