package com.geek.house.db;

import com.geek.house.util.Util;


/**
 * Created by mm on 14-11-4.
 */
public class Customer {
	public static String NULL_CUSTOMER = "CUSTOM_NULL";
	private String CustomerId;
    private String CustomerName;
    private String CustomerPhone;
	public String Deposit;
    public String Rent;
    
    public Customer() {
    	CustomerId = Util.createUniqueId(Customer.class.getSimpleName());
    }

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
	
    public void setDeposit(String deposit) {
		Deposit = deposit;
	}

	public void setRent(String rent) {
		Rent = rent;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getCustomerPhone() {
		return CustomerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		CustomerPhone = customerPhone;
	}

	public String getDeposit() {
		return Deposit;
	}

	public String getRent() {
		return Rent;
	}
}
