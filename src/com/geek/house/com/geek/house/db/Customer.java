package com.geek.house.com.geek.house.db;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mm on 14-11-4.
 */
public class Customer {
    private String id;
    private String name;
    private String phone;
    private long leaseTime;
    private long rentalDate;

    public Customer() {
        Calendar localCalendar = Calendar.getInstance();
        this.rentalDate = localCalendar.getTime().getTime();
        localCalendar.add(Calendar.MONTH, 12);
        this.leaseTime = localCalendar.getTime().getTime();
        createId();
    }

    private void createId() {
        long l1 = System.currentTimeMillis();
        String str = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date(l1));
        long l2 = 10000 + (int)(90000.0D * Math.random());
        id = "CUSTOM_" + String.valueOf(l2);
    }

    public void setName(String name) {

    }

    public void setPhoneNumber(String number) {

    }

    public String getUserId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public long getRentalDate() {
        return  rentalDate;
    }

    public long getLeaseTime() {
        return leaseTime;
    }

    public void setId(String customerId) {
        this.id = id;
    }
}
