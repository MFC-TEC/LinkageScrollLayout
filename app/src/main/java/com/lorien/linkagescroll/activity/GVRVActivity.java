package com.lorien.linkagescroll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import com.lorien.linkagescroll.R;
import com.lorien.linkagescroll.activity.adapter.GridAdapter;
import com.lorien.linkagescroll.activity.adapter.RecyclerAdapter;

import java.util.ArrayList;


public class GVRVActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private GridView mGv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvrv);

        mGv = findViewById(R.id.gridview);
        mRv = findViewById(R.id.recyclerview);

        GridAdapter adapter = new GridAdapter();
        adapter.setData(getGVData());
        mGv.setAdapter(adapter);

        mRv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter rvAdapter = new RecyclerAdapter(getRVData());
        mRv.setAdapter(rvAdapter);
    }

    private ArrayList<String> getGVData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = "GridView - ";
        for(int i = 0; i < 39; i++) {
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
