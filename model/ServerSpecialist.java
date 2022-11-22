package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about the ServerSpecialist relation
 */
public class ServerSpecialist {
    private final int pid;
    private final String name;
    private final int weight;
    private final int height;
    private final int aces;
    private final int jerseyNumber;
    private final int tid;

    public ServerSpecialist(int pid, String name, int weight, int height, int aces, int jerseyNumber, int tid) {
        this.pid = pid;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.aces = aces;
        this.jerseyNumber = jerseyNumber;
        this.tid = tid;
    }

    public int getPid() {
        return pid;
    }
    public String getName() {return name;}
    public int getWeight() {return weight;}
    public int getHeight() {return height;}
    public int getAces() {return aces;}
    public int getJerseyNumber() {return jerseyNumber;}
    public int getTid() {return tid;}
}