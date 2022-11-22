package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the WorksFor relation
 */
public class WorksFor {
    private final int cid;
    private final int lid;

    public WorksFor(int cid, int lid) {
        this.cid = cid;
        this.lid = lid;
    }

    public int getCid() {
        return cid;
    }
    public int getLid() {
        return lid;
    }
}
