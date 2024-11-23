import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Consumables extends Items {

    private int healPoints;
    private static Map<String, ArrayList<Consumables>> consumablesMap = new HashMap<>();

    /**
     * @description Constructor for Consumable object
     * @param item name for consumable
     * @param type type of consumable
     * @param healPoints healing points of consumable
     * @param description brief description of consumable
     * @param quantity how many
     */
    public Consumables(String item, String type, int healPoints, String description, int quantity) {
        super(item, type, description, quantity);
        this.healPoints = healPoints;
    }

    /** @return healing points of consumable as Integer*/
    public int getHealPoints() {
        return healPoints;
    }
    /**
     * @description sets healing points for consumable
     * @param healPoints value to be set
     */
    public void setHealPoints(int healPoints) {
        this.healPoints = healPoints;
    }

    @Override
    public String toString() {
        return "[" + getItem() + " | " + getType() + " | Heals: " + healPoints + " | " + getDescription() + " | " + "Qty: " + getQuantity() + "]";
    }

    /**@return String formated to found item, differs from "toString" method*/
    public String Found(){
        return "[" + getItem() + " | " + getType() + " | Heals: " + healPoints + " | " + getDescription() +"]";
    }

    /**@description initiates lists of consumables and puts to Map*/
    public static void initConsumablesList(){

        ArrayList<Consumables> foodItems = new ArrayList<>();
        ArrayList<Consumables> medicalItems = new ArrayList<>();

        foodItems.add(new Consumables("Space Steak", "Food", 15, "All nutrients preserved!", 1));
        foodItems.add(new Consumables("Energy Drink", "Drink", 8,  "Lovely refreshing drink", 1));
        foodItems.add(new Consumables("Sandwich", "Food", 12, "Vacuum sealed sandwich", 1));
        foodItems.add(new Consumables("Soda", "Drink", 7,"Diet soda", 1));
        foodItems.add(new Consumables("Nutri-Paste", "Food", 10, "Concentrated paste full of essential vitamins and proteins", 1));
        foodItems.add(new Consumables("Rations Bar", "Food", 12, "Compact bar with all essential nutrients for survival", 1));
        foodItems.add(new Consumables("Hydration Pack", "Drink", 5, "Replenishes hydration, great for long treks", 1));

        medicalItems.add(new Consumables("Bandage", "Bandage", 10, "Stops bleeding", 1));
        medicalItems.add(new Consumables("Adrenal Shot", "Injection", 25, "Instant energy boost, enhances alertness temporarily", 1));
        medicalItems.add(new Consumables("Advanced Medkit", "Medkit", 100, "Comprehensive medkit for more serious injuries", 1));
        medicalItems.add(new Consumables("Basic Medkit", "Medkit", 50, "Medkit for minor injuries", 1));

        consumablesMap.put("FoodList", foodItems);
        consumablesMap.put("MedicalList", medicalItems);
    }

    /**
     * @description retrieves consumable list from Map
     * @param listKey key of the list as String
     * @return ArrayList typed to Consumables
     */
    public static ArrayList<Consumables> retrieveList(String listKey){
        return consumablesMap.get(listKey);
    }

}
