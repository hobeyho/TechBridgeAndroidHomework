package com.nickjwpark.techbridgeandroidhomework;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Nick on 12/6/15.
 */
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DB_Login {
    public String TryLogin(String email){
        String response = "";
        try {
            // Log.d("json","f"+"http://epicerie.kr/order/android_deliverer_app_fetch_data?order_seq=" + detail_order_seq);
            URL url = new URL("http://techbridge.co.kr/android_login.php?email="+email);
            //Log.d("json","error1");

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
        Log.d("techbridge_android",response);
        return response;
    }

}
