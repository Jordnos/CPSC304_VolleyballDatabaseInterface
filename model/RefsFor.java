package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the RefsFor relation
 */
public class RefsFor extends Relation{
    private final String relationName = "RefsFor";
    private final String[] keyNames = {"rid","gid"};
    private final String[] attributeNames = {};
    private final int rid;
    private final int gid;

    public RefsFor(int rid, int gid) {
        this.rid = rid;
        this.gid = gid;
    }

    public RefsFor() {
        this.rid = 0;
        this.gid = 0;
    }

    public int getRid() {
        return rid;
    }
    public int getGid() {
        return gid;
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
