package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Ref relation
 */
public class Ref {
    private final int rid;
    private final String name;
    private final int salary;


    public Ref(int rid, String name, int salary) {
        this.rid = rid;
        this.name = name;
        this.salary = salary;
    }

    public int getRid() {
        return rid;
    }
    public String getName() {
        return name;
    }
    public int getSalary() {
        return salary;
    }


}
