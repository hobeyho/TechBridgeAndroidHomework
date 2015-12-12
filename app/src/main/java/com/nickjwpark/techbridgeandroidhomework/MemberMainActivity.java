package com.nickjwpark.techbridgeandroidhomework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberMainActivity extends AppCompatActivity {

    int id;
    String classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_main);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
            classId = extras.getString("classId");
        }

        final ListView listview = (ListView) findViewById(R.id.listMemberMain);
        String[] values = new String[] { "Homework", "Sandbox", "Highscore"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                if(position==0){
                    homeworkActivity();
                } else if(position==1){
                    sandboxActivity();
                } else if(position==2){
                    highscoreActivity();
                }

            }

        });
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

    public void homeworkActivity(){
        Activity fromActivity = this;
        Class toActivity = HomeworkActivity.class;
        Intent intent = new Intent(fromActivity,toActivity);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void sandboxActivity(){
        Activity fromActivity = this;
        Class toActivity = SandboxActivity.class;
        Intent intent = new Intent(fromActivity,toActivity);
        startActivity(intent);
    }

    public void highscoreActivity(){
        Activity fromActivity = this;
        Class toActivity = HighscoreActivity.class;
        Intent intent = new Intent(fromActivity,toActivity);
        intent.putExtra("class",classId);
        startActivity(intent);
    }

}
