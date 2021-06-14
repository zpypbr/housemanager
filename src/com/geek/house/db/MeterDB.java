package com.geek.house.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MeterDB {
	private final static String DBNAME = "meter.db";
	private final static String TABLENAME = "meter";
	private CustomerHelper mHelper;

	public MeterDB(Context context) {
		mHelper = new CustomerHelper(context);
	}

	private class CustomerHelper extends SQLiteOpenHelper {
		public CustomerHelper(Context context) {
			super(context, DBNAME, null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE table IF NOT EXISTS "
					+ TABLENAME
					+ " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, time TEXT, houseId TEXT, customerId TEXT, waterMeter TEXT, powerMeter TEXT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int i, int i2) {
		}
	}
}
