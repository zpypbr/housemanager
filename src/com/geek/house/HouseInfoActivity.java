package com.geek.house;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HouseInfoActivity extends Activity {
	private ListView listview;
	private Menu optionMenu;

	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_house_info);
	}

	private class MyAdapter extends BaseAdapter {
		private MyAdapter() {
		}

		@Override
		public int getCount() {
			return 0;
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
			View localView;
			ImageView localImageView1;
			ImageView localImageView2;
			TextView localTextView1;
			TextView localTextView2;
			if (position == 0) {
				localView = LayoutInflater.from(HouseInfoActivity.this.getApplicationContext())
						.inflate(R.layout.item_imageview_title_subtitle, null);
				((ImageView) localView.findViewById(R.id.imgHeader))
						.setImageResource(R.drawable.house_yellow);
				((TextView) localView.findViewById(R.id.textViewTitle)).setText("");
				localTextView1 = (TextView) localView.findViewById(R.id.textViewSubTitle);
				if (true) {
					localTextView1.setText("点击查看账单");
					localTextView1.setTextColor(HouseInfoActivity.this.getResources().getColor(
							R.color.Orange));
					return localView;
				}
			}
			if (position == 2) {
				localView = LayoutInflater.from(HouseInfoActivity.this.getApplicationContext())
						.inflate(R.layout.item_customer_phone, null);
				localImageView1 = (ImageView) localView.findViewById(R.id.imgHeader);
				localTextView1 = (TextView) localView.findViewById(R.id.textViewCustomerName);
				localTextView2 = (TextView) localView.findViewById(R.id.textViewPhone);
				localImageView2 = (ImageView) localView.findViewById(R.id.imageViewPhone);
				if (true) {
					localImageView1.setImageResource(2130837602);
					localTextView1.setText("");
					localTextView2.setText("");
					localImageView2.setVisibility(0);
				} else {
					localImageView1.setImageResource(2130837630);
					localTextView1.setText("尚未出租");
					localTextView2.setText("点击添加租客信息");
					localImageView2.setVisibility(4);
				}
				return localView;
			}
			localTextView1.setText("押金");
			localTextView2.setText("¥");
			return localView;
			localTextView1.setText("租金");
			localTextView2.setText("¥");
			return localView;
			localTextView1.setText("起租日期");
			localTextView2.setText("");
			return localView;
			localTextView1.setText("交租周期");
			localTextView2.setText("");
			return localView;
			localTextView1.setText("合约到期日");
			localTextView2.setText("");
			return localView;
		}
	}
}
