package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the WorksFor relation
 */
public class WorksFor extends Relation{
    private final String relationName = "WorksFor";
    private final String[] keyNames = {"rid","lid"};
    private final String[] attributeNames = {};
    private final int rid;
    private final int lid;

    public WorksFor(int rid, int lid) {
        this.rid = rid;
        this.lid = lid;
    }

    public WorksFor() {
        this.rid = 0;
        this.lid = 0;
    }

    public int getRid() {
        return rid;
    }
    public int getLid() {
        return lid;
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
