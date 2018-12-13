package com.lorien.linkagescroll.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.lorien.linkagescroll.ILinkageScrollHandler;
import com.lorien.linkagescroll.LinkageScrollEvent;
import com.lorien.linkagescroll.LinkageScrollHandler;

public class LScrollView extends ScrollView implements ILinkageScrollHandler {

    private LinkageScrollEvent mLinkageScrollEvent;

    public LScrollView(Context context) {
        this(context, null);
    }

    public LScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
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
            public void flingContentVertically(View target, int velocityY) {
                LScrollView.this.fling(velocityY);
            }

            @Override
            public void scrollContentBy(View target, int y) {
                LScrollView.this.scrollBy(0, y);
            }

            @Override
            public void stopContentScroll(View target) {
                LScrollView.this.fling(0);
            }
        };
    }
}
