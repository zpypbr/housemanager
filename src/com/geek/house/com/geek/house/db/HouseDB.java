package com.geek.house.com.geek.house.db;

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
        db.execSQL("insert into " + TABLENAME + " (id, name, number, customer) values(?,?,?,?)", new Object[]{u.getId(), u.getName(), u.getNumber(), u.getCustomer()});
        db.close();
    }

    public List<House> getAllHouse() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        List<House> list = new LinkedList<House>();
        Cursor c = db.rawQuery("select * from " + TABLENAME, null);
        while (c.moveToFirst()) {
            House house = new House();
            house.setHouseId(c.getString(c.getColumnIndex("id")));
            house.setHouseName(c.getString(c.getColumnIndex("name")));
            house.setHouseNum(c.getString(c.getColumnIndex("number")));
            house.setCustomer(c.getString(c.getColumnIndex("customer")));
            list.add(house);
        }
        c.close();
        db.close();
        return list;
    }

    public List<House> getWZHouse() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        List<House> list = new LinkedList<House>();
        Cursor c = db.rawQuery("select * from " + TABLENAME + " where customer is null", null);
        while (c.moveToFirst()) {
            House house = new House();
            house.setHouseId(c.getString(c.getColumnIndex("id")));
            house.setHouseName(c.getString(c.getColumnIndex("name")));
            house.setHouseNum(c.getString(c.getColumnIndex("number")));
            house.setCustomer(null);
            list.add(house);
        }
        c.close();
        db.close();
        return list;
    }

    public List<House> getYZHouse() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        List<House> list = new LinkedList<House>();
        Cursor c = db.rawQuery("select * from " + TABLENAME + " where customer is not null", null);
        while (c.moveToFirst()) {
            House house = new House();
            house.setHouseId(c.getString(c.getColumnIndex("id")));
            house.setHouseName(c.getString(c.getColumnIndex("name")));
            house.setHouseNum(c.getString(c.getColumnIndex("number")));
            house.setCustomer(c.getString(c.getColumnIndex("customer")));
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
            db.execSQL("CREATE table IF NOT EXISTS " + TABLENAME +
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, name TEXT, number TEXT, customer TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        }
    }
}
