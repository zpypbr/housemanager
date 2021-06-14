package com.geek.house.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mm on 14-11-4.
 */
public class HouseDB {
	private final static String DBNAME = "house.db";
	private final static String TABLENAME = "house";
	private CustomerHelper mHelper;

	public HouseDB(Context context) {
		mHelper = new CustomerHelper(context);
	}

	public void addHouse(House u) {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.execSQL(
				"insert into " + TABLENAME
						+ " (houseId, houseName, houseNum, customerId) values(?,?,?,?)",
				new Object[] { u.getHouseId(), u.getHouseName(), u.getHouseNum(), u.getCustomerId() });
		db.close();
	}

	public void updateCustomer(House u) {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.execSQL("update " + TABLENAME + " set customerId = ?, rentalDate = ? where houseId = ?",
				new String[] { u.getCustomerId(), u.getRentalDate(), u.getHouseId() });
		db.close();
	}

	public List<House> getAllHouse() {
		SQLiteDatabase db = mHelper.getReadableDatabase();
		List<House> list = new LinkedList<House>();
		Cursor c = db.rawQuery("select * from " + TABLENAME, null);
		while (c.moveToNext()) {
			House house = new House();
			house.setHouseId(c.getString(c.getColumnIndex("houseId")));
			house.setHouseName(c.getString(c.getColumnIndex("houseName")));
			house.setHouseNum(c.getString(c.getColumnIndex("houseNum")));
			house.setCustomerId(c.getString(c.getColumnIndex("customerId")));
			house.setRentalDate(c.getString(c.getColumnIndex("rentalDate")));
			list.add(house);
		}
		c.close();
		db.close();
		return list;
	}

	public List<House> getWZHouse() {
		SQLiteDatabase db = mHelper.getReadableDatabase();
		List<House> list = new LinkedList<House>();
		Cursor c = db.rawQuery("select * from " + TABLENAME + " where customerId = ?",
				new String[] { Customer.NULL_CUSTOMER });
		while (c.moveToNext()) {
			House house = new House();
			house.setHouseId(c.getString(c.getColumnIndex("houseId")));
			house.setHouseName(c.getString(c.getColumnIndex("houseName")));
			house.setHouseNum(c.getString(c.getColumnIndex("houseNum")));
			house.setCustomerId(Customer.NULL_CUSTOMER);
			house.setRentalDate(null);
			list.add(house);
		}
		c.close();
		db.close();
		return list;
	}

	public List<House> getYZHouse() {
		SQLiteDatabase db = mHelper.getReadableDatabase();
		List<House> list = new LinkedList<House>();
		Cursor c = db.rawQuery("select * from " + TABLENAME + " where customerId != ?",
				new String[] { Customer.NULL_CUSTOMER });
		while (c.moveToNext()) {
			House house = new House();
			house.setHouseId(c.getString(c.getColumnIndex("houseId")));
			house.setHouseName(c.getString(c.getColumnIndex("houseName")));
			house.setHouseNum(c.getString(c.getColumnIndex("houseNum")));
			house.setCustomerId(c.getString(c.getColumnIndex("customerId")));
			house.setRentalDate(c.getString(c.getColumnIndex("rentalDate")));
			list.add(house);
		}
		c.close();
		db.close();
		return list;
	}

	private class CustomerHelper extends SQLiteOpenHelper {
		public CustomerHelper(Context context) {
			super(context, DBNAME, null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE table IF NOT EXISTS "
					+ TABLENAME
					+ " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, houseId TEXT, houseName TEXT, houseNum TEXT, customerId TEXT, rentalDate TEXT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int i, int i2) {
		}
	}
}
