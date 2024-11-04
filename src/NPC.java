public class NPC {
    
    private int maxHealth;
    private int health;
    private String name;
    private int experience;
    private boolean alive;
    private Items equipped;
    
    public NPC(int maxHealth, int health, String name, int experience, boolean alive, Items equipped) {
        this.maxHealth = maxHealth;
        this.health = health;
        this.name = name;
        this.experience = experience;
        this.alive = alive;
        this.equipped = equipped;
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
    public Items getEquipped() {
        return equipped;
    }
    public void setEquipped(Items equipped) {
        this.equipped = equipped;
    }

    public void takeDamage(int damage){
        health -= damage;
    }



}
