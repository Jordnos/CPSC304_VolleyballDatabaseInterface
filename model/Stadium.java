package ca.ubc.cs304.model;


/**
 * The intent for this class is to update/store information about the StadiumAddress relation
 */
public class Stadium extends Relation{
    private final String relationName = "Stadium";
    private final String[] keyNames = {"sid"};
    private final String[] attributeNames = {"name"};
    private final int sid;
    private final String name;


    public Stadium(int sid, String name) {
        this.sid = sid;
        this.name = name;
    }

    public Stadium() {
        this.sid = 0;
        this.name = null;
    }

    public int getSid() {
        return sid;
    }
    public String getName() {
        return name;
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
