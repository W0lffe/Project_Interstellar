public class Skills {
    
    private String skill;
    private boolean playerHas;
    private String description;
    
    public Skills(String skill, boolean playerHas, String description) {
        this.skill = skill;
        this.playerHas = playerHas;
        this.description = description;
    }

    public String getSkill() {
        return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }
    public boolean isPlayerHas() {
        return playerHas;
    }
    public void setPlayerHas(boolean playerHas) {
        this.playerHas = playerHas;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Skills Weapons = new Skills("Weapons", false, "10% More damage");
    public static Skills Lockpicking = new Skills("Lockpicking", false, "Enables lockpicking");
    public static Skills Computers = new Skills("Computers", false, "Learn hacking/programming");
    public static Skills Medicine = new Skills("Medicine", false, "5% More from healing items");
    public static Skills Speech = new Skills("Speech", false, "Chance to speak your way out");
    public static Skills Engineering = new Skills("Engineering", false, "Allow repair and enhancements");
    public static Skills Stealth = new Skills("Stealth", false, "You become a silhouette in the dark");

    @Override
    public String toString() {
        return "Skills [skill=" + skill + ", playerHas=" + playerHas + ", description=" + description + "]";
    }
    






    
}
