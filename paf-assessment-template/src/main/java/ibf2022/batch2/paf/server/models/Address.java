package ibf2022.batch2.paf.server.models;

public class Address {

    private String Address;
	private String Building;
	private String Street;
	private String Zipcode;
    private String Borough;

    
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public String getBuilding() {
        return Building;
    }
    public void setBuilding(String building) {
        Building = building;
    }
    public String getStreet() {
        return Street;
    }
    public void setStreet(String street) {
        Street = street;
    }
    public String getZipcode() {
        return Zipcode;
    }
    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }
    public String getBorough() {
        return Borough;
    }
    public void setBorough(String borough) {
        Borough = borough;
    }
    
    public Address(String address, String building, String street, String zipcode, String borough) {
        Address = address;
        Building = building;
        Street = street;
        Zipcode = zipcode;
        Borough = borough;
    }


}
