package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the RefsFor relation
 */
public class RefsFor {
    private final int rid;
    private final int gid;

    public RefsFor(int rid, int gid) {
        this.rid = rid;
        this.gid = gid;
    }

    public int getRid() {
        return rid;
    }
    public int getGid() {
        return gid;
    }
}
