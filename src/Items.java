import java.util.ArrayList;
import java.util.Collections;


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
class ItemLists{

    private static ArrayList<ArrayList<Items>> listOfItemLists = new ArrayList<>();
    private static ArrayList<ArrayList<Items>> prologueItemLists = new ArrayList<>();

    
    /**@description Creates tiered itemlists and places to one big list */
    public static void initItemLists(){
        
        ArrayList<Items> lowTierList = new ArrayList<>();
        ArrayList<Items> mediumTierList = new ArrayList<>();
        ArrayList<Items> highTierList = new ArrayList<>();

        lowTierList.add(Consumables.BANDAGE);
        lowTierList.add(Consumables.BANDAGE);
        lowTierList.add(Consumables.BASIC_MEDKIT);
        lowTierList.add(Consumables.HYDRATION_PACK);
        lowTierList.add(Consumables.RATIONS_BAR);

        mediumTierList.add(Consumables.BASIC_MEDKIT);
        mediumTierList.add(Consumables.BASIC_MEDKIT);
        mediumTierList.add(Consumables.ADRENAL_SHOT);
        mediumTierList.add(Consumables.SPACE_STEAK);

        highTierList.add(Consumables.ADVANCED_MEDKIT);
        highTierList.add(Consumables.ADVANCED_MEDKIT);
        highTierList.add(Consumables.ADRENAL_SHOT);

        for (int i = 0; i < 10; i++) {
            if (i < 6) {
                listOfItemLists.add(lowTierList);
            }
            else if(i > 6 && i < 9){
                listOfItemLists.add(mediumTierList);
            }
            else{
                listOfItemLists.add(highTierList);
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
        lockerItems.add(Weapon.LASER_PISTOL);
        lockerItems.add(Consumables.SPACE_SODA);
        lockerItems.add(Consumables.BASIC_MEDKIT);

        ArrayList<Items> supplyContainer = new ArrayList<>();
        supplyContainer.add(Consumables.BASIC_MEDKIT);
        supplyContainer.add(Consumables.BASIC_MEDKIT);

        prologueItemLists.add(lockerItems);
        prologueItemLists.add(supplyContainer);

    }

    public static ArrayList<Items> fetchPrologueItemList(int index){
        return prologueItemLists.get(index);
    }

}
