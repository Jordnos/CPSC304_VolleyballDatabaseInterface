package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the League relation
 */
public class League extends Relation{
    private final String relationName = "League";
    private final String[] keyNames = {"lid"};
    private final String[] attributeNames = {"cName","name"};
    private final int lid;
    private final String cName;
    private final String name;


    public League(String cName, String name, int lid) {
        this.lid = lid;
        this.cName = cName;
        this.name = name;

    }

    public League() {
        this.lid = 0;
        this.cName = null;
        this.name = null;
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
