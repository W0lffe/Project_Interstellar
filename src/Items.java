public class Items {
    
    private String item;
    private String type;
    private int heals;
    private int minDamage;
    private int maxDamage;
    private String description;
    private int quantity;

    public Items(String item, String type, int heals, int minDamage, int maxDamage, String description, int quantity) {
        this.item = item;
        this.type = type;
        this.heals = heals;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.description = description;
        this.quantity = quantity;
    }
    
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getHeals() {
        return heals;
    }
    public void setHeals(int heals) {
        this.heals = heals;
    }
    public int getMinDamage() {
        return minDamage;
    }
    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }
    public int getMaxDamage() {
        return maxDamage;
    }
    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static Items PULSE_PISTOL = new Items("Pulse Pistol", "Energy Weapon", 0, 8, 12, "A compact energy-based sidearm", 1);
    public static Items PULSE_RIFLE = new Items("Pulse Rifle", "Energy Weapon", 0,18, 22, "Firepower with versatility", 1);
    public static Items LASER_RIFLE  = new Items("Laser Rifle", "Energy Weapon", 0,15, 20, "Lightweight rifle with precision", 1);
    public static Items LASER_PISTOL  = new Items("Laser Pistol", "Energy Weapon", 0, 9, 13, "Lightweight pistol for sharpshooters", 1);
    public static Items QUANTUM_REPEATER  = new Items("Quantum Repeater", "Energy Weapon", 0, 27, 35, "Unleash a hail of destabilized energy", 1);

    public static Items SPACE_STEAK = new Items("Space Steak", "Consumable", 15, 0, 0, "All nutrients preserved!", 1);
    public static Items ENERGY_DRINK = new Items("Energy Drink", "Consumable", 8, 0, 0, "Lovely refreshing drink", 1);
    public static Items SPACE_SANDWICH = new Items("Sandwich", "Consumable", 12, 0, 0, "Vacuum sealed sandwich", 1);
    public static Items SPACE_SODA = new Items("Soda", "Consumable", 7, 0, 0, "Diet soda", 1);

    public static Items BASIC_MEDKIT = new Items("Basic Medkit", "Consumable", 50, 0, 0, "Medkit for minor injuries", 1);

    @Override
    public String toString() {
        return "Items [item=" + item + ", type=" + type + ", heals=" + heals + ", minDamage=" + minDamage
                + ", maxDamage=" + maxDamage + ", description=" + description + ", quantity=" + quantity + "]";
    }










}
