public class Items {

    private String item;
    private String type;
    private String description;
    private int quantity;

    public Items(String item, String type, String description, int quantity) {
        this.item = item;
        this.type = type;
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

}

class Weapon extends Items {

    private int minDamage;
    private int maxDamage;

    public Weapon(String item, String type, int minDamage, int maxDamage, String description ,int quantity) {
        super(item, type, description, quantity);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
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

    

    @Override
    public String toString() {
        return "[" + getItem() + ", " + getType() + "| Damage: " + minDamage + "-" + maxDamage + " | " + getDescription() + "]";
    }



    public static Weapon PULSE_PISTOL = new Weapon("Pulse Pistol", "Energy Weapon", 8, 12, "A compact energy-based sidearm", 1);
    public static Weapon PULSE_RIFLE = new Weapon("Pulse Rifle", "Energy Weapon" ,18, 22, "Firepower with versatility", 1);
    public static Weapon LASER_RIFLE  = new Weapon("Laser Rifle", "Energy Weapon" ,15, 20, "Lightweight rifle with precision", 1);
    public static Weapon LASER_PISTOL  = new Weapon("Laser Pistol", "Energy Weapon" , 9, 13, "Lightweight pistol for sharpshooters", 1);
    public static Weapon QUANTUM_REPEATER  = new Weapon("Quantum Repeater", "Energy Weapon" , 27, 35, "Unleash a hail of destabilized energy", 1);
}

class Consumables extends Items {

    private int healPoints;

    public Consumables(String item, String type, int healPoints, String description, int quantity) {
        super(item, type, description, quantity);
        this.healPoints = healPoints;
    }

    public int getHealPoints() {
        return healPoints;
    }

    public void setHealPoints(int healPoints) {
        this.healPoints = healPoints;
    }

    @Override
    public String toString() {
        return "[" + getItem() + ", " + getType() + "| Heals: " + healPoints + " | " + getDescription() + " | " + "Qty: " + getQuantity() + "]";
    }


    public static Consumables SPACE_STEAK = new Consumables("Space Steak", "Food", 15, "All nutrients preserved!", 1);
    public static Consumables ENERGY_DRINK = new Consumables("Energy Drink", "Drink", 8,  "Lovely refreshing drink", 1);
    public static Consumables SPACE_SANDWICH = new Consumables("Sandwich", "Food", 12, "Vacuum sealed sandwich", 1);
    public static Consumables SPACE_SODA = new Consumables("Soda", "Drink", 7,"Diet soda", 1);

    public static Consumables BASIC_MEDKIT = new Consumables("Basic Medkit", "Medkit", 50, "Medkit for minor injuries", 1);

}
