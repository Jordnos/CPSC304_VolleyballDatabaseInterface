package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Team relation
 */
public class Team {
    private final int tid;
    private final String name;
    private final int lid;
    private final int cid


    public Team(int tid, String name, int lid, int cid) {
        this.tid = tid;
        this.name = name;
        this.lid = cid;
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


}
