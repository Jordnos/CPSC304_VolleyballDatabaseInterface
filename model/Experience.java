package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Experience relation
 */
public class Experience {
    private final int yearsCoaching;
    private final String experience;


    public Experience(int yearsCoaching, String experience) {
        this.yearsCoaching = yearsCoaching;
        this.experience = experience;
    }

    public int getYearsCoaching() {
        return yearsCoaching;
    }
    public String getExperience() {
        return experience;
    }

}
