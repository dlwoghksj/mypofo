package com.example.capstone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MysubsAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<MysubsItem> data; //Item 목록을 담을 배열
    private int layout;
    public MysubsAdapter(Context context,int layout, ArrayList<MysubsItem> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getIv_subs_icon();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }
        MysubsItem subsItem = data.get(position);

        ImageView iv_subs_icon = (ImageView) convertView.findViewById(R.id.iv_subs_icon);
        iv_subs_icon.setImageResource(subsItem.getIv_subs_icon());

        TextView tv_subs_name = (TextView) convertView.findViewById(R.id.tv_subs_name);
        tv_subs_name.setText(subsItem.getTv_subs_name());

        TextView tv_subs_date = (TextView) convertView.findViewById(R.id.tv_subs_date);
        tv_subs_date.setText(subsItem.getTv_subs_date());


        return convertView;
    }
}
