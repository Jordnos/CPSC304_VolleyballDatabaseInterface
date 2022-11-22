package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the BMI relation
 */
public class Bmi {
    private final int height;
    private final int weight;
    private final float bmi;


    public Bmi(int height, int weight, float bmi) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
    }

    public int getHeight() {return height;}
    public int getWeight() {return weight;}
    public float getBmi() {return bmi;}

}
