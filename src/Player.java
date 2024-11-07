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

    public void addItem(Items itemToAdd) {
        for (Items items : Inventory) {
            if (items.getItem().equals(itemToAdd.getItem())) {
                items.setQuantity(itemToAdd.getQuantity() + items.getQuantity());
                return;
            }
        }
        Inventory.add(itemToAdd);
        System.out.println(itemToAdd.getItem() + " added to your inventory");

    }

    private void ShowInventory(Scanner action) {
        if (Inventory.isEmpty()) {
            System.out.println("Inventory is empty");
            return;
        } else {

            String userAction;
            do {
                int i = 1;
                System.out.println("Inventory: ");
                for (Items items : Inventory) {
                    System.out.println(i + ". " + items);
                    i++;
                }

                System.out.println("\nA) Use or Equip item\nB) Go back");
                userAction = Validation.UserInput(action);
                if (userAction.equals("A")) {
                    EquipItem(action);
                }
            } while (!userAction.equals("B"));

        }
    }

    private void EquipItem(Scanner action) {
        int choice;

        while (true) {
            System.out.println("Wich item?");
            choice = Validation.UserINput(action);
            if (choice >= 0 && choice <= Inventory.size()) {

                Items selectedItem = Inventory.get(choice - 1);

                if (selectedItem instanceof Consumables) {
                    Consumables useItem = (Consumables) selectedItem;
                    System.out.println("Used: " + useItem.getItem());
                    RestoreHealth(useItem);
                    useItem.setQuantity(selectedItem.getQuantity() - 1);
                    if (useItem.getQuantity() <= 0) {
                        Inventory.remove(choice - 1);
                    }
                    break;
                } else if (selectedItem instanceof Weapon) {
                    Weapon equipWeapon = (Weapon) selectedItem;
                    setEquipped(equipWeapon);
                    System.out.println("Equipped: " + equipWeapon.getItem());
                    break;
                }
            } else {
                System.out.println("I dont have that item.");
            }
        }
    }

    private void ShowSkills(Scanner action) {
        System.out.println("You have: ");
        for (Skills skill : PlayerSkills) {
            System.out.println(skill);
        }
        System.out.println();
    }

    public void Character(Scanner action) {
        String userAction;
        do {

            CharacterInfo();
            System.out.println("A) Show Skills\nB) Level Up\nC) Show Inventory\nD) Back");

            userAction = Validation.UserInput(action);
            switch (userAction) {
                case "A":
                    ShowSkills(action);
                    break;
                case "B":
                    if (experience >= expNeeded) {
                        LevelUp(action);
                        break;
                    } else {
                        System.out.println("Not enough of experience!");
                        break;
                    }
                case "C":
                    ShowInventory(action);
                    break;
                default:
                    break;
            }
        } while (!userAction.equals("D"));

    }

    public void takeDamage(int damage) {
        health -= damage;

        if (health <= 0) {
            setAlive(false);
        }
    }

    private void RestoreHealth(Consumables item) {
        int itemHeal = item.getHealPoints();

        if (PlayerSkills.contains(Skills.Medicine)) {
            itemHeal += 5;
        }

        health += itemHeal;
        System.out.println(item.getItem() + " healed you " + itemHeal + " hitpoints!");

        if (health > maxHealth) {
            health = maxHealth;
        }
    }

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

    private void LevelUp(Scanner action) {
        setLevel(getLevel() + 1);
        int remainingExp = experience - expNeeded;
        setExperience(remainingExp);
        setMaxHealth(getMaxHealth() + 10);
        setHealth(getMaxHealth());
        setExpNeeded(getExpNeeded() + 250);

        int userInput;
        boolean skillChosen = false;
        do {
            int i = 1;
            System.out.println("Choose a skill: ");
            for (Skills skill : Skills.SkillList) {
                if (!PlayerSkills.contains(skill)) {
                    System.out.printf("%d) %s \n", i, skill);
                    i++;
                }
            }
            userInput = Validation.UserINput(action);

            if (userInput > 0 && userInput <= Skills.SkillList.size()) {
                Skills chosenSkill = Skills.SkillList.get(userInput - 1);
                PlayerSkills.add(chosenSkill);
                chosenSkill.setPlayerHas(true);
                Skills.SkillList.remove(userInput - 1);
                skillChosen = true;
            } else {
                System.out.println("No way I can learn that!");
            }

        } while (!skillChosen);

    }

}
