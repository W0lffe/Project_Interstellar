import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main{

    public static void main(String[] args) {

            ArrayList<Items> Inventory = new ArrayList<>();
            Inventory.add(Items.BASIC_MEDKIT);
            
            ArrayList<Skills> PlayerSkills = new ArrayList<>();
            Skills.Weapons.setPlayerHas(true);
            PlayerSkills.add(Skills.Weapons);

            Player player = new Player("Jaxon", 150, Inventory, PlayerSkills, 0, Items.LASER_PISTOL, 1, 0, 500);
            
            player.addItem(Items.BASIC_MEDKIT);

            System.out.println(player.toString());



    }

}