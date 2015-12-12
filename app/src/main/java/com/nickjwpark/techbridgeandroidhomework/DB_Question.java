package com.nickjwpark.techbridgeandroidhomework;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by Nick on 12/11/15.
 */
public class DB_Question {
    public String GetQuestion(int ch, int num){
        String response = null;
        try {
            URL url = new URL("http://techbridge.co.kr/android_question.php?ch="+ch+"&num="+num);

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
//        Log.d("techbridge_android",jsonArray.toString());
        return response;
    }

}
