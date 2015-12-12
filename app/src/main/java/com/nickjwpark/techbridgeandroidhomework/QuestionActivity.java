package com.nickjwpark.techbridgeandroidhomework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class QuestionActivity extends AppCompatActivity {

    TextView textNumber;
    TextView textQuestion;
    static TextView textLog;
    TextView textInput;
    TextView textReturn;
    static ScrollView scrollLog;
    Button btnRun;
    Button btnSubmit;
    int ch;
    int num;
    String question="";
    String input_type="";
    String return_type="";
    String input_sample="";
    String return_sample="";
    String log="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        textNumber = (TextView) findViewById(R.id.textNumber);
        textQuestion = (TextView) findViewById(R.id.textQuestion);
        textLog = (TextView) findViewById(R.id.textLog);
        scrollLog = (ScrollView) findViewById(R.id.scrollLog);
        textInput = (TextView) findViewById(R.id.textInput);
        textReturn = (TextView) findViewById(R.id.textReturn);
        btnRun = (Button) findViewById(R.id.btnRun);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String question = extras.getString("question");
            textNumber.setText(question);
            question = question.substring(9);
            Log.d("techbridge_android",question);
            int pos = question.indexOf(".");
            ch = Integer.parseInt(question.substring(0,pos));
            num = Integer.parseInt(question.substring(pos+1));

            if(isOnline()){
                new questionTask().execute(new DB_Question());
            } else {
                Toast.makeText(getApplicationContext(), "네트워크 연결을 확인해주세요.",Toast.LENGTH_LONG).show();
            }
        }

        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()){
                    println("Running...");

                    Homework.ch1_1(5,7);

                }else{
                    Toast.makeText(getApplicationContext(), "네트워크 연결을 확인해주세요.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void settextToAdapter(String response) {
        if(response.equals("/%//%//%//%/")){
//            Log.d("techbridge_android", "jsonEmpty");
//            Toast.makeText(getApplicationContext(), "문제가 아직 없네요!",Toast.LENGTH_LONG).show();
        }else {
            Log.d("techbridge_android", response);
            int pos = response.indexOf("/%/");
            question = response.substring(0,pos);
            response = response.substring(pos+3);
            pos = response.indexOf("/%/");
            input_type = response.substring(0,pos);
            response = response.substring(pos+3);
            pos = response.indexOf("/%/");
            return_type = response.substring(0,pos);
            response = response.substring(pos+3);
            pos = response.indexOf("/%/");
            input_sample = response.substring(0,pos);
            response = response.substring(pos+3);
            return_sample = response;

            textQuestion.setText(question);
            textInput.setText("inputs: "+input_type);
            textReturn.setText("returns: "+return_type);
            btnRun.setEnabled(true);
            btnSubmit.setEnabled(true);
//            println("HELLO");
        }
    }

    public static void println(String str){
        String txt = (String) QuestionActivity.textLog.getText();
        txt+="\n";
        QuestionActivity.textLog.setText(txt+str);
        scrollLog.scrollTo(0, scrollLog.getBottom());
    }

    private class questionTask extends AsyncTask<DB_Question, Long, String> {

        @Override
        protected String doInBackground(DB_Question... params) {
            //it is executed on Background thread
            return params[0].GetQuestion(ch, num);
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

