package com.nickjwpark.techbridgeandroidhomework;

import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class RegisterActivity extends AppCompatActivity {

    EditText textNameRegister;
    EditText textEmailRegister;
    EditText textPasswordRegister;
    EditText textPasswordCheckRegister;
    EditText textClassRegister;
    Button btnRegisterSend;

    String email;
    String password;
    String passwordCheck;
    String name;
    String classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textNameRegister = (EditText) findViewById(R.id.textNameRegister);
        textEmailRegister = (EditText) findViewById(R.id.textEmailRegister);
        textPasswordRegister = (EditText) findViewById(R.id.textPasswordRegister);
        textPasswordCheckRegister = (EditText) findViewById(R.id.textPasswordCheckRegister);
        textClassRegister = (EditText) findViewById(R.id.textClassRegister);
        btnRegisterSend = (Button) findViewById(R.id.btnRegisterSend);

        btnRegisterSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name = textNameRegister.getText().toString();
                email = textEmailRegister.getText().toString();
                password = textPasswordRegister.getText().toString();
                passwordCheck = textPasswordCheckRegister.getText().toString();
                classId = textClassRegister.getText().toString();

                if(name!= null && !name.isEmpty() && email!= null && !email.isEmpty() && password!= null && !password.isEmpty() && passwordCheck!= null && !passwordCheck.isEmpty() && classId!= null && !classId.isEmpty()){
                    if(isOnline()){
                        if(password.equals(passwordCheck)){
                            //first hash password here
                            try {
                                password = PasswordHash.createHash(password);
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (InvalidKeySpecException e) {
                                e.printStackTrace();
                            }
                            new registerTask().execute(new DB_Register());
                        } else {
                            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "네트워크 연결을 확인해주세요.",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "모든 칸을 채워주세요.",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
    public void settextToAdapter(String response) {

        String s = "";

        if(Integer.parseInt(response)==0){
            Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다!",Toast.LENGTH_LONG).show();
            finish();
        } else if(Integer.parseInt(response)==1){
            Toast.makeText(getApplicationContext(), "이미 쓰이고있는 이메일입니다. 새로운 이메일을 쓰세요.",Toast.LENGTH_LONG).show();
        }
    }

    private class registerTask extends AsyncTask<DB_Register, Long, String> {

        @Override
        protected String doInBackground(DB_Register... params) {

            //it is executed on Background thread

            return params[0].TryRegister(name,email,password,classId);

        }

        @Override
        protected void onPostExecute(final String response) {

            settextToAdapter(response);

        }
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
