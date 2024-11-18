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
        return "[" + getItem() + " | " + getType() + " | Heals: " + healPoints + " | " + getDescription() + " | " + "Qty: " + getQuantity() + "]";
    }

    public String Found(){
        return "[" + getItem() + " | " + getType() + " | Heals: " + healPoints + " | " + getDescription() +"]";
    }

    //predefined consumables
    public static Consumables SPACE_STEAK = new Consumables("Space Steak", "Food", 15, "All nutrients preserved!", 1);
    public static Consumables ENERGY_DRINK = new Consumables("Energy Drink", "Drink", 8,  "Lovely refreshing drink", 1);
    public static Consumables SPACE_SANDWICH = new Consumables("Sandwich", "Food", 12, "Vacuum sealed sandwich", 1);
    public static Consumables SPACE_SODA = new Consumables("Soda", "Drink", 7,"Diet soda", 1);
    public static Consumables NUTRI_PASTE = new Consumables("Nutri-Paste", "Food", 10, "Concentrated paste full of essential vitamins and proteins", 1);
    public static Consumables ADRENAL_SHOT = new Consumables("Adrenal Shot", "Injection", 20, "Instant energy boost, enhances alertness temporarily", 1);
    public static Consumables HYDRATION_PACK = new Consumables("Hydration Pack", "Drink", 5, "Replenishes hydration, great for long treks", 1);
    public static Consumables RATIONS_BAR = new Consumables("Rations Bar", "Food", 12, "Compact bar with all essential nutrients for survival", 1);
    public static Consumables ADVANCED_MEDKIT = new Consumables("Advanced Medkit", "Medkit", 100, "Comprehensive medkit for more serious injuries", 1);
    public static Consumables BASIC_MEDKIT = new Consumables("Basic Medkit", "Medkit", 50, "Medkit for minor injuries", 1);

}
