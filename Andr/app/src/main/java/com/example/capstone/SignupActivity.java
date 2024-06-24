package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    private EditText edit_signup_email;
    private EditText edit_signup_pw;
    private EditText edit_signup_name;
    private TextView textView_result;
    long now = System.currentTimeMillis();
    private FirebaseAuth firebaseAuth;

    ServerURL serverURL = new ServerURL();
    String page = serverURL.getPage() + "UserJoin.do";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        //뒤로가기 툴바 배치
        Toolbar toolbar = findViewById(R.id.backspace_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit_signup_email = (EditText)findViewById(R.id.edit_signup_email);
        edit_signup_pw = (EditText)findViewById(R.id.edit_signup_pw);
        edit_signup_name = (EditText)findViewById(R.id.edit_signup_name);

        textView_result = (TextView)findViewById(R.id.textView_result);

        textView_result.setMovementMethod(new ScrollingMovementMethod());


        Button btn_signup_submit = (Button)findViewById(R.id.btn_signup_submit);
        btn_signup_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userEmail = edit_signup_email.getText().toString();
                String userPw = edit_signup_pw.getText().toString();
                String userName = edit_signup_name.getText().toString();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String joinDate = sdf.format(date);

                Pattern pattern = android.util.Patterns.EMAIL_ADDRESS;

                if(!userEmail.equals("") && !userPw.equals("") && !userName.equals("")){
                    if(!pattern.matcher(userEmail).matches()){
                        Toast.makeText(SignupActivity.this, "이메일을 정확히 입력하세요.", Toast.LENGTH_SHORT).show();
                    }else if(!Pattern.matches("^[a-zA-Z0-9!@.#$%^&*?_~]{6,20}$", userPw)){
                        Toast.makeText(SignupActivity.this, "비밀번호는 6~20자리 이내로 입력하세요.", Toast.LENGTH_SHORT).show();
                    }else{
                        createUser(userEmail, userPw, userName);
                        Thread th=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    URL url=new URL(page);
                                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                                    //서버에 보낼 파라미터
                                    String param="userEmail="+edit_signup_email.getText().toString()
                                            +"&userPw="+edit_signup_pw.getText().toString()
                                            +"&userName="+edit_signup_name.getText().toString()
                                            +"&joinDate="+joinDate;
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

                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        th.start(); //백그라운드 스레드 시작
                    }
                    Log.v("user", userEmail + "," + userPw + "," + userName);
                }else if(userEmail.equals("")){
                    Toast.makeText(SignupActivity.this, "계정을 입력하세요.", Toast.LENGTH_SHORT).show();
                }else if(userPw.equals("")){
                    Toast.makeText(SignupActivity.this, "비밀번호를 입력하세요. ", Toast.LENGTH_SHORT).show();
                }else if(userName.equals("")){
                    Toast.makeText(SignupActivity.this, "이름을 입력하세요. ", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void createUser(String email, String pw, String name) {
        firebaseAuth.createUserWithEmailAndPassword(email, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(SignupActivity.this, "이미 존재하는 계정입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //뒤로가기 툴바 눌렀을 때
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
