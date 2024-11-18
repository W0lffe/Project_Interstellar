public class ProgressFlags {
    
    private String action;
    private boolean done;
    
    public ProgressFlags(String action, boolean done) {
        this.action = action;
        this.done = done;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    @Override
    public String toString() {
        return "ProgressFlags [action=" + action + ", done=" + done + "]";
    }

    
}
