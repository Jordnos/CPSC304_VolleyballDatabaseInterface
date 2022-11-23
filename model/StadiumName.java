package ca.ubc.cs304.model;


/**
 * The intent for this class is to update/store information about the StadiumName relation
 */
public class StadiumName extends Relation{
    private final String relationName = "StadiumName";
    private final String[] keyNames = {"city","address"};
    private final String[] attributeNames = {"name"};
    private final String city;
    private final String address;
    private final String name;



    public StadiumName(String city, String address, String name) {
        this.city = city;
        this.address = address;
        this.name = name;
    }

    public StadiumName() {
        this.city = null;
        this.address = null;
        this.name = null;
    }

    public String getCity() {
        return city;
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
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
