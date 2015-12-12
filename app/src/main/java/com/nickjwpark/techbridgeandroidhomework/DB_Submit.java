package com.nickjwpark.techbridgeandroidhomework;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nick on 12/12/15.
 */
public class DB_Submit {
    public String TrySubmit(int id, int ch, int num, int score, int outof){
        String response = "";
        String question = ""+ch+"_"+num;
        try {
            URL url = new URL("http://techbridge.co.kr/android_submit.php?id="+id+"&question="+question+"&score="+score+"&outof="+outof);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.d("json", conn.toString());
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            response = responseStrBuilder.toString();
        } catch (IOException e) {
            Log.d("techbridge_android",e.toString());
            e.printStackTrace();
        }
//        Log.d("techbridge_android",response);
        return response;
    }}
