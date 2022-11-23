package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Experience relation
 */
public class Experience extends Relation{
    private final String relationName = "Experience";
    private final String[] keyNames = {"yearsCoaching"};
    private final String[] attributeNames = {"experience"};
    private final int yearsCoaching;
    private final String experience;


    public Experience(int yearsCoaching, String experience) {
        this.yearsCoaching = yearsCoaching;
        this.experience = experience;
    }

    public Experience() {
        this.yearsCoaching = 0;
        this.experience = null;
    }

    public int getYearsCoaching() {
        return yearsCoaching;
    }
    public String getExperience() {
        return experience;
    }

    public String getRelationName() {
        return relationName;
    }

    @Override
    public String[] getNonKeyNames() {
        return attributeNames;
    }

    @Override
    public String[] getKeyNames() {
        return keyNames;
    }

}
