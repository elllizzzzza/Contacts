package org.example;

public class Organization extends Contact{
    private String orgName;
    private String address;

    public Organization(String orgName ,String address) {
        super();
        this.address = address;
        this.orgName = orgName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        updateLastUpdated();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
        updateLastUpdated();
    }

    @Override
    public String[] getEditableFields() {
        return new String[] {"name", "address", "number"};
    }

    @Override
    public void setField(String fieldName, String value) {
        switch (fieldName.toLowerCase()) {
            case "name":
                setOrgName(value);
                break;
            case "address":
                setAddress(value);
                break;
            case "number":
                setPhoneNumber(value);
                break;
            default:
                System.out.println("Field not found");
        }
    }

    @Override
    public String getField(String fieldName) {
        switch (fieldName.toLowerCase()) {
            case "name":
                return orgName;
            case "address":
                return address;
            case "number":
                return getPhoneNumber();
            default:
                return "";
        }
    }

    @Override
    public String getInfo() {
        return "Organization{" +
                "name=" + orgName +
                ", address = " + address +
                ", number = " + getPhoneNumber() +
                ", time created = " + getTimeCreated() +
                ", last edited = " + getLastUpdated() +
                '}';
    }
}
