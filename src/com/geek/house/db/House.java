package com.geek.house.db;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.geek.house.util.Util;

/**
 * Created by mm on 14-11-4.
 */
public class House implements Serializable {
	private static final long serialVersionUID = 1L;

	public String getHouseId() {
		return HouseId;
	}

	public void setHouseId(String houseId) {
		HouseId = houseId;
	}

	public String getHouseName() {
		return HouseName;
	}

	public void setHouseName(String houseName) {
		HouseName = houseName;
	}

	public String getHouseNum() {
		return HouseNum;
	}

	public void setHouseNum(String houseNum) {
		HouseNum = houseNum;
	}

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public void setRentalDate(String rentalDate) {
		RentalDate = rentalDate;
	}

	public String getRentalDate() {
		return RentalDate;
	}

	public long getRecentlyDay() {
		Calendar calendar = Calendar.getInstance();
		long NowDay = calendar.getTime().getTime();
		calendar.setTime(java.sql.Date.valueOf(RentalDate));
		calendar.add(Calendar.MONTH, 1);
		long NextDay = calendar.getTime().getTime();
		RecentlyDay = (NextDay - NowDay) / 86400000L;
		return RecentlyDay;
	}

	private String HouseId;
	private String HouseName;
	private String HouseNum;
	private String CustomerId;
	private String RentalDate;
	private long RecentlyDay = 0L;

	public House() {
		HouseId = Util.createUniqueId(House.class.getSimpleName());
		CustomerId = Customer.NULL_CUSTOMER;
	}

	public String getHouseInfo() {
		return HouseName + " " + HouseNum;
	}
}
