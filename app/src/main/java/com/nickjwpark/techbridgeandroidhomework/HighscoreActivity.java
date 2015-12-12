package com.nickjwpark.techbridgeandroidhomework;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HighscoreActivity extends AppCompatActivity {

    String classId;
    TextView textHighscoreTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        textHighscoreTitle = (TextView) findViewById(R.id.textHighscoreTitle);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            classId = extras.getString("classId");
        }

        if(isOnline()){
            textHighscoreTitle.setText("Highscore for "+classId);
            new highscoreTask().execute(new DB_Highscore());
        } else {
            Toast.makeText(getApplicationContext(), "네트워크 연결을 확인해주세요.",Toast.LENGTH_LONG).show();
        }

    }

    private class highscoreTask extends AsyncTask<DB_Highscore, Long, String> {

        @Override
        protected String doInBackground(DB_Highscore... params) {
            //it is executed on Background thread
            return params[0].GetHighscore(classId);
        }

        @Override
        protected void onPostExecute(final String response) {
            settextToAdapter(response);
        }
    }

    public void settextToAdapter(String response) {
        if (response.equals("")){
            Toast.makeText(getApplicationContext(), "에러",Toast.LENGTH_LONG).show();
        } else {
            Log.d("techbridge_android",response);
            String [] highscoreList = response.split(";");
            String [][] highscores = new String [highscoreList.length][2];
            for(int i=0; i<highscoreList.length; i++){
                String [] student = highscoreList[i].split(",");
                highscores[i][0] = student[0];
                highscores[i][1] = student[1];
            }
            Arrays.sort(highscores, new Comparator<String[]>() {
                @Override
                public int compare(final String[] entry1, final String[] entry2) {
                    final int score1 = Integer.parseInt(entry1[1]);
                    final int score2 = Integer.parseInt(entry2[1]);
                    return (Integer.valueOf(score1).compareTo(Integer.valueOf(score2)))*-1;
                }
            });

            final ListView listview = (ListView) findViewById(R.id.listHighscore);
            String[] values = new String[highscoreList.length];
            for(int i=0; i<values.length; i++){
                values[i] = highscores[i][0] + ":   " + highscores[i][1]+"점";
                Log.d("techbridge_android",values[i]);
            }

            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < values.length; ++i) {
                list.add(values[i]);
            }
            final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                }
            });
        }
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }
        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }
        @Override
        public boolean hasStableIds() {
            return true;
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
