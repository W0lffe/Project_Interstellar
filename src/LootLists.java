import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**@description Contains item list creation and appropriate methods*/
public class LootLists {
    
    private static Map<String, ArrayList<ArrayList<Items>>> lootListsMap = new HashMap<>();
    private static ArrayList<ArrayList<Items>> prologueItemLists = new ArrayList<>();
    
    /**@description Creates tiered itemlists and places to one big list */
    public static void initLootItemLists(){
        
        //Retrieve consumables from Map
        ArrayList<Consumables> medical = Consumables.retrieveList("MedicalList");
        ArrayList<Consumables> food = Consumables.retrieveList("FoodList");

        //Init tiered lists
        ArrayList<Items> npcLootLowTier = new ArrayList<>();
        ArrayList<Items> npcLootMedTier = new ArrayList<>();
        ArrayList<Items> npcLootHighTier = new ArrayList<>();

        ArrayList<Items> medicalLootLowTier = new ArrayList<>();
        ArrayList<Items> medicalLootMedTier = new ArrayList<>();
        ArrayList<Items> medicalLootHighTier = new ArrayList<>();

        //Nested ArrayLists for lists of loot
        ArrayList<ArrayList<Items>> listOfNPClootLists = new ArrayList<>();
        ArrayList<ArrayList<Items>> listOfMedicalItemLists = new ArrayList<>();

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

            if (i < 1) {
                medicalLootLowTier.add(medicalItem);
            }
            else if (i < 2) {
                npcLootLowTier.add(medicalItem);
                npcLootLowTier.add(foodItem);
                npcLootLowTier.add(foodItem2);
            }
            else if(i > 2 && i < 5){
                npcLootMedTier.add(medicalItem);
                npcLootMedTier.add(medicalItem2);
                npcLootMedTier.add(foodItem);
                
                medicalLootMedTier.add(medicalItem);
            }
            else{
                npcLootHighTier.add(medicalItem);
                npcLootHighTier.add(medicalItem2);
                npcLootHighTier.add(foodItem);
                npcLootHighTier.add(foodItem2);
                
                medicalLootHighTier.add(medicalItem);
                medicalLootHighTier.add(medicalItem2);

            }
        }

        //Add all tiered lists to arraylist
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                listOfNPClootLists.add(npcLootLowTier); //50%
                listOfMedicalItemLists.add(medicalLootLowTier);
            }
            else if(i >= 5 && i < 8){
                listOfNPClootLists.add(npcLootMedTier); //30%
                listOfMedicalItemLists.add(medicalLootMedTier);
            }
            else{
                listOfNPClootLists.add(npcLootHighTier); //20%
                listOfMedicalItemLists.add(medicalLootHighTier);
            }
        }
        
        lootListsMap.put("NPC", listOfNPClootLists);
        lootListsMap.put("Meds", listOfMedicalItemLists);

        createPrologueItemLists();
    }

    /**
     * @description Gets itemlist from Map, shuffles and returns random lootlist
     * @param type type of lootlist to get from Map
     * @return random typed itemlist from Map
     */
    public static ArrayList<Items> retrieveRandomLootList(String type){
        ArrayList<ArrayList<Items>> lootLists = lootListsMap.get(type);

        ArrayList<Items> itemListToReturn = new ArrayList<>();
        Collections.shuffle(lootLists);
        itemListToReturn = lootLists.getFirst();

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
