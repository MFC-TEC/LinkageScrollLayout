package com.lorien.linkagescroll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lorien.linkagescroll.R;
import com.lorien.linkagescroll.utils.UIUtils;

public class WVSVActivity extends AppCompatActivity {
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

    private LinearLayout mScrollContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wvsv);

        mWebView = findViewById(R.id.webview);
        mScrollContainer = findViewById(R.id.scroll_container);

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

        fillScrollView();
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
