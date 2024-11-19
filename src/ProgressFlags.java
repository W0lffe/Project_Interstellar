public class ProgressFlags {
    
    private String action;
    private boolean done;
    
    /**
     * @description create progress flag object for player
     * @param action set action that was completed
     * @param done set true or false, based if completed or not
     * @info mostly used if action was completed, so tracking only TRUE 
     */
    public ProgressFlags(String action, boolean done) {
        this.action = action;
        this.done = done;
    }
    /**@return completed action as String */
    public String getAction() {
        return action;
    }
    /**
     * @description set action that is completed for flag
     * @param action string of action name
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**@return true or false of progress flag */
    public boolean isDone() {
        return done;
    }
    /**
     * @description set flag true or false
     * @param done true or false
     */
    public void setDone(boolean done) {
        this.done = done;
    }
    @Override
    public String toString() {
        return "ProgressFlags [action=" + action + ", done=" + done + "]";
    }

    
}
