import java.util.ArrayList;

public class Player {
    
    private String name;
    private int health;
    private ArrayList<Items> Inventory;
    private ArrayList<Skills> PlayerSkills;
    private int skillpoint;
    private Items equipped;
    private int Level;
    private int experience;
    private int expNeeded;
    
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
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
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

    public Player(String name, int health, ArrayList<Items> inventory, ArrayList<Skills> playerSkills, int skillpoint,
                Items equipped, int level, int experience, int expNeeded) {
        
        this.name = name;
        this.health = health;
        Inventory = inventory;
        PlayerSkills = playerSkills;
        this.skillpoint = skillpoint;
        this.equipped = equipped;
        Level = level;
        this.experience = experience;
        this.expNeeded = expNeeded;
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", health=" + health + ", Inventory=" + Inventory + ", PlayerSkills="
                + PlayerSkills + ", skillpoint=" + skillpoint + ", equipped=" + equipped + ", Level=" + Level
                + ", experience=" + experience + ", expNeeded=" + expNeeded + "]";
    }

    public void addItem(Items itemToAdd){
        for (Items items : Inventory) {
            if (items.getItem().equals(itemToAdd.getItem())) {
                items.setQuantity(itemToAdd.getQuantity() + items.getQuantity());
                return;
            }
        }
       Inventory.add(itemToAdd);
    }
}


