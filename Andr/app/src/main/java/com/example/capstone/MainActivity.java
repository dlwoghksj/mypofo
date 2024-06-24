package com.example.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String userEmail; String userName; String joinDate; String userPw; String groupCode; String ottCode;
    String subscription; String ottName; String waitingOttCode; String waitingUserPaymentDate;
    String new_first;String waitingCount;String userPaymentDate;
    String startDate;String endDate;String ottID;String ottPW;String createDate;
    String creditName, creditNumber, creditCVC;

    ServerURL serverURL = new ServerURL();
    String page = serverURL.getPage() + "UserProfile.do";

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("DeviceToken : ", FirebaseMessaging.getInstance().getToken().toString());

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");
        userPw = intent.getStringExtra("userPw");
        userName = intent.getStringExtra("userName");
        joinDate = intent.getStringExtra("joinDate");
        creditName= intent.getStringExtra("creditName");
        creditNumber = intent.getStringExtra("creditNumber");
        creditCVC = intent.getStringExtra("creditCVC");

        Thread th=new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    URL url=new URL(page);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    //서버에 보낼 파라미터
                    String param="userEmail="+userEmail
                            +"&userPw="+userPw;
                    StringBuilder sb=new StringBuilder();
                    if(conn!=null){
                        conn.setConnectTimeout(10000);
                        conn.setRequestMethod("POST");
                        conn.setUseCaches(false);
                        //서버에 파라미터 전송, post 방식, 인코딩 방식 지정
                        conn.getOutputStream().write(
                                param.getBytes("utf-8"));
                        if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                            //서버의 응답 텍스트
                            BufferedReader br=
                                    new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                            while(true){
                                String line=br.readLine();
                                if(line==null) break;
                                sb.append(line+"\n");
                            }
                            br.close();
                        }
                        conn.disconnect();
                    }
                    //서버의 응답 텍스트를 json으로 변환
                    JSONObject jsonObj=new JSONObject(sb.toString());
                    Log.v("sb", sb.toString());
                    String map = jsonObj.getString("map");
                    JSONObject mapObj = new JSONObject(map);
                    Log.v("map", map);

                    String user = mapObj.getString("user");
                    JSONObject userObj = new JSONObject(user);
                    String waiting = mapObj.getString("waiting");
                    JSONObject waitingObj = new JSONObject(waiting);
                    Log.v("user", user);
                    Log.v("waiting", waiting);

                    userEmail = userObj.getString("userEmail");
                    userPw = userObj.getString("userPw");
                    userName = userObj.getString("userName");
                    joinDate = userObj.getString("joinDate");
                    creditName= userObj.optString("creditName", "no value");
                    creditNumber = userObj.optString("creditNumber", "no value");
                    creditCVC = userObj.optString("creditCVC", "no value");

                    waitingOttCode = waitingObj.optString("waitingOttCode", "no value");
                    waitingUserPaymentDate = waitingObj.optString("waitingUserPaymentDate", "no value");
                    new_first = waitingObj.optString("new_first", "no value");
                    waitingCount = waitingObj.optString("waitingCount", "no value");

                    groupCode = userObj.optString("groupCode", "no value");
                    ottCode = userObj.optString("ottCode", "no value");
                    ottName = userObj.optString("ottName", "no value");
                    subscription = userObj.optString("subscription", "no value");
                    userPaymentDate = userObj.optString("userPaymentDate", "no value");
                    startDate = userObj.optString("startDate", "no value");
                    endDate = userObj.optString("endDate", "no value");
                    ottID = userObj.optString("ottID", "no value");
                    ottPW = userObj.optString("ottPW", "no value");
                    createDate = userObj.optString("createDate", "no value");


                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        th.start(); //백그라운드 스레드 시작





        //OTT 이미지뷰 클릭----------------------------------------------------------------------------
        ImageView iv_netflix = findViewById(R.id.iv_netflix);
        ImageView iv_wavve= findViewById(R.id.iv_wavve);
        ImageView iv_disney = findViewById(R.id.iv_disney);
        ImageView iv_watcha = findViewById(R.id.iv_watcha);



        iv_netflix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OttDetailActivity.class);
                intent.putExtra("ottDetailCode", "1");
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
                intent.putExtra("creditName", creditName);
                intent.putExtra("creditNumber", creditNumber);
                intent.putExtra("creditCVC", creditCVC);

                startActivity(intent);
            }
        });

        iv_wavve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OttDetailActivity.class);
                intent.putExtra("ottDetailCode", "2");
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
                intent.putExtra("creditName", creditName);
                intent.putExtra("creditNumber", creditNumber);
                intent.putExtra("creditCVC", creditCVC);
                startActivity(intent);
            }
        });

        iv_disney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OttDetailActivity.class);
                intent.putExtra("ottDetailCode", "3");
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
                intent.putExtra("creditName", creditName);
                intent.putExtra("creditNumber", creditNumber);
                intent.putExtra("creditCVC", creditCVC);
                startActivity(intent);
            }
        });

        iv_watcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OttDetailActivity.class);
                intent.putExtra("ottDetailCode", "4");
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
                intent.putExtra("creditName", creditName);
                intent.putExtra("creditNumber", creditNumber);
                intent.putExtra("creditCVC", creditCVC);
                startActivity(intent);
            }
        });

        //네비게이션 툴바 , 메뉴 설정-------------------------------------------------------------------
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 왼쪽 상단 버튼 만들기

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburg);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        //네비게이션 메뉴 클릭-------------------------------------------------------------------------
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_my_subs:
                        Intent intent = new Intent(MainActivity.this, MysubsListActivity.class);
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

                        intent.putExtra("creditName", creditName);
                        intent.putExtra("creditNumber", creditNumber);
                        intent.putExtra("creditCVC", creditCVC);

                        startActivity(intent);
                        return true;
                    case R.id.item_logout :

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                FirebaseAuth.getInstance().signOut();
                            }
                        }, 0);
                    case R.id.item_notice:
                        Intent intent3 = new Intent(MainActivity.this, NoticeListActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.item_account_setting :
                        Intent intent2 = new Intent(MainActivity.this, InfoChangeActivity.class);
                        intent2.putExtra("userEmail", userEmail);
                        intent2.putExtra("userPw", userPw);
                        intent2.putExtra("userName", userName);
                        intent2.putExtra("joinDate", joinDate);
                        intent2.putExtra("groupCode", groupCode);
                        intent2.putExtra("ottCode", ottCode);
                        intent2.putExtra("ottName", ottName);
                        intent2.putExtra("subscription", subscription);
                        intent2.putExtra("userPaymentDate", userPaymentDate);
                        intent2.putExtra("startDate", startDate);
                        intent2.putExtra("endDate", endDate);
                        intent2.putExtra("ottID", ottID);
                        intent2.putExtra("ottPW", ottPW);
                        intent2.putExtra("createDate", createDate);

                        intent2.putExtra("waitingOttCode", waitingOttCode);
                        intent2.putExtra("waitingUserPaymentDate", waitingUserPaymentDate);
                        intent2.putExtra("new_first", new_first);
                        intent2.putExtra("waitingCount", waitingCount);

                        intent2.putExtra("creditName", creditName);
                        intent2.putExtra("creditNumber", creditNumber);
                        intent2.putExtra("creditCVC", creditCVC);

                        startActivity(intent2);
                        return true;
                        
                }
                return false;
            }
        });

        View header = navigationView.getHeaderView(0);

        TextView tv_navi_userName = header.findViewById(R.id.tv_navi_userName);
        tv_navi_userName.setText(userName);



    }
    //네비게이션 메뉴 버튼 클릭----------\--------------------------------------------------------------
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}