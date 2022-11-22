package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Setter relation
 */
public class Setter {
    private final int pid;
    private final String name;
    private final int weight;
    private final int height;
    private final int setAttempts;
    private final float setSuccessRate;
    private final int jerseyNumber;
    private final int tid;

    public Setter(int pid, String name, int weight, int height, int setAttempts, float setSuccessRate ,int jerseyNumber, int tid) {
        this.pid = pid;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.setAttempts = setAttempts;
        this.setSuccessRate = setSuccessRate;
        this.jerseyNumber = jerseyNumber;
        this.tid = tid;
    }

    public int getPid() {
        return pid;
    }
    public String getName() {return name;}
    public int getWeight() {return weight;}
    public int getHeight() {return height;}
    public int getSetAttempts() {return setAttempts;}
    public float getSetSuccessRate() {return setSuccessRate;}
    public int getJerseyNumber() {return jerseyNumber;}
    public int getTid() {return tid;}
}
