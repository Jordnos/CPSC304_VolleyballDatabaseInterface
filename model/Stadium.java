package ca.ubc.cs304.model;


/**
 * The intent for this class is to update/store information about the StadiumAddress relation
 */
public class Stadium {
    private final String city;
    private final String address;
    private final String name;


    public Stadium(String city, String address, String name) {
        this.city = city;
        this.address = address;
        this.name = name;
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

}
