package com.nickjwpark.techbridgeandroidhomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

import android.app.Activity;
import android.util.SparseArray;
import android.view.Menu;
import android.widget.ExpandableListView;

public class HomeworkActivity extends AppCompatActivity {

    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listChapters);
        HomeworkExpandableListAdapter adapter = new HomeworkExpandableListAdapter(this, groups, this);
        listView.setAdapter(adapter);
    }

    public void createData() {
        for (int j = 0; j < 6; j++) {
            int ch = j + 1;
            Group group = new Group("Chapter " + ch);
            for (int i = 0; i < 10; i++) {
                int question = i + 1;
                group.children.add("Question " + ch + "." + question);
            }
            groups.append(j, group);
        }
    }
}