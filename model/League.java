package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the League relation
 */
public class League {
    private final int lid;
    private final String cName;
    private final String name;


    public League(String cName, String name, int lid) {
        this.lid = lid;
        this.cName = cName;
        this.name = name;

    }

    public int lid() {
        return lid;
    }
    public String getName() {
        return name;
    }
    public String getCName() {
        return cName;
    }
}
