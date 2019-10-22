package com.jangphong.hem.banksearchassignment;

public class ListItem {

    private String bank_name;
    private String address;
    private String ifsc;
    private String branch;
    private String bank_id;
    private String city;
    private String district;
    private String state;

    public ListItem(String bank_name, String address, String ifsc, String branch, String bank_id, String city, String district, String state)
    {
        this.bank_name = bank_name;
        this.address = address;
        this.ifsc = ifsc;
        this.branch = branch;
        this.bank_id = bank_id;
        this.city = city;
        this.district = district;
        this.state = state;
    }

    public String getBank_name()
    {
        return bank_name;
    }

    public String getAddress() {
        return address;
    }

    public String getIfsc() {
        return ifsc;
    }

    public String getBranch() {
        return branch;
    }

    public String getBank_id() {
        return bank_id;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }
}
