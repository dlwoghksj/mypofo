package com.example.capstone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class NoticeAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<NoticeItem> data; //Item 목록을 담을 배열
    private int layout;

    public NoticeAdapter(Context context, int layout, ArrayList<NoticeItem> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() { //리스트 안 Item의 개수를 센다.
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position).getTv_notice_list_title();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
        NoticeItem noticeItem = data.get(position);

        ImageView iv_notice_list_img = (ImageView) convertView.findViewById(R.id.iv_notice_list_img);
        iv_notice_list_img.setImageResource(noticeItem.getIv_notice_list_img());

        TextView tv_notice_list_title = (TextView) convertView.findViewById(R.id.tv_notice_list_title);
        tv_notice_list_title.setText(noticeItem.getTv_notice_list_title());

        TextView tv_notice_list_date = (TextView) convertView.findViewById(R.id.tv_notice_list_date);
        tv_notice_list_date.setText(noticeItem.getTv_notice_list_date());


        return convertView;
    }
}