package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the HeadCoach relation
 */
public class HeadCoach {
    private final int cid;
    private final String name;
    private final int yearsCoaching;


    public HeadCoach(int cid, String name, int yearsCoaching) {
        this.cid = cid;
        this.name = name;
        this.yearsCoaching = yearsCoaching;
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

}
