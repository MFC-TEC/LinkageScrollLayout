package com.lorien.linkagescroll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lorien.linkagescroll.R;
import com.lorien.linkagescroll.activity.adapter.RecyclerAdapter;
import com.lorien.linkagescroll.utils.UIUtils;

import java.util.ArrayList;

public class RVSVActivity extends AppCompatActivity {

    private RecyclerView mRv;

    private LinearLayout mScrollContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvsv);

        mRv = findViewById(R.id.recyclerview);
        mScrollContainer = findViewById(R.id.scroll_container);

        mRv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter adapter = new RecyclerAdapter(getData());
        mRv.setAdapter(adapter);

        fillScrollView();
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = "RecyclerView - ";
        for(int i = 0; i < 30; i++) {
            data.add(temp + i);
        }
        return data;
    }

    private void fillScrollView() {
        for (int i = 0; i < 30; i++) {
            TextView tv = new TextView(this);
            LinearLayout.LayoutParams lp =
                    new LinearLayout.LayoutParams(-1, (int) UIUtils.dp2px(this, 60));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
            tv.setGravity(Gravity.CENTER);
            tv.setText("ScrollView - " + i);
            View divider = new View(this);
            mScrollContainer.addView(tv, lp);
            lp = new LinearLayout.LayoutParams(-1, 1);
            divider.setBackgroundColor(0xff555555);
            mScrollContainer.addView(divider, lp);
        }
    }
}
