package ca.ubc.cs304.model;




/**
 * The intent for this class is to update/store information about the Country relation
 */
public class Country {
    private final String cName;
    private final int population;


    public Country(String cName,int population) {
        this.population = population;
        this.cName = cName;
    }

    public int getPopulation() {
        return population;
    }
    public String getCName() {
        return cName;
    }
}
