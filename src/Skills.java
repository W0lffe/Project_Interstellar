import java.util.ArrayList;

/**@class Skills
 * @description contains object creating of Skills and object methods
 */
public class Skills {
    
    private String skill;
    private boolean playerHas;
    private String description;
    
    /**
     * @description Constructor for skill
     * @param skill name for skill
     * @param playerHas true or false, based on if player has skill
     * @param description brief description of skill
     */
    public Skills(String skill, boolean playerHas, String description) {
        this.skill = skill;
        this.playerHas = playerHas;
        this.description = description;
    }

    /** @return skill object name*/
    public String getSkill() {
        return skill;
    }
    /**
     * @description set a name for skill
     * @param skill string to be set as name
     */
    public void setSkill(String skill) {
        this.skill = skill;
    }

    /** @return true or false based on if player has skill*/
    public boolean isPlayerHas() {
        return playerHas;
    }
      /**
     * @description set skill true or false if player has or not
     * @param skill true or false
     */
    public void setPlayerHas(boolean playerHas) {
        this.playerHas = playerHas;
    }

    /** @return skill object name*/
    public String getDescription() {
        return description;
    }

    /**
     * @description set a description for skill
     * @param description string to be set as description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**@decsription Initialized list of skills */
    public static ArrayList<Skills> SkillList = new ArrayList<>();

    //Add predefined skills to arraylist, function called from Game.newGame
    /**@function initializeSkillArray
     * @description initializes list of skills with predefined skills
     */
    public static void initializeSkillArray(){
        SkillList.add(Weapons);
        SkillList.add(Lockpicking);
        SkillList.add(Computers);
        SkillList.add(Medicine);
        SkillList.add(Speech);
        SkillList.add(Engineering);
        SkillList.add(Stealth);
    }

    //Predefined Skills
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
