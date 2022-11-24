package ca.ubc.cs304.model;



/**
 * The intent for this class is to update/store information about the Ref relation
 */
public class Ref extends Relation{
    private final String relationName = "Ref";
    private final String[] keyNames = {"rid"};
    private final String[] attributeNames = {"name","salary"};
    private final int rid;
    private final String name;
    private final int salary;


    public Ref(int rid, String name, int salary) {
        this.rid = rid;
        this.name = name;
        this.salary = salary;
    }

    public Ref() {
        this.rid = 0;
        this.name = null;
        this.salary = 0;
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


    @Override
    public Object[] getData() {
        Object[] o = {rid, name, salary};
        return o;
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
