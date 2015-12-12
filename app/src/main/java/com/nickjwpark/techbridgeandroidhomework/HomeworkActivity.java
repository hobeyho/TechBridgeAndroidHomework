package com.nickjwpark.techbridgeandroidhomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class HomeworkActivity extends AppCompatActivity {

    SparseArray<Group> groups = new SparseArray<Group>();
    static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listChapters);
        HomeworkExpandableListAdapter adapter = new HomeworkExpandableListAdapter(this, groups, this, id);
        listView.setAdapter(adapter);
    }

    public void createData() {
        for (int j = 0; j < 6; j++) {
            int ch = j + 1;
            Group group = new Group("Chapter " + ch);
            for (int i = 0; i < 15; i++) {
                int question = i + 1;
                group.children.add("Question " + ch + "_" + question);
            }
            groups.append(j, group);
        }
    }
}