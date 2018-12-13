package com.lorien.linkagescroll.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lorien.linkagescroll.R;

import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private List<String> list;

    public void setData(List<String> datas) {
        this.list = datas;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        TextView tv = item.findViewById(R.id.text);
        tv.setText((String) getItem(position));
        return item;
    }
}
