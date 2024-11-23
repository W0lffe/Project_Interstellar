import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Items {

    private String item;
    private String type;
    private String description;
    private int quantity;

    /**
     * @description Constructor for Items object
     * @param item name of item
     * @param type type of item
     * @param description brief description for item
     * @param quantity how many
     * @info THIS IS PARENT FOR WEAPON AND CONSUMABLES
     */
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

/**@description Contains item list creation and setting random inventory list to NPC */
class ItemLootLists{

    private static ArrayList<ArrayList<Items>> listOfItemLists = new ArrayList<>();
    private static ArrayList<ArrayList<Items>> prologueItemLists = new ArrayList<>();

    
    /**@description Creates tiered itemlists and places to one big list */
    public static void initLootItemLists(){
        
        //Retrieve consumables from Map
        ArrayList<Consumables> medical = Consumables.retrieveList("MedicalList");
        ArrayList<Consumables> food = Consumables.retrieveList("FoodList");

        //Init tiered lists
        ArrayList<Items> lowTier = new ArrayList<>();
        ArrayList<Items> medTier = new ArrayList<>();
        ArrayList<Items> highTier = new ArrayList<>();

        Random random = new Random();


        //Loop items from consumables to tiered lists using random
        for (int i = 0; i < 7; i++) {
            Items medicalItem = medical.get(random.nextInt(medical.size()));
            Items medicalItem2;
            Items foodItem = food.get(random.nextInt(food.size()));
            Items foodItem2;

            //Ensure items are not equal
            do {
                medicalItem2 = medical.get(random.nextInt(medical.size()));
                foodItem2 = food.get(random.nextInt(food.size()));
            } while (medicalItem2 == medicalItem && foodItem2 == foodItem);


            if (i < 2) {
                lowTier.add(medicalItem);
                lowTier.add(foodItem);
                lowTier.add(foodItem2);
            }
            else if(i > 2 && i < 5){
                medTier.add(medicalItem);
                medTier.add(medicalItem2);
                medTier.add(foodItem);
            }
            else{
                highTier.add(medicalItem);
                highTier.add(medicalItem2);
                highTier.add(foodItem);
                highTier.add(foodItem2);
            }
        }

        //Add all tiered lists to arraylist
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                listOfItemLists.add(lowTier); //50%
            }
            else if(i >= 5 && i < 8){
                listOfItemLists.add(medTier); //30%
            }
            else{
                listOfItemLists.add(highTier); //20%
            }
        }
        

        createPrologueItemLists();
    }

    /**
     * @description Shuffles collection of item lists
     * @return random item list from collection
     */
    public static ArrayList<Items> randomLootForNPC(){

        ArrayList<Items> itemListToReturn = new ArrayList<>();
        Collections.shuffle(listOfItemLists);
        itemListToReturn = listOfItemLists.getFirst();

        return itemListToReturn;

    }

    /**@description create predefined itemlists for prologue */
    private static void createPrologueItemLists(){

        ArrayList<Items> lockerItems = new ArrayList<>();
        lockerItems.addAll(Arrays.asList(Weapon.retrieveTieredWeapon("Low Tier"), Consumables.retrieveList("FoodList").get(3), Consumables.retrieveList("MedicalList").get(3),  
                                        Consumables.retrieveList("MedicalList").get(3)));
       
        ArrayList<Items> supplyContainer = new ArrayList<>();
        supplyContainer.addAll(Arrays.asList( Consumables.retrieveList("MedicalList").get(3),  Consumables.retrieveList("MedicalList").get(2)));

        prologueItemLists.addAll(Arrays.asList(lockerItems, supplyContainer));
    }

    /**@description fetches predefined arraylist to prologue
     * @param index value of index to get from arraylist
     * @return ArrayList typed to Items */
    public static ArrayList<Items> fetchPrologueItemList(int index){
        return prologueItemLists.get(index);
    }

}
