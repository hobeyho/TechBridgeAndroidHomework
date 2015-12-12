package com.nickjwpark.techbridgeandroidhomework;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.app.Activity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class MainActivity extends AppCompatActivity {
    Button btnRegister;
    Button btnLogin;
    EditText textEmail;
    EditText textPassword;
    String email;
    String password;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        textEmail = (EditText) findViewById(R.id.textEmail);
        textPassword = (EditText) findViewById(R.id.textPassword);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = textEmail.getText().toString();
                password = textPassword.getText().toString();
                if (email!= null && !email.isEmpty() && password!= null && !password.isEmpty()) {
                    if(isOnline()){
                        new loginTask().execute(new DB_Login());
                    } else {
                        Toast.makeText(getApplicationContext(), "네트워크 연결을 확인해주세요.",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "모든 칸을 채워주세요.",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerActivity();
            }
        });
    }

    public void settextToAdapter(String response) {

        String s = "";

        if(response.equals("0")){
            Toast.makeText(getApplicationContext(), "이메일과 비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
        } else{
            boolean match = false;
            int pos = response.indexOf("pw:");
            id = Integer.parseInt(response.substring(0,pos));
            response = response.substring(pos+3);
            try {
                match = PasswordHash.validatePassword(password, response);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            if(match){
                Toast.makeText(getApplicationContext(), "로그인 성공!",Toast.LENGTH_LONG).show();
                homeworkActivity();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "이메일과 비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
            }
        }
    }

    private class loginTask extends AsyncTask<DB_Login, Long, String> {

        @Override
        protected String doInBackground(DB_Login... params) {
            //it is executed on Background thread
            return params[0].TryLogin(email);
        }

        @Override
        protected void onPostExecute(final String response) {
            settextToAdapter(response);
        }
    }

    public void homeworkActivity(){
        Activity fromActivity = this;
        Class toActivity = HomeworkActivity.class;
        Intent intent = new Intent(fromActivity,toActivity);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void registerActivity(){
        Activity fromActivity = this;
        Class toActivity = RegisterActivity.class;
        Intent intent = new Intent(fromActivity,toActivity);
        startActivity(intent);
    }


    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
