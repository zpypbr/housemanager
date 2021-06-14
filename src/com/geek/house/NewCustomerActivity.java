package com.geek.house;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.geek.house.db.Customer;
import com.geek.house.db.CustomerDB;
import com.geek.house.db.House;
import com.geek.house.db.HouseDB;
import com.geek.house.util.Util;

public class NewCustomerActivity extends Activity {
	private House mHouse;
	private HouseDB mHouseDB;
	private CustomerDB mCustomerDB;
	private EditText mCustomerName;
	private EditText mCustomerPhone;
	private EditText mCustomerDeposit;
	private EditText mCustomerRent;

	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_new_customer);
		mCustomerDB = new CustomerDB(this);
		mHouseDB = new HouseDB(this);
		mHouse = ((House) getIntent().getExtras().getSerializable("HOUSE"));
		mCustomerName = (EditText) findViewById(R.id.editTextCustomerName);
		mCustomerPhone = (EditText) findViewById(R.id.editTextPhoneNumber);
		mCustomerDeposit = (EditText) findViewById(R.id.editTextDeposit);
		mCustomerRent = (EditText) findViewById(R.id.editTextRent);
	}

	private void saveCustomer() {
		Customer customer = new Customer();
		customer.setCustomerName(mCustomerName.getText().toString());
		customer.setCustomerPhone(mCustomerPhone.getText().toString());
		customer.setDeposit(mCustomerDeposit.getText().toString());
		customer.setRent(mCustomerRent.getText().toString());
		mCustomerDB.addCustomer(customer);
		mHouse.setRentalDate(getRentalDate());
		mHouse.setCustomerId(customer.getCustomerId());
		mHouseDB.updateCustomer(mHouse);
	}

	private String getRentalDate() {
		Calendar calendar = Calendar.getInstance();
	    return Util.FormatDate(new Date(calendar.getTime().getTime()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu paramMenu) {
		getMenuInflater().inflate(R.menu.save, paramMenu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
		switch (paramMenuItem.getItemId()) {
		case R.id.action_save:
			saveCustomer();
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(paramMenuItem);
		}
	}
}
