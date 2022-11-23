package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Setter relation
 */
public class Setter extends Relation{
    private final String relationName = "Setter";
    private final String[] keyNames = {"pid"};
    private final String[] attributeNames = {"name","weight","height","setAttempts","setSuccessRate","jerseyNumber","tid"};
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

    public Setter() {
        this.pid = 0;
        this.name = null;
        this.weight = 0;
        this.height = 0;
        this.setAttempts = 0;
        this.setSuccessRate = 0;
        this.jerseyNumber = 0;
        this.tid = 0;
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
    @Override
    public String getRelationName() {
        return relationName;
    }

    @Override
    public String[] getKeyNames() {
        return keyNames;
    }

    @Override
    public String[] getNonKeyNames() {
        return attributeNames;
    }
}
