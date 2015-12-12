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
public class DB_Highscore {
    public String GetHighscore(String classId){
        String response = null;
        try {
            URL url = new URL("http://techbridge.co.kr/android_highscore.php?classId="+classId);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
        Log.d("techbridge_android",response);
        return response;
    }

}