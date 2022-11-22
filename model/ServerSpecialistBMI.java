package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the ServerSpecialistBMI relation
 */
public class ServerSpecialistBMI {
    private final int pid;
    private final float bmi;


    public ServerSpecialistBMI(int pid, float bmi) {
        this.pid = pid;
        this.bmi = bmi;
    }

    public int getPid() {return pid;}
    public float getBmi() {return bmi;}

}
