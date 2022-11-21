package ca.ubc.cs304.model;


/**
 * The intent for this class is to update/store information about the StadiumName relation
 */
public class StadiumName {
    private final String address;
    private final String name;



    public StadiumName(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }

}
