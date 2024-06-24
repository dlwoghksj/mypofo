package com.example.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InfoChangeActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;


    EditText et_infochange_pw1;
    EditText et_infochange_pw2;

    Spinner sp_infochange;
    EditText et_infochange_card;
    EditText et_infochange_cvc;
    Button btn_pwch_detail;

    String userEmail, userName, joinDate, userPw;
    String groupCode; String ottCode;
    String subscription; String ottName; String waitingOttCode; String waitingUserPaymentDate;
    String new_first;String waitingCount;String userPaymentDate;
    String startDate;String endDate;String ottID;String ottPW;String createDate;
    String creditName, creditNumber, creditCVC;

    ServerURL serverURL = new ServerURL();
    String page = serverURL.getPage() + "UserUpdate.do";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infochange);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Log.v("firebase user ------", user.toString()) ;
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("userEmail");
        userName = intent.getStringExtra("userName");
        joinDate = intent.getStringExtra("joinDate");
        userPw = intent.getStringExtra("userPw");

        creditName= intent.getStringExtra("creditName");
        creditNumber = intent.getStringExtra("creditNumber");
        creditCVC = intent.getStringExtra("creditCVC");

        Log.v("--------credit info", creditName + ","+ creditNumber + "," + creditCVC);

        et_infochange_pw1 = findViewById(R.id.et_infochange_pw1);
        et_infochange_pw2 = findViewById(R.id.et_infochange_pw2);

        sp_infochange = findViewById(R.id.sp_infochange);
        et_infochange_card = findViewById(R.id.et_infochange_card);
        et_infochange_cvc = findViewById(R.id.et_infochange_cvc);

        if(!creditNumber.equals("no value")){
            et_infochange_card.setText(creditNumber);
            et_infochange_cvc.setText(creditCVC);
        }



        //뒤로가기 툴바 배치
        Toolbar toolbar = findViewById(R.id.backspace_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayAdapter Adapter = ArrayAdapter.createFromResource(this, R.array.cardcompany, android.R.layout.simple_spinner_dropdown_item);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_infochange.setAdapter(Adapter); //어댑터에 연결해줍니다.

        int spinnerPosition ;
        if(creditName.equals("BC card")){
            spinnerPosition = Adapter.getPosition("BC card" );
            sp_infochange.setSelection(spinnerPosition);
        }else if(creditName.equals("KB card")){
            spinnerPosition = Adapter.getPosition("KB card" );
            sp_infochange.setSelection(spinnerPosition);
        }else{
            spinnerPosition = Adapter.getPosition("HYUNDAI card" );
            sp_infochange.setSelection(spinnerPosition);
        }

        sp_infochange.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    creditName = "HYUNDAI card";
                } else if(position == 1){
                    creditName = "BC card";
                } else if(position == 2){
                    creditName = "KB card";
                }
            } //이 오버라이드 메소드에서 position은 몇번째 값이 클릭됐는지 알 수 있습니다.
            //getItemAtPosition(position)를 통해서 해당 값을 받아올수있습니다.

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        btn_pwch_detail = findViewById(R.id.btn_pwch_detail);
        btn_pwch_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creditNumber = et_infochange_card.getText().toString();
                creditCVC = et_infochange_cvc.getText().toString();
                Log.v("-------credit info", creditName + ","+ creditNumber + "," + creditCVC);
                String pw1 = et_infochange_pw1.getText().toString();
                String pw2 = et_infochange_pw2.getText().toString();
                if(pw1.equals("") && pw2.equals("")){
                    Toast.makeText(InfoChangeActivity.this, "비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }else if(!pw1.equals(pw2)){
                    Toast.makeText(InfoChangeActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }else{
                    userPw = pw1;
                    changePassword(userPw);
                    if(creditNumber.equals("")){
                        creditName = "";
                        creditNumber = "";
                        creditCVC = "";
                    }
                    Thread th=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                URL url = new URL(page);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                //서버에 보낼 파라미터
                                String param = "userEmail=" + userEmail + "&userPw=" + userPw
                                        + "&creditName=" + creditName
                                        + "&creditNumber=" + creditNumber
                                        + "&creditCVC=" + creditCVC;
                                Log.v("param", param);
                                StringBuilder sb = new StringBuilder();
                                if (conn != null) {
                                    conn.setConnectTimeout(10000);
                                    conn.setRequestMethod("POST");
                                    conn.setUseCaches(false);
                                    //서버에 파라미터 전송, post 방식, 인코딩 방식 지정
                                    conn.getOutputStream().write(
                                            param.getBytes("utf-8"));
                                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                        //서버의 응답 텍스트
                                        BufferedReader br =
                                                new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                                        while (true) {
                                            String line = br.readLine();
                                            if (line == null) break;
                                            sb.append(line + "\n");
                                        }
                                        br.close();
                                    }
                                    conn.disconnect();
                                }
                                //서버의 응답 텍스트를 json으로 변환
                                JSONObject jsonObj = new JSONObject(sb.toString());
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

                                Intent intent = new Intent(InfoChangeActivity.this, MainActivity.class);
                                intent.putExtra("userEmail", userEmail);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userPw", userPw);
                                intent.putExtra("joinDate", joinDate);
                                intent.putExtra("creditName", creditName);
                                intent.putExtra("creditNumber", creditNumber);
                                intent.putExtra("creditCVC", creditCVC);


                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run()
                                    {
                                        Toast.makeText(getApplicationContext(), "계정 설정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                    }
                                }, 0);



                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                    th.start(); //백그라운드 스레드 시작
                }
            }
        });


    }
    private void changePassword(String pw) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Log.v("------user firebaseAuth", user.toString());
        firebaseAuth.getCurrentUser().updatePassword(pw)
               .addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {

                   }
               })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       e.printStackTrace();
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
}
