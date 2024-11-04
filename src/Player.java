import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    
    private String name;
    private int maxHealth;
    private int health;
    private ArrayList<Items> Inventory;
    private ArrayList<Skills> PlayerSkills;
    private int skillpoint;
    private Items equipped;
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

    public int getSkillpoint() {
        return skillpoint;
    }

    public void setSkillpoint(int skillpoint) {
        this.skillpoint = skillpoint;
    }

    public Items getEquipped() {
        return equipped;
    }

    public void setEquipped(Items equipped) {
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

    public Player(String name, int health, int maxHealth, ArrayList<Items> inventory, ArrayList<Skills> playerSkills, int skillpoint,
                Items equipped, int level, int experience, int expNeeded, boolean alive) {
        
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        Inventory = inventory;
        PlayerSkills = playerSkills;
        this.skillpoint = skillpoint;
        this.equipped = equipped;
        this.level = level;
        this.experience = experience;
        this.expNeeded = expNeeded;
        this.alive = alive;
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", maxHealth=" + maxHealth + ", health=" + health + ", Inventory=" + Inventory
                + ", PlayerSkills=" + PlayerSkills + ", skillpoint=" + skillpoint + ", equipped=" + equipped
                + ", level=" + level + ", experience=" + experience + ", expNeeded=" + expNeeded + ", status=" + alive
                + "]";
    }

    public void addItem(Items itemToAdd){
        for (Items items : Inventory) {
            if (items.getItem().equals(itemToAdd.getItem())) {
                items.setQuantity(itemToAdd.getQuantity() + items.getQuantity());
                return;
            }
        }
       Inventory.add(itemToAdd);
       System.out.println(itemToAdd.getItem()+ " Added to your inventory");

    }

    private void ShowInventory(){
        int i = 1;
        for(Items items : Inventory){
            System.out.println(i + ". " + items);
            i++;
        }
    }

    private void ShowSkills(){
        for(Skills skill : PlayerSkills){
            System.out.println(skill);
        }
    }

    public void Character(Scanner action){
        System.out.println("A) Show Skills\nB) Level Up\nC) Show Inventory\nD) Back");
        String userAction;
        do {
            userAction = Validation.UserInput(action);
            switch (userAction) {
                case "A":
                    ShowSkills();
                    break;
                case "B":
                    break;
                case "C":
                    ShowInventory();
                    break;
                default:
                    break;
            }
        } while (!userAction.equals("D"));

    }

    public void takeDamage(int damage){
        health -= damage;
    }
}


