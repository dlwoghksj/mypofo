package com.example.capstone;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    TextView tv_signup;
    EditText edit_login_email, edit_login_pw;
    Button btn_login;
    String userEmail; String userPw; String userName; String joinDate;
    String creditName, creditNumber, creditCVC;

    ServerURL serverURL = new ServerURL();
    String page = serverURL.getPage() + "UserLogin.do";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        edit_login_email=findViewById(R.id.edit_login_email);
        edit_login_pw=findViewById(R.id.edit_login_pw);


        tv_signup = findViewById(R.id.tv_signup);
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = edit_login_email.getText().toString();
                userPw = edit_login_pw.getText().toString();
                Log.v("user", userEmail + "," + userPw);
                if(userEmail.equals("") && userPw.equals("")){
                    Toast.makeText(LoginActivity.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                }else{
                    loginUser(userEmail, userPw);
                }
            }
        });
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    userEmail = user.getEmail();
                    Thread th=new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try{
                                URL url=new URL(page);
                                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                                //서버에 보낼 파라미터
                                String param="userEmail="+userEmail + "&userPw=" + userPw;
                                Log.v("param", param);
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
                                    JSONObject userObj = new JSONObject(user);
                                    Log.v("user", user);

                                    userEmail = userObj.getString("userEmail");
                                    userPw = userObj.getString("userPw");
                                    userName = userObj.getString("userName");
                                    joinDate = userObj.getString("joinDate");
                                    creditName= userObj.optString("creditName", "no value");
                                    creditNumber = userObj.optString("creditNumber", "no value");
                                    creditCVC = userObj.optString("creditCVC", "no value");


                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("userEmail", userEmail);
                                    intent.putExtra("userName", userName);
                                    intent.putExtra("userPw", userPw);
                                    intent.putExtra("joinDate", joinDate);
                                    intent.putExtra("creditName", creditName);
                                    intent.putExtra("creditNumber", creditNumber);
                                    intent.putExtra("creditCVC", creditCVC);


                                    startActivity(intent);
                                }

                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                    th.start(); //백그라운드 스레드 시작
                }
            }
        };

    }

    private void loginUser(String email, String pw) {
        firebaseAuth.signInWithEmailAndPassword(email, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //로그인 성공
                            firebaseAuth.addAuthStateListener(firebaseAuthListener);
                            Toast.makeText(LoginActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            firebaseAuth.addAuthStateListener(firebaseAuthListener);
            Toast.makeText(LoginActivity.this, "자동 로그인 되었습니다.", Toast.LENGTH_LONG).show();
        }

    }


}