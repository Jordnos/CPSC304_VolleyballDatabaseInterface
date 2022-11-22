package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the MiddleBlocker relation
 */
public class MiddleBlocker {
    private final int pid;
    private final String name;
    private final int weight;
    private final int height;
    private final int blocks;
    private final int jerseyNumber;
    private final int tid;

    public MiddleBlocker(int pid, String name, int weight, int height, int blocks, int jerseyNumber, int tid) {
        this.pid = pid;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.blocks = blocks;
        this.jerseyNumber = jerseyNumber;
        this.tid = tid;
    }

    public int getPid() {
        return pid;
    }
    public String getName() {return name;}
    public int getWeight() {return weight;}
    public int getHeight() {return height;}
    public int getBlocks() {return blocks;}
    public int getJerseyNumber() {return jerseyNumber;}
    public int getTid() {return tid;}
}
