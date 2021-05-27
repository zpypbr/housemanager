package com.geek.house.com.geek.house.db;

/**
 * Created by mm on 14-11-4.
 */
public class House {
    private String id;
    private String name;
    private String number;
    private String customer;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getCustomer() {
        return customer;
    }

    public void setHouseId(String id) {
        this.id = id;
    }

    public void setHouseName(String name) {
        this.name = name;
    }

    public void setHouseNum(String number) {
        this.name = number;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
