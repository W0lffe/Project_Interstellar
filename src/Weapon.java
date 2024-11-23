import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Weapon extends Items{
    
    private int minDamage;
    private int maxDamage;
    private static Map<String, ArrayList<Weapon>> tieredWeaponsMap = new HashMap<>();

    /**
     * @description Constructor for Weapon object
     * @param item weapon name
     * @param type Type of weapon
     * @param minDamage minimum damage of weapon
     * @param maxDamage maximum damage of weapon
     * @param description brief description of weapon
     * @param quantity how many weapons
     */
    public Weapon(String item, String type, int minDamage, int maxDamage, String description ,int quantity) {
        super(item, type, description, quantity);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    /**@return minimun damage of weapon as Integer */
    public int getMinDamage() {
        return minDamage;
    }

    /**
     * @description set minimum damage for weapon
     * @param minDamage value to be set
     */
    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    /**@return maximum damage of weapon as Integer */
    public int getMaxDamage() {
        return maxDamage;
    }

    
    /**
     * @description set maximum damage for weapon
     * @param minDamage value to be set
     */
    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }


    @Override
    public String toString() {
        return "[" + getItem() + " | " + getType() + " | Damage: " + minDamage + "-" + maxDamage + " | " + getDescription() + "]";
    }

    //predefined weapons
    public static Weapon NONE = new Weapon("Hands", "Hands", 0, 0, "Hands", 0);
    public static Weapon GRAVITY_CANNON = new Weapon("Gravity Cannon", "Heavy Weapon", 40, 50, "Uses gravity pulses to crush and destabilize enemies", 1);
    public static Weapon ANTIMATTER_RIFLE = new Weapon("Antimatter Rifle", "Energy Weapon", 60, 75, "Fires a powerful stream of antimatter particles, instantly annihilating targets", 1);

    /**@description initialize tiered weapons lists for NPC usage and loot pools.
     * Lists are placed to a Map
    */
    public static void initWeaponLists(){
        ArrayList<Weapon> lowTierWeapons = new ArrayList<>();
        ArrayList<Weapon> mediumTierWeapons = new ArrayList<>();
        ArrayList<Weapon> highTierWeapons = new ArrayList<>();

        //LOW-GRADE | MAX-DAMAGE BELOW 20
        lowTierWeapons.add(new Weapon("Pulse Pistol", "Energy Weapon", 8, 12, "A compact energy-based sidearm", 1));
        lowTierWeapons.add(new Weapon("Laser Pistol", "Energy Weapon" , 9, 13, "Lightweight pistol for sharpshooters", 1));
        lowTierWeapons.add(new Weapon("Phase Revolver", "Energy Weapon", 14, 18, "Emits short-range energy blasts that can phase through armor with minimal delay", 1));
        lowTierWeapons.add(new Weapon("Sentry Carbine", "Energy Carbine", 11, 17, "Mid-range energy carbine for quick strikes", 1));

        //MEDIUM-GRADE | MAX-DAMAGE BELOW 30
        mediumTierWeapons.add(new Weapon("Blaze Rifle", "Energy Rifle", 20, 25, "An energy rifle that shoots high-intensity plasma bolts capable of igniting enemies", 1));
        mediumTierWeapons.add(new Weapon("Stinger Pistol", "Projectile Weapon", 16, 20, "Fires compact, armor-piercing rounds with pinpoint accuracy, perfect for critical shots", 1));
        mediumTierWeapons.add(new Weapon("Pulse Rifle", "Energy Weapon" ,18, 22, "Firepower with versatility", 1));
        mediumTierWeapons.add(new Weapon("Laser Rifle", "Energy Weapon" ,15, 20, "Lightweight rifle with precision", 1));
        mediumTierWeapons.add(new Weapon("Particle Beamer", "Energy Weapon", 20, 28, "Fires a continuous, precise particle beam", 1));
        mediumTierWeapons.add(new Weapon("Hypersonic Repeater", "Projectile Weapon", 18, 24, "Rapid-firing weapon that launches high-speed projectiles with piercing power", 1));

        //HIGH-GRADE | MAX-DAMAGE OVER 30
        highTierWeapons.add(new Weapon("Fury Rifle", "Projectile Rifle", 28, 45, "Fires explosive rounds that deal massive area damage", 1));
        highTierWeapons.add(new Weapon("Fusion Rifle", "Energy Weapon", 25, 35, "Fires fusion energy rounds with explosive impact and long-range capabilities", 1));
        highTierWeapons.add(new Weapon("Quantum Repeater", "Energy Weapon" , 27, 35, "Unleash a hail of destabilized energy", 1));
        highTierWeapons.add(new Weapon("Omega Carbine", "Energy Carbine", 30, 40, "A heavy-hitting carbine that fires powerful charged energy bolts", 1));

        tieredWeaponsMap.put("Low Tier", lowTierWeapons);
        tieredWeaponsMap.put("Medium Tier", mediumTierWeapons);
        tieredWeaponsMap.put("High Tier", highTierWeapons);
    }
    
    /**
     * @description retrieves tiered weapon from weaponlist
     * @param tier Wanted tier to retrieve as string
     * @return Tiered weapon as Weapon object
     */
    public static Weapon retrieveTieredWeapon(String tier){
        ArrayList<Weapon> tierList = tieredWeaponsMap.get(tier);
        Collections.shuffle(tierList);
        return tierList.getFirst();
    }
}
