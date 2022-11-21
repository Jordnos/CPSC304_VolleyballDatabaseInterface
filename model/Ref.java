package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Ref relation
 */
public class Ref {
    private final int rid;
    private final String name;


    public Ref(int rid, String name) {
        this.rid = rid;
        this.name = name;
    }

    public int getRid() {
        return rid;
    }
    public String getName() {
        return name;
    }

}

