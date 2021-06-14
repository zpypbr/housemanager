package com.geek.house;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.geek.house.db.Customer;
import com.geek.house.db.CustomerDB;
import com.geek.house.db.House;

public class HouseInfoActivity extends Activity implements OnItemClickListener {
	private ListView mHouseInfo;
	private House mHouse;
	private Customer mCustomer;
	private CustomerDB mCustomerDB;
	private MyAdapter mMyAdapter;
	private List<HouseData> mHouseInfoList = new ArrayList<HouseData>();
	private boolean mNoCustomer;
	private final int NEW_CUSTOMER_CODE = 1;

	private enum HOUSE_INFO {
		HOUSE_TOP, HOUSE_GROUP, HOUSE_CUSTOMER, HOUSE_TEXT
	};

	private class HouseData {
		HOUSE_INFO info;
		String title;
		String subTitle;
	}

	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_house_info);
		mCustomerDB = new CustomerDB(this);
		mHouse = ((House) getIntent().getExtras().getSerializable("HOUSE"));
		mNoCustomer = Customer.NULL_CUSTOMER.equals(mHouse.getCustomerId());
		if (!mNoCustomer) {
			mCustomer = mCustomerDB.getCustomer(mHouse.getCustomerId());
		}
		loadHouseInfo();
		mHouseInfo = (ListView) findViewById(R.id.listViewHouseInfo);
		mMyAdapter = new MyAdapter(this);
		mHouseInfo.setAdapter(mMyAdapter);
		mHouseInfo.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if (mNoCustomer && arg2 == 2) {
			Intent intent = new Intent();
			intent.setClass(this, NewCustomerActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("HOUSE", mHouse);
			intent.putExtras(bundle);
			startActivityForResult(intent, NEW_CUSTOMER_CODE);
		}
	}

	private void loadHouseInfo() {
		HouseData data = new HouseData();
		data.info = HOUSE_INFO.HOUSE_TOP;
		data.title = mHouse.getHouseInfo();
		if (mNoCustomer) {
			data.subTitle = "房屋闲置没有账单";
		} else {
			data.subTitle = "点击查看账单";
		}
		mHouseInfoList.add(data);
		//
		data = new HouseData();
		data.info = HOUSE_INFO.HOUSE_GROUP;
		data.title = "租客信息";
		mHouseInfoList.add(data);
		//
		data = new HouseData();
		data.info = HOUSE_INFO.HOUSE_CUSTOMER;
		if (mNoCustomer) {
			data.title = "尚未出租";
			data.subTitle = "点击添加租客信息";
			mHouseInfoList.add(data);
		} else {
			data.title = mCustomer.getCustomerName();
			data.subTitle = mCustomer.getCustomerPhone();
			mHouseInfoList.add(data);
			//
			data = new HouseData();
			data.info = HOUSE_INFO.HOUSE_GROUP;
			data.title = "签约资料";
			mHouseInfoList.add(data);
			//
			data = new HouseData();
			data.info = HOUSE_INFO.HOUSE_TEXT;
			data.title = "押金";
			data.subTitle = mCustomer.getDeposit();
			mHouseInfoList.add(data);
			//
			data = new HouseData();
			data.info = HOUSE_INFO.HOUSE_TEXT;
			data.title = "租金";
			data.subTitle = mCustomer.getRent();
			mHouseInfoList.add(data);
		}
	}

	class MyAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public MyAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return mHouseInfoList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				switch (mHouseInfoList.get(position).info) {
				case HOUSE_TOP:
					convertView = inflater.inflate(R.layout.item_imageview_title_subtitle, null,
							false);
					holder.title = (TextView) convertView.findViewById(R.id.textViewTitle);
					holder.subTitle = (TextView) convertView.findViewById(R.id.textViewSubTitle);
					break;
				case HOUSE_GROUP:
					convertView = inflater.inflate(R.layout.item_group_name, null, false);
					holder.title = (TextView) convertView.findViewById(R.id.textViewGroupName);
					break;
				case HOUSE_CUSTOMER:
					convertView = inflater.inflate(R.layout.item_customer_phone, null, false);
					holder.title = (TextView) convertView.findViewById(R.id.textViewCustomerName);
					holder.subTitle = (TextView) convertView.findViewById(R.id.textViewPhone);
					break;
				case HOUSE_TEXT:
					convertView = inflater.inflate(R.layout.item_textview_textview_noborder, null,
							false);
					holder.title = (TextView) convertView.findViewById(R.id.textViewLeft);
					holder.subTitle = (TextView) convertView.findViewById(R.id.textViewRight);
					break;
				}
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			fillView(position, holder);
			return convertView;
		}

		private void fillView(int position, ViewHolder holder) {
			HouseData data = mHouseInfoList.get(position);
			if (data.title != null) {
				holder.title.setText(data.title);
			}
			if (data.subTitle != null) {
				holder.subTitle.setText(data.subTitle);
			}
		}
	}

	static class ViewHolder {
		TextView title;
		TextView subTitle;
	}
}
