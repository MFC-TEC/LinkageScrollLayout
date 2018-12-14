package com.lorien.linkagescroll.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import com.lorien.linkagescroll.LinkageScrollLayout;
import com.lorien.linkagescroll.LinkageScrollListenerAdapter;
import com.lorien.linkagescroll.PosIndicator;
import com.lorien.linkagescroll.R;
import com.lorien.linkagescroll.activity.adapter.MyListAdapter;

import java.util.ArrayList;

public class WVLVActivity extends AppCompatActivity {

    public static final String TAG = WVLVActivity.class.getSimpleName();

    String htmlString = "<h1>Title1</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p><br>" +
            "<h1>Title2</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p><br>" +
            "<h1>Title3</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title4</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title5</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title6</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title7</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title8</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title9</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title10</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title11</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title12</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title13</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title14</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title15</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title16</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title17</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p>" +
            "<h1>Title18</h1><p>This is HTML text. <i>Formatted in italics.</i><br>Anothor Line.</p><br>" ;

    private WebView mWebView;
    private ListView mListView;
    private LinkageScrollLayout mLinkageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wvlv);

        mWebView = findViewById(R.id.webview);
        mListView = findViewById(R.id.listview);
        mLinkageLayout = findViewById(R.id.root);

        mWebView = findViewById(R.id.webview);
        mWebView.loadData(htmlString, "text/html", "utf-8");
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        };
        mWebView.setWebViewClient(webViewClient);

        MyListAdapter adapter = new MyListAdapter();
        adapter.setData(getData());
        mListView.setAdapter(adapter);

        mLinkageLayout.addLinkageScrollListener(new LinkageScrollListenerAdapter() {
            @Override
            public void onTopJustIn(PosIndicator posIndicator) {
                // when top view move into layout, this function will be called
                Log.d(TAG, "onTopJustIn");
            }

            @Override
            public void onTopJustOut(PosIndicator posIndicator) {
                // when top view move out of layout, this function will be called
                Log.d(TAG, "onTopJustOut");
            }

            @Override
            public void onBottomJustIn(PosIndicator posIndicator) {
                // when bottom view move into layout, this function will be called
                Log.d(TAG, "onBottomJustIn");
            }

            @Override
            public void onBottomJustOut(PosIndicator posIndicator) {
                // when bottom view move out of layout, this function will be called
                Log.d(TAG, "onBottomJustOut");
            }

            @Override
            public void onPositionChanged(PosIndicator posIndicator) {
                Log.d(TAG, "onPositionChanged, postion: " + posIndicator.getCurrentPos());
            }
        });
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = "ListView - ";
        for(int i = 0; i < 30; i++) {
            data.add(temp + i);
        }
        return data;
    }

}
