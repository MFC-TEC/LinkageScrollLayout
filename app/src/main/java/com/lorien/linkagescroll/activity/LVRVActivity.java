package com.lorien.linkagescroll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.lorien.linkagescroll.R;
import com.lorien.linkagescroll.activity.adapter.MyListAdapter;
import com.lorien.linkagescroll.activity.adapter.RecyclerAdapter;

import java.util.ArrayList;

public class LVRVActivity extends AppCompatActivity {

    private ListView mLv;

    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvrv);

        mLv = findViewById(R.id.listview);
        mRv = findViewById(R.id.recyclerview);

        MyListAdapter lvAdapter = new MyListAdapter();
        lvAdapter.setData(getLVData());
        mLv.setAdapter(lvAdapter);

        mRv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter rvAdapter = new RecyclerAdapter(getRVData());
        mRv.setAdapter(rvAdapter);
    }

    private ArrayList<String> getLVData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = "ListView - ";
        for(int i = 0; i < 30; i++) {
            data.add(temp + i);
        }
        return data;
    }

    private ArrayList<String> getRVData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = "RecyclerView - ";
        for(int i = 0; i < 30; i++) {
            data.add(temp + i);
        }
        return data;
    }

}
