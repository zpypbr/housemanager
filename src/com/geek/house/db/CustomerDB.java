package com.geek.house.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mm on 14-11-4.
 */
public class CustomerDB {
	private final static String DBNAME = "customer.db";
	private final static String TABLENAME = "customer";
	private CustomerHelper mHelper;

	public CustomerDB(Context context) {
		mHelper = new CustomerHelper(context);
	}

	public void addCustomer(Customer u) {
		SQLiteDatabase db = mHelper.getWritableDatabase();
		db.execSQL("insert into " + TABLENAME
				+ " (customerId, customerName, customerPhone) values(?,?,?)",
				new Object[] { u.getCustomerId(), u.getCustomerName(), u.getCustomerPhone() });
		db.close();
	}

	public Customer getCustomer(String customerId) {
		SQLiteDatabase db = mHelper.getReadableDatabase();
		Customer customer = new Customer();
		Cursor c = db.rawQuery("select * from " + TABLENAME + " where customerId = ?",
				new String[] { customerId });
		while (c.moveToNext()) {
			customer.setCustomerId(customerId);
			customer.setCustomerName(c.getString(c.getColumnIndex("customerName")));
			customer.setCustomerPhone(c.getString(c.getColumnIndex("customerPhone")));
		}
		return customer;
	}

	private class CustomerHelper extends SQLiteOpenHelper {
		public CustomerHelper(Context context) {
			super(context, DBNAME, null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE table IF NOT EXISTS "
					+ TABLENAME
					+ " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, customerId TEXT, customerName TEXT, customerPhone TEXT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int i, int i2) {
		}
	}
}
