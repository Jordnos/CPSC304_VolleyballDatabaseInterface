package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Coach Experience relation
 */
public class CoachExperience extends Relation{

    private final String relationName = "CoachExperience";
    private final String[] keyNames = {"cid"};
    private final String[] attributeNames = {"experience"};
    private final int cid;
    private final String experience;


    public CoachExperience(int cid, String experience) {
        this.cid = cid;
        this.experience = experience;
    }

    public CoachExperience() {
        this.cid = 0;
        this.experience = null;
    }

    public int getCid() {
        return cid;
    }
    public String getExperience() {
        return experience;
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
