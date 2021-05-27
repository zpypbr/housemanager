package com.geek.house.com.geek.house.db;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.IBinder;

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

    public void addMessage(Customer u) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("insert into" + TABLENAME + " (user, name, phone) values(?,?,?)", new Object[]{u.getUserId(), u.getName(), u.getPhoneNumber()});
        db.close();
    }

    private class CustomerHelper extends SQLiteOpenHelper {
        public CustomerHelper(Context context) {
            super(context, DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE table IF NOT EXISTS " + TABLENAME +
                    " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, name TEXT, phone TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        }
    }
}
