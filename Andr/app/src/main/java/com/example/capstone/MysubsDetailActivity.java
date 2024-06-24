package com.example.capstone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MysubsDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private int img;
    Button btn_cancel;
    String userEmail; String userName; String joinDate; String userPw; String groupCode; String ottCode;
    String subscription; String ottName; String waitingOttCode; String waitingUserPaymentDate;
    String new_first;String waitingCount;String userPaymentDate;
    String startDate;String endDate;String ottID;String ottPW;String createDate;

    ServerURL serverURL = new ServerURL();
    String page = serverURL.getPage() + "OttCancel.do";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysubs_detail);

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


        boolean waiting = intent.getBooleanExtra("waiting", false);

        ImageView subsicon = (ImageView) findViewById(R.id.iv_subs_detail_icon);
        TextView subsname=(TextView) findViewById(R.id.tv_subs_detail_name);
        TextView subsdate=(TextView) findViewById(R.id.tv_subs_detail_date);
        TextView subscreatedate = (TextView) findViewById(R.id.tv_subs_detail_createdate);
        TextView subsID = (TextView) findViewById(R.id.tv_subs_detail_id);
        TextView subsPW = (TextView) findViewById(R.id.tv_subs_detail_pw);
        TextView subs_subscription = (TextView) findViewById(R.id.tv_subscription);


        img=Integer.parseInt(intent.getStringExtra("subsicon"));
        subsicon.setImageResource(img);
        subsname.setText("OTT : " + intent.getStringExtra("subsname"));

        if(waiting == true){
            subsdate.setText("결제 날짜 : " + intent.getStringExtra("waitingUserPaymentDate"));
            subscreatedate.setText("그룹 " + intent.getStringExtra("subsdate"));
            subsID.setText(intent.getStringExtra("new_first"));
            subsPW.setText("");
            subs_subscription.setText("");

        }else{
            subsdate.setText("이용 기간 : " + intent.getStringExtra("subsdate"));
            subscreatedate.setText("그룹 생성일 : " + intent.getStringExtra("createDate"));
            subsID.setText(" 계정 ID : " + intent.getStringExtra("ottID"));
            subsPW.setText(" 계정 PW : " + intent.getStringExtra("ottPW"));
            Log.v("subscription", subscription);
            if(subscription.equals("0")){
                subscription = "구독 취소 완료 (종료 기간까지 사용 가능)";
            }else if(subscription.equals("1")){
                subscription = "구독 중 ";
            }
            subs_subscription.setText(subscription);
        }

        //뒤로가기 툴바 배치
        Toolbar toolbar = findViewById(R.id.backspace_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MysubsDetailActivity.this);
                builder.setTitle("구독 취소").setMessage("구독을 취소하시겠습니까?");
                builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Thread th=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    URL url=new URL(page);
                                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                                    //서버에 보낼 파라미터

                                    String param="userEmail="+userEmail
                                            +"&userPw="+userPw
                                            +"&groupCode=" + groupCode
                                            + "&waitingOttCode=" + waitingOttCode;
                                    Log.v("parameter", param);
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
                                    String result = mapObj.getString("result");

                                    if(result == "true"){
                                        String user = mapObj.getString("user");
                                        String waiting = mapObj.getString("waiting");
                                        JSONObject userObj = new JSONObject(user);
                                        JSONObject waitingObj = new JSONObject(waiting);


                                        Log.v("user", user);
                                        Log.v("waiting", waiting);
                                        userEmail = userObj.getString("userEmail");
                                        userPw = userObj.getString("userPw");
                                        userName = userObj.getString("userName");
                                        joinDate = userObj.getString("joinDate");

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




                                        Intent intent = new Intent(MysubsDetailActivity.this, MysubsListActivity.class);
                                        intent.putExtra("userEmail", userEmail);
                                        intent.putExtra("userName", userName);
                                        intent.putExtra("userPw", userPw);
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

                                        Handler handler = new Handler(Looper.getMainLooper());
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run()
                                            {
                                                Toast.makeText(getApplicationContext(), "구독 취소가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                                startActivity(intent);
                                            }
                                        }, 0);


                                    }else{
                                        //Toast.makeText(getApplicationContext() ,"로그인 실패", Toast.LENGTH_SHORT).show();
                                        Handler handler = new Handler(Looper.getMainLooper());
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run()
                                            {
                                                Toast.makeText(getApplicationContext(), "Error. ", Toast.LENGTH_SHORT).show();
                                            }
                                        }, 0);
                                        return;

                                    }

                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        th.start(); //백그라운드 스레드 시작

                    }
                });
                AlertDialog alertID = builder.create();
                alertID.show();
            }
        });

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

    @Override
    public void onClick(View v) {

    }
}
