import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private String name;
    private int maxHealth;
    private int health;
    private ArrayList<Items> Inventory;
    private ArrayList<Skills> PlayerSkills;
    private Weapon equipped;
    private int level;
    private int experience;
    private int expNeeded;
    private boolean alive;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean status) {
        this.alive = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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
    }

    public int getExpNeeded() {
        return expNeeded;
    }

    public void setExpNeeded(int expNeeded) {
        this.expNeeded = expNeeded;
    }

    public Player(String name, int health, int maxHealth, ArrayList<Items> inventory, ArrayList<Skills> playerSkills,
            Weapon equipped, int level, int experience, int expNeeded, boolean alive) {

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
    }

    //Function adds items to player inventory with parameter object
    public void addItem(Items itemToAdd) {
        //Loop through player inventory, check if has same items to increase quantity
        for (Items items : Inventory) {
            if (items.getItem().equals(itemToAdd.getItem())) {
                items.setQuantity(itemToAdd.getQuantity() + items.getQuantity());
                return;
            }
        }
        //Add item to invetory
        Inventory.add(itemToAdd);
        System.out.println(itemToAdd.getItem() + " added to your inventory");

    }

    //Function shows invetory
    private void ShowInventory(Scanner action) {
        //Return if inventory is empty
        if (Inventory.isEmpty()) {
            System.out.println("Inventory is empty");
            return;
        } else {

            String userAction;
            do {
                int i = 1;
                System.out.println("Inventory: ");
                //Loop through inventory item objects
                for (Items items : Inventory) {
                    System.out.println(i + ". " + items);
                    i++;
                }

                //Ask player if they want to equip item
                System.out.println("\nA) Use or Equip item\nB) Go back");
                userAction = Validation.UserInput(action);
                if (userAction.equals("A")) {
                    EquipItem(action); //Go to function where player equips/uses item
                }
            } while (!userAction.equals("B"));

        }
    }

    //Function equips item for player or uses item
    private void EquipItem(Scanner action) {
        int choice;

        while (true) {

            //Ask wich item to equip
            System.out.println("Wich item?");
            choice = Validation.UserINput(action);
            if (choice >= 0 && choice <= Inventory.size()) {

                Items selectedItem = Inventory.get(choice - 1);

                //If item is object class Consumables
                if (selectedItem instanceof Consumables) {
                    Consumables useItem = (Consumables) selectedItem; //Convert to Items to Consumables
                    System.out.println("Used: " + useItem.getItem());
                    RestoreHealth(useItem); //Send consumable to RestoreHealth function
                    useItem.setQuantity(selectedItem.getQuantity() - 1); //Reduce quantity
                    if (useItem.getQuantity() <= 0) {
                        Inventory.remove(choice - 1); //If quantity is zero, remove object from inventory
                    }
                    break;
                } else if (selectedItem instanceof Weapon) {
                    Weapon equipWeapon = (Weapon) selectedItem; //Convert Items to Weapon
                    setEquipped(equipWeapon); //set Weapon object equipped for player
                    System.out.println("Equipped: " + equipWeapon.getItem()); 
                    break;
                }
            } else {
                System.out.println("I dont have that item.");
            }
        }
    }

    //Print skills in playerSkills array
    private void ShowSkills(Scanner action) {
        System.out.println("You have: ");
        for (Skills skill : PlayerSkills) {
            System.out.println(skill);
        }
        System.out.println();
    }

    //Show character details 
    public void Character(Scanner action) {
        String userAction;
        do {

            CharacterInfo(); //Print info of player
            System.out.println("A) Show Skills\nB) Level Up\nC) Show Inventory\nD) Back");

            userAction = Validation.UserInput(action);
            switch (userAction) {
                case "A":
                    ShowSkills(action); //Open acquired skills menu
                    break;
                case "B":
                    if (experience >= expNeeded) {
                        LevelUp(action); //Level up character if experience requirements is met
                        break;
                    } else {
                        System.out.println("Not enough of experience!");
                        break;
                    }
                case "C":
                    ShowInventory(action); //Show player inventory
                    break;
                default:
                    break;
            }
        } while (!userAction.equals("D")); //Go back 

    }

    //Reduce taken damage from player health
    public void takeDamage(int damage) {
        health -= damage;

        //if player health is zero or less, player "dies"
        if (health <= 0) {
            setAlive(false);
        }
    }


    //Restore health with Consumables object
    private void RestoreHealth(Consumables item) {
        int itemHeal = item.getHealPoints(); //Get healing points, property of object

        if (PlayerSkills.contains(Skills.Medicine)) {
            itemHeal += 5; //If player has medicine skill, object heals 5 points more
        }

        health += itemHeal;
        System.out.println(item.getItem() + " healed you " + itemHeal + " hitpoints!");

        //if health goes over maxhealth, health is set to max
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    //Prints info of character
    private void CharacterInfo() {
        String isEquipped = "";
        String levelUp = "";

        if (experience >= expNeeded) {
            levelUp = "- Level Up Available";
        }

        if (equipped == null) {
            isEquipped = "None";
        } else {
            isEquipped = equipped.getItem();
        }
        System.out.printf("[%s  | Level: %d | XP: %d/%d %s]\n[Health: %d/%d | Equipped: %s]\n",
                name, level, experience, expNeeded, levelUp, health, maxHealth, isEquipped);

    }

    //Level up character
    private void LevelUp(Scanner action) {
        setLevel(getLevel() + 1); //Gets current level and adds 1, sets to player level property
        int remainingExp = experience - expNeeded; //checks how much exp was leftover
        setExperience(remainingExp); //Set leftover as current experience amount
        setMaxHealth(getMaxHealth() + 10); //Adds 10 points to max health 
        setHealth(getMaxHealth()); //Restores character's max health
        setExpNeeded(getExpNeeded() + 250); //Increases required experience for next level

        int userInput;
        boolean skillChosen = false;
        do {
            int i = 1;
            System.out.println("Choose a skill: ");
            //Loop through SkillList (initialized in Game.newGame)
            for (Skills skill : Skills.SkillList) {
                if (!PlayerSkills.contains(skill)) {
                    System.out.printf("%d) %s \n", i, skill);
                    i++;
                }
            }
            userInput = Validation.UserINput(action);

            //Check if user selection of skill is in range
            if (userInput > 0 && userInput <= Skills.SkillList.size()) {
                Skills chosenSkill = Skills.SkillList.get(userInput - 1); //get skill from list
                PlayerSkills.add(chosenSkill); //add to player acquired skills
                //chosenSkill.setPlayerHas(true); no use for this yet
                Skills.SkillList.remove(userInput - 1); //removes from SkillList so it wont show again when selecting skills
                skillChosen = true; //break loop
            } else {
                System.out.println("No way I can learn that!");
            }

        } while (!skillChosen);
    }

    public void LoreExperience(){
        setExperience(experience + Utility.LoreItemEXP);
    }

    public void LootExperience(){
        setExperience(experience + Utility.LootItemEXP);
    }

}
