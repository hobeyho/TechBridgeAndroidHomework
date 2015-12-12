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
    int score;
    int outof;
    int id;
    String question="";
    String input_type="";
    String return_type="";
    String input_sample="";
    String return_sample="";
    String input_type_short="";
    String return_type_short="";
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
            id = extras.getInt("id");

            int pos = question.indexOf("_");
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
                    println("--------Running--------");
                    runMethod();
                }else{
                    Toast.makeText(getApplicationContext(), "네트워크 연결을 확인해주세요.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()){
                    println("Submitting...\n");
                    new submitTask().execute(new DB_Submit());
                }else{
                    Toast.makeText(getApplicationContext(), "네트워크 연결을 확인해주세요.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void settextToAdapter(String response) {
        if(response.equals("/%//%//%//%//%//%/")){

        }else {
            Log.d("techbridge_android", response);
//            println(response);
            String[] responseArr = response.split("/%/");
            question = responseArr[0];
            input_type = responseArr[1];
            return_type = responseArr[2];
            input_sample = responseArr[3];
            return_sample = responseArr[4];
            input_type_short = responseArr[5];
            return_type_short = responseArr[6];

            textQuestion.setText(question);
            textInput.setText("inputs: "+input_type);
            textReturn.setText("returns: "+return_type);
            btnRun.setEnabled(true);
            btnSubmit.setEnabled(true);
        }
    }

    public void runMethod() {
        String [] inputs = input_type_short.split(",");
        String [] sample_inputs = input_sample.split("/&&/");
        String [] sample_answers = return_sample.split("/&&/");
        score = 0;
        outof = sample_inputs.length;
        for(int i=0; i<sample_inputs.length; i++){
            println("input: " + Arrays.toString(sample_inputs[i].split("/&/")));
            forRunMethod(inputs, sample_inputs[i], sample_answers[i]);
        }
        println("점수: "+score+"/"+outof);
        println("");
    }
    public void forRunMethod(String [] inputs, String sample_inputs, String sample_answers){
        int idef = -9999;
        double ddef = -9999;
        String sdef = "";
        int [] aidef = {0};
        String [] asdef = {""};
        int ia = idef;
        int ib = idef;
        int ic = idef;
        double da = ddef;
        double db = ddef;
        double dc = ddef;
        String sa = sdef;
        String sb = sdef;
        String sc = sdef;
        int [] aia = aidef;
        int [] aib = aidef;
        int [] aic = aidef;
        String [] asa = asdef;
        String [] asb = asdef;
        String [] asc = asdef;
        String result ="";

        String [] vars = sample_inputs.split("/&/");

        for(int i=0; i<inputs.length; i++) {
            if (inputs[i].equals("i")) {
                if(ia==idef){
                    ia = Integer.parseInt(vars[i]);
                } else if(ib==idef){
                    ib = Integer.parseInt(vars[i]);
                } else if(ic==idef){
                    ic = Integer.parseInt(vars[i]);
                }
            } else if (inputs[i].equals("d")) {
                if(da==ddef) {
                    da = Double.parseDouble(vars[i]);
                } else if(db==idef) {
                    db = Double.parseDouble(vars[i]);
                } else if(dc==idef) {
                    dc = Double.parseDouble(vars[i]);
                }
            } else if (inputs[i].equals("s")) {
                if(sa.equals(sdef)){
                    sa = vars[i];
                } else if(sb.equals(sdef)){
                    sb = vars[i];
                } else if(sc.equals(sdef)){
                    sc = vars[i];
                }
            } else if (inputs[i].equals("ai")) {

            } else if (inputs[i].equals("as")) {

            }
        }

        if(ch==1){
            if(num==1){
                result = ""+Homework.ch1_1(ia, ib);
            } else if(num==2){

            } else if(num==3){

            } else if(num==4){

            } else if(num==5){

            } else if(num==6){

            } else if(num==7){

            } else if(num==8){

            } else if(num==9){

            } else if(num==10){

            } else if(num==11){

            } else if(num==12){

            } else if(num==13){

            } else if(num==14){

            } else if(num==15){

            }
        } else if(ch==2){

        } else if(ch==3){

        } else if(ch==4){

        } else if(ch==5){

        } else if(ch==6){

        }
        println("return: "+result);
        if(result.equals(sample_answers)){
            println("정답!");
            score++;
        } else {
            println("땡!");
        }
        println("");
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

    private class submitTask extends AsyncTask<DB_Submit, Long, String> {

        @Override
        protected String doInBackground(DB_Submit... params) {
            //it is executed on Background thread
            return params[0].TrySubmit(id, ch, num, score, outof);
        }

        @Override
        protected void onPostExecute(final String response) {
            settextToAdapter2(response);
        }
    }

    public void settextToAdapter2(String response) {
        if (response.equals(null)){
            println("문제가 있습니다");
        } else if(response.equals("updated successfully!")){
            println(response);
        } else if (response.equals("submitted successfully!")){
            println(response);
        } else {
            println("문제가 있습니다");
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

