package ca.ubc.cs304.model;


/**
 * The intent for this class is to update/store information about the StadiumAddress relation
 */
public class StadiumAddress extends Relation{
    private final String relationName = "StadiumAddress";
    private final String[] keyNames = {"sid"};
    private final String[] attributeNames = {"city","address"};
    private final int sid;
    private final String city;
    private final String address;


    public StadiumAddress(int sid, String city, String address) {
        this.sid = sid;
        this.city = city;
        this.address = address;
    }

    public StadiumAddress() {
        this.sid = 0;
        this.city = null;
        this.address = null;
    }

    public int getSid() {
        return sid;
    }
    public String getCity() {
        return city;
    }
    public String getAddress() {
        return address;
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
