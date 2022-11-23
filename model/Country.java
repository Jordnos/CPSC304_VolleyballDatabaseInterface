package ca.ubc.cs304.model;




/**
 * The intent for this class is to update/store information about the Country relation
 */
public class Country extends Relation{

    private final String relationName = "Country";
    private final String[] keyNames = {"cName"};
    private final String[] attributeNames = {"population"};
    private final String cName;
    private final int population;


    public Country(String cName,int population) {
        this.population = population;
        this.cName = cName;
    }
    public Country() {
        this.population = 0;
        this.cName = null;
    }

    public int getPopulation() {
        return population;
    }
    public String getCName() {
        return cName;
    }
    @Override
    public Object[] getData() {
        Object[] o = {cName, population};
        return o;
    }

    @Override
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
