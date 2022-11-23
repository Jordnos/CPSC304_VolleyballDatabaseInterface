package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the BMI relation
 */
public class Bmi extends Relation{

    private final String relationName = "BMI";
    private final String[] keyNames = {"height","weight"};
    private final String[] attributeNames = {"bmi"};
    private final int height;
    private final int weight;
    private final float bmi;


    public Bmi(int height, int weight, float bmi) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
    }

    public Bmi() {
        this.height = 0;
        this.weight = 0;
        this.bmi = 0;
    }

    public int getHeight() {return height;}
    public int getWeight() {return weight;}
    public float getBmi() {return bmi;}

    @Override
    public Object[] getData() {
        return new Object[] {height, weight, bmi};
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
