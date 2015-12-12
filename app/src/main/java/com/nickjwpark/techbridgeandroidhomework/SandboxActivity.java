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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public class SandboxActivity extends AppCompatActivity {

    TextView textLog;
    ScrollView scrollLog;
    Button btnRun;


    public void runMethod() {
        println("Hello World!");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        textLog = (TextView) findViewById(R.id.textLog);
        scrollLog = (ScrollView) findViewById(R.id.scrollLog);
        btnRun = (Button) findViewById(R.id.btnRun);

        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                println("--------Running--------");
                runMethod();
            }
        });
    }
    public void println(String str) {
        String txt = (String) QuestionActivity.textLog.getText();
        txt += "\n";
        QuestionActivity.textLog.setText(txt + str);
        scrollLog.scrollTo(0, scrollLog.getBottom());
    }
}