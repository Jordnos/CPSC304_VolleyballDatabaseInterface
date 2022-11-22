package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Coach Experience relation
 */
public class CoachExperience {
    private final int cid;
    private final String experience;


    public CoachExperience(int cid, String experience) {
        this.cid = cid;
        this.experience = experience;
    }

    public int getCid() {
        return cid;
    }
    public String getExperience() {
        return experience;
    }

}
