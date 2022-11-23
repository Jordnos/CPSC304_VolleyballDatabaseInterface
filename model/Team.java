package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Team relation
 */
public class Team extends Relation{
    private final String relationName = "Team";
    private final String[] keyNames = {"tid"};
    private final String[] attributeNames = {"name","lid","cid"};
    private final int tid;
    private final String name;
    private final int lid;
    private final int cid;


    public Team(int tid, String name, int lid, int cid) {
        this.tid = tid;
        this.name = name;
        this.lid = lid;
        this.cid = cid;
    }

    public Team() {
        this.tid = 0;
        this.name = null;
        this.lid = 0;
        this.cid = 0;
    }

    public int getTid() {
        return tid;
    }
    public String getName() {
        return name;
    }
    public int getLid() {
        return lid;
    }
    public int getCid() {
        return cid;
    }
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
