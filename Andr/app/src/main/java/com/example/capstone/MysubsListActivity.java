package com.example.capstone;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MysubsListActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<MysubsItem> data = null;
    String userEmail; String userName; String joinDate; String userPw; String groupCode; String ottCode;
    String subscription; String ottName; String waitingOttCode; String waitingUserPaymentDate;
    String new_first;String waitingCount;String userPaymentDate;
    String startDate;String endDate;String ottID;String ottPW;String createDate;
    String creditName, creditNumber, creditCVC;

    private FragmentManager fragmentManager;
    private FragmentMysubs fragment_mysubs;
    private FragmentWaiting fragment_waiting;
    private FragmentTransaction transaction;

    private Button btn_1;
    private Button btn_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysubs);

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");
        userName = intent.getStringExtra("userName");
        joinDate = intent.getStringExtra("joinDate");
        userPw = intent.getStringExtra("userPw");
        groupCode = intent.getStringExtra("groupCode");
        ottCode = intent.getStringExtra("ottCode");
        subscription = intent.getStringExtra("subscription");
        ottName = intent.getStringExtra("ottName");
        waitingOttCode = intent.getStringExtra("waitingOttCode");
        waitingUserPaymentDate = intent.getStringExtra("waitingUserPaymentDate");
        new_first = intent.getStringExtra("new_first");
        waitingCount = intent.getStringExtra("waitingCount");
        userPaymentDate = intent.getStringExtra("userPaymentDate");
        startDate = intent.getStringExtra("startDate");
        endDate = intent.getStringExtra("endDate");
        ottID = intent.getStringExtra("ottID");
        ottPW = intent.getStringExtra("ottPW");
        createDate = intent.getStringExtra("createDate");
        creditName= intent.getStringExtra("creditName");
        creditNumber = intent.getStringExtra("creditNumber");
        creditCVC = intent.getStringExtra("creditCVC");

        fragmentManager = getSupportFragmentManager();
        fragment_mysubs = new FragmentMysubs();
        fragment_waiting = new FragmentWaiting();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment_mysubs).commitAllowingStateLoss();

        btn_1 = (Button) findViewById(R.id.btn_subs);
        btn_2 = (Button) findViewById(R.id.btn_waiting);


        Bundle bundle = new Bundle();

        bundle.putString("userEmail", userEmail);
        bundle.putString("userPw", userPw);
        bundle.putString("userName", userName);
        bundle.putString("joinDate", joinDate);
        bundle.putString("groupCode", groupCode);
        bundle.putString("ottCode", ottCode);
        bundle.putString("ottName", ottName);
        bundle.putString("subscription", subscription);
        bundle.putString("userPaymentDate", userPaymentDate);
        bundle.putString("startDate", startDate);
        bundle.putString("endDate", endDate);
        bundle.putString("ottID", ottID);
        bundle.putString("ottPW", ottPW);
        bundle.putString("createDate", createDate);

        bundle.putString("waitingOttCode", waitingOttCode);
        bundle.putString("waitingUserPaymentDate", waitingUserPaymentDate);
        bundle.putString("new_first", new_first);
        bundle.putString("waitingCount", waitingCount);

        fragment_mysubs.setArguments(bundle);
        fragment_waiting.setArguments(bundle);


        //뒤로가기 툴바 배치
        Toolbar toolbar = findViewById(R.id.backspace_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    //뒤로가기 툴바 눌렀을 때
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(MysubsListActivity.this, MainActivity.class);
                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userPw", userPw);
                intent.putExtra("userName", userName);
                intent.putExtra("joinDate", joinDate);
                intent.putExtra("groupCode", groupCode);
                intent.putExtra("ottCode", ottCode);
                intent.putExtra("ottName", ottName);
                intent.putExtra("subscription", subscription);
                intent.putExtra("userPaymentDate", userPaymentDate);
                intent.putExtra("startDate", startDate);
                intent.putExtra("endDate", endDate);
                intent.putExtra("ottID", ottID);
                intent.putExtra("ottPW", ottPW);
                intent.putExtra("createDate", createDate);

                intent.putExtra("waitingOttCode", waitingOttCode);
                intent.putExtra("waitingUserPaymentDate", waitingUserPaymentDate);
                intent.putExtra("new_first", new_first);
                intent.putExtra("waitingCount", waitingCount);

                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }


    public void clickHandler(View view)
    {
        transaction = fragmentManager.beginTransaction();


        switch(view.getId())
        {
            case R.id.btn_subs:
                transaction.replace(R.id.frameLayout, fragment_mysubs).commitAllowingStateLoss();
                btn_1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#252525")));
                btn_2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#686868")));

                break;
            case R.id.btn_waiting:
                transaction.replace(R.id.frameLayout, fragment_waiting).commitAllowingStateLoss();
                btn_2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#252525")));
                btn_1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#686868")));

                break;
        }
    }

}
