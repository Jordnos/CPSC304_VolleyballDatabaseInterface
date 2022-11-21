package ca.ubc.cs304.model;


/**
 * The intent for this class is to update/store information about the StadiumAddress relation
 */
public class StadiumAddress {
    private final int sid;
    private final String cName;
    private final String name;


    public StadiumAddress(String cName, String name, int sid) {
        this.cName = cName;
        this.name = name;
        this.sid = sid;
    }

    public int getSid() {
        return sid;
    }
    public String getName() {
        return name;
    }
    public String getCName() {
        return cName;
    }
}
