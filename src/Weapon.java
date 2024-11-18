public class Weapon extends Items{
    
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
        return "[" + getItem() + " | " + getType() + " | Damage: " + minDamage + "-" + maxDamage + " | " + getDescription() + "]";
    }


    //predefined weapons
    public static Weapon NONE = new Weapon("Hands", "Hands", 0, 0, "Hands", 0);
    public static Weapon STINGER_PISTOL = new Weapon("Stinger Pistol", "Projectile Sidearm", 12, 16, "Fires compact, armor-piercing rounds with pinpoint accuracy, perfect for critical shots", 1);
    public static Weapon PHASE_REVOLVER = new Weapon("Phase Revolver", "Energy Sidearm", 14, 18, "Emits short-range energy blasts that can phase through armor with minimal delay", 1);
    public static Weapon PULSE_PISTOL = new Weapon("Pulse Pistol", "Energy Weapon", 8, 12, "A compact energy-based sidearm", 1);
    public static Weapon PULSE_RIFLE = new Weapon("Pulse Rifle", "Energy Weapon" ,18, 22, "Firepower with versatility", 1);
    public static Weapon LASER_RIFLE  = new Weapon("Laser Rifle", "Energy Weapon" ,15, 20, "Lightweight rifle with precision", 1);
    public static Weapon LASER_PISTOL  = new Weapon("Laser Pistol", "Energy Weapon" , 9, 13, "Lightweight pistol for sharpshooters", 1);
    public static Weapon QUANTUM_REPEATER  = new Weapon("Quantum Repeater", "Energy Weapon" , 27, 35, "Unleash a hail of destabilized energy", 1);
    public static Weapon GRAVITY_CANNON = new Weapon("Gravity Cannon", "Heavy Weapon", 40, 50, "Uses gravity pulses to crush and destabilize enemies", 1);
    public static Weapon PARTICLE_BEAMER = new Weapon("Particle Beamer", "Energy Weapon", 20, 28, "Fires a continuous, precise particle beam", 1);
    public static Weapon HYPERSONIC_REPEATER = new Weapon("Hypersonic Repeater", "Projectile Weapon", 18, 24, "Rapid-firing weapon that launches high-speed projectiles with piercing power", 1);
}
