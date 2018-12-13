package com.lorien.linkagescroll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lorien.linkagescroll.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    /**
     * WebView & ListView
     *
     * @param view
     */
    public void wvlv(View view) {
        startActivity(new Intent(this, WVLVActivity.class));
    }

    /**
     * WebView & RecyclerView
     *
     * @param view
     */
    public void wvrv(View view) {
        startActivity(new Intent(this, WVRVActivity.class));
    }

    /**
     * WebView & ScrollView
     *
     * @param view
     */
    public void wvsv(View view) {
        startActivity(new Intent(this, WVSVActivity.class));
    }

    /**
     * WebView & GridView
     *
     * @param view
     */
    public void wvgv(View view) {
        startActivity(new Intent(this, WVGVActivity.class));
    }

    /**
     * RecyclerView & WebView
     *
     * @param view
     */
    public void rvwv(View view) {
        startActivity(new Intent(this, RVWVActivity.class));
    }

    /**
     * ListView & RecyclerView
     *
     * @param view
     */
    public void lvrv(View view) {
        startActivity(new Intent(this, LVRVActivity.class));
    }

    /**
     * GridView & RecyclerView
     *
     * @param view
     */
    public void gvrv(View view) {
        startActivity(new Intent(this, GVRVActivity.class));
    }

    /**
     * RecyclerView & ScrollView
     *
     * @param view
     */
    public void rvsv(View view) {
        startActivity(new Intent(this, RVSVActivity.class));
    }

    public void svgv(View view) {
        startActivity(new Intent(this, SVGVActivity.class));
    }
}
