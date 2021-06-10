package com.geek.house.com.geek.house.db;

import java.text.SimpleDateFormat;

/**
 * Created by mm on 14-11-4.
 */
public class House {
    private String id;
    private String name;
    private String number;
    private String customer;

    public House() {
        createId();
    }

    private void createId() {
        long l1 = System.currentTimeMillis();
        String str = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new java.sql.Date(l1));
        long l2 = 10000 + (int)(90000.0D * Math.random());
        id = "HOUSE_" + String.valueOf(l2);
    }

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
