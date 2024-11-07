import java.util.ArrayList;

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

    public static ArrayList<Skills> SkillList = new ArrayList<>();

    public static void initializeSkillArray(){
        SkillList.add(Weapons);
        SkillList.add(Lockpicking);
        SkillList.add(Computers);
        SkillList.add(Medicine);
        SkillList.add(Speech);
        SkillList.add(Engineering);
        SkillList.add(Stealth);
        
        
    }
    public static Skills Weapons = new Skills("Weapons", false, "Every weapon now deals 5 extra damage!");
    public static Skills Lockpicking = new Skills("Lockpicking", false, "Enables lockpicking");
    public static Skills Computers = new Skills("Computers", false, "Learn hacking/programming");
    public static Skills Medicine = new Skills("Medicine", false, "Healing items now heals 5 extra points!");
    public static Skills Speech = new Skills("Speech", false, "Chance to speak your way out");
    public static Skills Engineering = new Skills("Engineering", false, "Allow repair and enhancements");
    public static Skills Stealth = new Skills("Stealth", false, "You become a silhouette in the dark");

    @Override
    public String toString() {
        return "[Skill: " + skill + " | " + description + "]";
    }
    






    
}
