package com.geek.house;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.geek.house.com.geek.house.db.Customer;
import com.geek.house.com.geek.house.db.House;
import com.geek.house.com.geek.house.db.HouseDB;

public class NewHouseActivity extends Activity {
    private HouseDB mHouseDB;
    private EditText mHouseName;
    private EditText mHouseNum;
	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_new_house);
        mHouseDB = new HouseDB(this);
        mHouseName = (EditText)findViewById(R.id.editTextHouseName);
        mHouseNum = (EditText)findViewById(R.id.editTextHouseNum);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu paramMenu) {
		getMenuInflater().inflate(R.menu.save, paramMenu);
		return true;
	}

    private void saveHouse() {
        House house = new House();
        house.setHouseName(mHouseName.getText().toString());
        house.setHouseNum(mHouseNum.getText().toString());
        house.setCustomer(Customer.NULL_CUSTOMER);
        mHouseDB.addHouse(house);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
		switch (paramMenuItem.getItemId()) {
		case R.id.action_save:
            saveHouse();
			finish();
		default:
			return super.onOptionsItemSelected(paramMenuItem);
		}
	}
}
