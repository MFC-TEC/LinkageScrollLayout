package com.lorien.linkagescroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;

import com.lorien.linkagescroll.ILinkageScrollHandler;
import com.lorien.linkagescroll.LinkageScrollEvent;
import com.lorien.linkagescroll.LinkageScrollHandler;


public class LWebView extends WebView implements ILinkageScrollHandler {

    private LinkageScrollEvent mLinkageScrollEvent;

    public LWebView(Context context) {
        this(context, null);
    }

    public LWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (!canScrollVertically(-1)) {
            if (mLinkageScrollEvent != null) {
                mLinkageScrollEvent.onContentScrollToTop();
            }
        }
        if (!canScrollVertically(1)) {
            if (mLinkageScrollEvent != null) {
                mLinkageScrollEvent.onContentScrollToBottom();
            }
        }
    }

    @Override
    public void setOnContentViewScrollEvent(LinkageScrollEvent event) {
        mLinkageScrollEvent = event;
    }

    @Override
    public LinkageScrollHandler provideScrollHandler() {
        return new LinkageScrollHandler() {
            @Override
            public void flingContentVertically(View contentView, int velocityY) {
                LWebView.this.flingScroll(0, velocityY);
            }

            @Override
            public void scrollContentBy(View bottomView, int y) {
                LWebView.this.scrollBy(0, y);
            }

            @Override
            public void stopContentScroll(View bottomView) {
                LWebView.this.flingScroll(0, 0);
            }
        };
    }
}
