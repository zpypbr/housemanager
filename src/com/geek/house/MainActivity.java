package com.geek.house;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import com.geek.house.db.HouseDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnItemClickListener {
	private ListView mHouseList;
	private MyAdapter mMyAdapter;
	private HouseDB mHouseDb;
	private CustomerDB mCustomerDB;
	private List<House> mHoustList = new ArrayList<House>();

	private final int NEW_HOUSE_CODE = 1;

	private enum HOUSE_TYPE {
		ALL_HOUSE, YZ_HOUSE, WZ_HOUSE, SZ_HOUSE
	};

	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_main);
		mHouseDb = new HouseDB(this);
		mCustomerDB = new CustomerDB(this);
		mMyAdapter = new MyAdapter(this);
		mHouseList = (ListView) findViewById(R.id.listViewHouse);
		mHouseList.setOnItemClickListener(this);
		setFilter(HOUSE_TYPE.ALL_HOUSE);
		mHouseList.setAdapter(mMyAdapter);
	}

	private void setFilter(HOUSE_TYPE type) {
		switch (type) {
		case ALL_HOUSE:
			mHoustList = mHouseDb.getAllHouse();
			break;
		case YZ_HOUSE:
			mHoustList = mHouseDb.getYZHouse();
			break;
		case WZ_HOUSE:
			mHoustList = mHouseDb.getWZHouse();
			break;
		case SZ_HOUSE:
			break;
		}
		mMyAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu paramMenu) {
		getMenuInflater().inflate(R.menu.main, paramMenu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
		switch (paramMenuItem.getItemId()) {
		case R.id.action_newhouse:
			Intent intent = new Intent();
			intent.setClass(this, NewHouseActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("HOUSE", new House());
			intent.putExtras(bundle);
			startActivityForResult(intent, NEW_HOUSE_CODE);
			return true;
		case R.id.action_item_allhouse:
			setFilter(HOUSE_TYPE.ALL_HOUSE);
			return true;
		case R.id.action_item_yzhouse:
			setFilter(HOUSE_TYPE.YZ_HOUSE);
			return true;
		case R.id.action_item_wzhouse:
			setFilter(HOUSE_TYPE.WZ_HOUSE);
			return true;
		case R.id.action_item_szhouse:
			setFilter(HOUSE_TYPE.SZ_HOUSE);
			return true;
		default:
			return super.onOptionsItemSelected(paramMenuItem);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		House house = mHoustList.get(arg2);
		Intent intent = new Intent();
		intent.setClass(this, HouseInfoActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("HOUSE", house);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (NEW_HOUSE_CODE == requestCode) {
			if (resultCode == Activity.RESULT_OK) {
				setFilter(HOUSE_TYPE.ALL_HOUSE);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private class MyAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public MyAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return mHoustList.size();
		}

		@Override
		public Object getItem(int i) {
			return null;
		}

		@Override
		public long getItemId(int i) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup viewGroup) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_imageview_title_subtitle, null, false);
				holder = new ViewHolder();
				holder.title = (TextView) convertView.findViewById(R.id.textViewTitle);
				holder.subTitle = (TextView) convertView.findViewById(R.id.textViewSubTitle);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			fillView(position, holder);
			return convertView;
		}

		private void fillView(int position, ViewHolder holder) {
			House house = mHoustList.get(position);
			holder.title.setText(house.getHouseInfo());
			if (Customer.NULL_CUSTOMER.equals(house.getCustomerId())) {
				holder.subTitle.setText("尚未出租，点击添加租客信息。");
			} else {
				long date = house.getRecentlyDay();
				if ((date == 6570L) || (date == 2920L)) {
					holder.subTitle.setText("没有未收账单");
					return;
				}
				if (date > 7L) {
					holder.subTitle.setText("●距离收租日还有" + date + "天");
					return;
				}
				if (date > 0L) {
					holder.subTitle.setText("●距离收租日还有" + date + "天");
					return;
				}
				if (date == 0L) {
					holder.subTitle.setText("●今日收租");
					return;
				}
				long l2 = 0L - date;
				holder.subTitle.setText("●收租日已过" + l2 + "天");
			}
		}
	}

	static class ViewHolder {
		TextView title;
		TextView subTitle;
	}
}
