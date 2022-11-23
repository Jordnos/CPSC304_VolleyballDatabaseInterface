package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the OutsideHitterBMI relation
 */
public class OutsideHitterBMI extends Relation{
    private final String relationName = "OutsideHitterBMI";
    private final String[] keyNames = {"pid"};
    private final String[] attributeNames = {"bmi"};
    private final int pid;
    private final float bmi;


    public OutsideHitterBMI(int pid, float bmi) {
        this.pid = pid;
        this.bmi = bmi;
    }

    public OutsideHitterBMI() {
        this.pid = 0;
        this.bmi = 0;
    }

    public int getPid() {return pid;}
    public float getBmi() {return bmi;}

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
