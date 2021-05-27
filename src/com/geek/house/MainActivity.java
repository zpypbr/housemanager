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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.geek.house.com.geek.house.db.House;
import com.geek.house.com.geek.house.db.HouseDB;

import java.util.List;

public class MainActivity extends Activity {
    private ListView mHouseList;
    private MyAdapter mMyAdapter;
    private HouseDB mHouseDb;
    private enum HOUSE_TYPE {ALL_HOUSE, YZ_HOUSE, WZ_HOUSE, SZ_HOUSE};

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);
        mHouseDb = new HouseDB(this);
        mMyAdapter = new MyAdapter(this);
        mMyAdapter.setFilter(HOUSE_TYPE.WZ_HOUSE);
        mHouseList = (ListView) findViewById(R.id.listViewHouse);
        mHouseList.setAdapter(mMyAdapter);
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
                startActivity(new Intent(this, NewHouseActivity.class));
                return true;
            case R.id.action_item_allhouse:
                mMyAdapter.setFilter(HOUSE_TYPE.ALL_HOUSE);
                return true;
            case R.id.action_item_yzhouse:
                mMyAdapter.setFilter(HOUSE_TYPE.YZ_HOUSE);
                return true;
            case R.id.action_item_wzhouse:
                mMyAdapter.setFilter(HOUSE_TYPE.WZ_HOUSE);
                return true;
            case R.id.action_item_szhouse:
                mMyAdapter.setFilter(HOUSE_TYPE.SZ_HOUSE);
                return true;
            default:
                return super.onOptionsItemSelected(paramMenuItem);
        }
    }

    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<House> mHoustList;

        public MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        private void setFilter(HOUSE_TYPE type) {
            switch (type){
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
            } else{
                holder = (ViewHolder) convertView.getTag();
            }
            fillView(position, holder);
            return convertView;
        }

        private void fillView(int position, ViewHolder holder) {
            holder.title.setText(mHoustList.get(position).getName());
        }
    }

    static class ViewHolder {
        TextView title;
        TextView subTitle;
    }
}
