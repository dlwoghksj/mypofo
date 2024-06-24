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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OttDetailActivity extends AppCompatActivity {
    String userEmail; String userName; String joinDate; String userPw; String groupCode; String ottCode;
    String subscription; String ottName; String waitingOttCode; String waitingUserPaymentDate;
    String new_first;String waitingCount;String userPaymentDate;
    String startDate;String endDate;String ottID;String ottPW;String createDate;
    String creditName, creditNumber, creditCVC;

    private DatabaseReference mDatabase;

    ServerURL serverURL = new ServerURL();
    String page = serverURL.getPage() + "OttSubscription.do";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ott_detail);

        Intent intent = getIntent();

        String ottDetailCode = intent.getStringExtra("ottDetailCode");
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

        creditName = intent.getStringExtra("creditName");
        creditNumber = intent.getStringExtra("creditNumber");
        creditCVC = intent.getStringExtra("creditCVC");

        ImageView icon = (ImageView) findViewById(R.id.iv_ott);
        TextView price=(TextView) findViewById(R.id.tv_price);
        TextView info = (TextView) findViewById(R.id.tv_ottInfo);
        ImageView pay = (ImageView) findViewById(R.id.iv_pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OttDetailActivity.this);
                builder.setTitle("구독").setMessage("구독하시겠습니까?");
                builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(creditNumber.equals("")){
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run()
                                {
                                    Toast.makeText(getApplicationContext(), "카드 정보를 입력한 후 구독이 가능합니다. (계정 설정->카드정보 입력)", Toast.LENGTH_LONG).show();
                                }
                            }, 0);
                            return;
                        }else{
                            Thread th=new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        URL url=new URL(page);
                                        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                                        //서버에 보낼 파라미터

                                        String param="userEmail="+ userEmail + "&userPw=" + userPw
                                                + "&waitingOttCode=" + ottDetailCode +"&ottCode=" + ottDetailCode;
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

                                        if(result.equals("0")){
                                            Handler handler = new Handler(Looper.getMainLooper());
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run()
                                                {
                                                    Toast.makeText(getApplicationContext(), "현재 구독중인 서비스입니다.", Toast.LENGTH_SHORT).show();
                                                }
                                            }, 0);
                                            return;


                                        }else if(result.equals("1")){

                                            String user = mapObj.getString("user");
                                            String waiting = mapObj.getString("waiting");
                                            JSONObject userObj = new JSONObject(user);
                                            JSONObject waitingObj = new JSONObject(waiting);

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
                                            creditName= userObj.optString("creditName", "no value");
                                            creditNumber = userObj.optString("creditNumber", "no value");
                                            creditCVC = userObj.optString("creditCVC", "no value");


                                            mDatabase = FirebaseDatabase.getInstance().getReference();
                                            Group group = new Group(groupCode, waitingOttCode, waitingCount);
                                            mDatabase.child("group").setValue(group);



                                            Intent intent = new Intent(OttDetailActivity.this, MainActivity.class);
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
                                            intent.putExtra("creditName", creditName);
                                            intent.putExtra("creditNumber", creditNumber);
                                            intent.putExtra("creditCVC", creditCVC);

                                            Handler handler = new Handler(Looper.getMainLooper());
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run()
                                                {
                                                    Toast.makeText(getApplicationContext(), "구독이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                                    startActivity(intent);
                                                }
                                            }, 0);


                                        }else{
                                            Handler handler = new Handler(Looper.getMainLooper());
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run()
                                                {
                                                    Toast.makeText(getApplicationContext(), "현재 대기중인 서비스입니다.", Toast.LENGTH_SHORT).show();
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
                    }
                });
                AlertDialog alertID = builder.create();
                alertID.show();

            }
        });

        if(ottDetailCode.equals("1")){
            icon.setImageResource(R.drawable.netflix);
            price.setText("5000￦ / month");
            info.setText("넷플릭스는 TV 프로그램과 영화를 인터넷 연결 지원 디바이스에서 광고 없이 시청할 수 있는 멤버십 기반 스트리밍 서비스입니다. \n\n\n또한 iOS, Android 또는 Windows 10 디바이스에서 \nTV 프로그램과 영화를 저장하여 인터넷 연결 없이 시청할 수도 있습니다.");
        }
        else if(ottDetailCode.equals("2")){
            icon.setImageResource(R.drawable.wavve);
            price.setText("4000￦ / month");
            info.setText("웨이브에서 드라마, 예능, 웨이브 오리지널,\n 해외 시리즈까지 무제한 감상하세요! \n\n핫한 기대작부터 고전 명작까지 웨이브에서 \n무한 정주행!");
        }
        else if(ottDetailCode.equals("3")){
            icon.setImageResource(R.drawable.disney);
            price.setText("3000￦ / month");
            info.setText("디즈니, 픽사, 마블, 스타워즈, 내셔널지오그래픽, Star\n 콘텐츠를 다 함께 즐겨보세요.\n\n 인기 영화, 몰아보기 시리즈, 디즈니+와 Star에서\n 독점 공개하는 오리지널까지, 원하는 만큼 볼 수 있습니다.\n\n 완결 시리즈는 물론 오리지널 신작과 최신 히트작도 \n언제든지 시청하세요.");
        }
        else if(ottDetailCode.equals("4")){
            icon.setImageResource(R.drawable.watcha);
            price.setText("4000￦ / month");
            info.setText("영화, 드라마, 예능, 다큐멘터리를 무제한으로 즐기세요.\n\n이제 TV로 최고의 화질을 경험하세요.\n최대 Ultra HD 4K 해상도로 생생한 감동을 느껴보세요.\n\n 이동 중에도 감상을 멈추지 마세요.\n보고 싶은 콘텐츠를 다운로드하여 오프라인으로 즐기세요.");
        }


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
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
