package com.lorien.linkagescroll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lorien.linkagescroll.R;
import com.lorien.linkagescroll.activity.adapter.GridAdapter;
import com.lorien.linkagescroll.utils.UIUtils;

import java.util.ArrayList;

public class SVGVActivity extends AppCompatActivity {

    private LinearLayout mScrollContainer;

    private GridView mGv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svgv);

        mScrollContainer = findViewById(R.id.scroll_container);
        fillScrollView();

        mGv = findViewById(R.id.gridview);
        GridAdapter adapter = new GridAdapter();
        adapter.setData(getData());
        mGv.setAdapter(adapter);
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

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = "GridView - ";
        for(int i = 0; i < 39; i++) {
            data.add(temp + i);
        }
        return data;
    }
}
