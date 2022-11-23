package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the HeadCoach relation
 */
public class HeadCoach extends Relation{
    private final String relationName = "HeadCoach";
    private final String[] keyNames = {"cid"};
    private final String[] attributeNames = {"name","yearsCoaching"};
    private final int cid;
    private final String name;
    private final int yearsCoaching;


    public HeadCoach(int cid, String name, int yearsCoaching) {
        this.cid = cid;
        this.name = name;
        this.yearsCoaching = yearsCoaching;
    }

    public HeadCoach() {
        this.cid = 0;
        this.name = null;
        this.yearsCoaching = 0;
    }

    public int getCid() {
        return cid;
    }
    public String getName() {
        return name;
    }
    public int getYearsCoaching() {
        return yearsCoaching;
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
