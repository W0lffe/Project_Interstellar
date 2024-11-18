import java.util.ArrayList;

public class NPC {
    private int maxHealth;
    private int health;
    private String name;
    private int experience;
    private boolean alive;
    private Weapon equipped;
    private ArrayList<Items> inventory;
    private VerticalStatus statusContainer;

    public NPC(int maxHealth, int health, String name, int experience, boolean alive, Weapon equipped, ArrayList<Items> inventory) {
        this.maxHealth = maxHealth;
        this.health = health;
        this.name = name;
        this.experience = experience;
        this.alive = alive;
        this.equipped = equipped;
        this.inventory = inventory;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Weapon getEquipped() {
        return equipped;
    }

    public void setEquipped(Weapon equipped) {
        this.equipped = equipped;
    }

    public ArrayList<Items> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Items> inventory) {
        this.inventory = inventory;
    }

    public VerticalStatus getStatusContainer() {
        return statusContainer;
    }

    public void setStatusContainer(VerticalStatus statusContainer) {
        this.statusContainer = statusContainer;
    }

    //NPC Methods

    public void takeDamage(int damage){
        health -= damage;

        if (health <= 0) {
            setAlive(false);
        }

        enemyStatusUpdate();
    }

    public void enemyStatusUpdate(){
        String enemyStatus = name + " | Health: " + health + "/"+ maxHealth;
        statusContainer.updateEnemyStatus(enemyStatus);        
    }
}
