package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the MiddleBlocker relation
 */
public class MiddleBlocker extends Relation{
    private final String relationName = "MiddleBlocker";
    private final String[] keyNames = {"pid"};
    private final String[] attributeNames = {"name","weight","height","blocks","jerseyNumber","tid"};
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

    public MiddleBlocker() {
        this.pid = 0;
        this.name = null;
        this.weight = 0;
        this.height = 0;
        this.blocks = 0;
        this.jerseyNumber = 0;
        this.tid = 0;
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
