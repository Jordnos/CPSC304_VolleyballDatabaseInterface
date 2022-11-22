package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Libero relation
 */
public class Libero {
    private final int pid;
    private final String name;
    private final int weight;
    private final int height;
    private final int digs;
    private final int jerseyNumber;
    private final int tid;

    public Libero(int pid, String name, int weight, int height, int digs, int jerseyNumber, int tid) {
        this.pid = pid;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.digs = digs;
        this.jerseyNumber = jerseyNumber;
        this.tid = tid;
    }

    public int getPid() {
        return pid;
    }
    public String getName() {return name;}
    public int getWeight() {return weight;}
    public int getHeight() {return height;}
    public int getDigs() {return digs;}
    public int getJerseyNumber() {return jerseyNumber;}
    public int getTid() {return tid;}
}
