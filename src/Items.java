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

