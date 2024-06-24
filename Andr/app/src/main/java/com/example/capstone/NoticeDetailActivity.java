package com.example.capstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class NoticeDetailActivity extends AppCompatActivity {

    private int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* xml과 연결 */
        setContentView(R.layout.notice_detail);
        //뒤로가기 툴바 배치
        Toolbar toolbar = findViewById(R.id.backspace_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        ImageView iv_notice_detail_img = (ImageView) findViewById(R.id.iv_notice_detail_img);
        TextView tv_notice_detail_title=(TextView) findViewById(R.id.tv_notice_detail_title);
        TextView tv_notice_detail_date=(TextView) findViewById(R.id.tv_notice_detail_date);
        TextView tv_notice_detail_info=(TextView) findViewById(R.id.tv_notice_detail_info);

        img=Integer.parseInt(intent.getStringExtra("notice_img"));
        iv_notice_detail_img.setImageResource(img);
        tv_notice_detail_title.setText(intent.getStringExtra("notice_title"));
        tv_notice_detail_date.setText(intent.getStringExtra("notice_date"));
        tv_notice_detail_info.setText(intent.getStringExtra("notice_info"));





    }
    //뒤로가기 툴바 눌렀을 때
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}