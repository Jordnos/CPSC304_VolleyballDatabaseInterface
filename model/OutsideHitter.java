package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the OutsideHitter relation
 */
public class OutsideHitter {
    private final int pid;
    private final String name;
    private final int weight;
    private final int height;
    private final int kills;
    private final int aces;
    private final int blocks;
    private final int jerseyNumber;
    private final int tid;

    public OutsideHitter(int pid, String name, int weight, int height, int kills, int aces, int blocks, int jerseyNumber, int tid) {
        this.pid = pid;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.kills = kills;
        this.aces = aces;
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
    public int getKills() {return kills;}
    public int getAces() {return aces;}
    public int getBlocks() {return blocks;}
    public int getJerseyNumber() {return jerseyNumber;}
    public int getTid() {return tid;}
}
