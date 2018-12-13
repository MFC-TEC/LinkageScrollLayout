package com.lorien.linkagescroll.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;

import com.lorien.linkagescroll.ILinkageScrollHandler;
import com.lorien.linkagescroll.LinkageScrollEvent;
import com.lorien.linkagescroll.LinkageScrollHandler;

public class LGridView extends GridView implements ILinkageScrollHandler {

    private LinkageScrollEvent mLinkageScrollEvent;

    private OnScrollListener mOnScrollListener;

    public LGridView(Context context) {
        this(context, null);
    }

    public LGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        super.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (mOnScrollListener != null) {
                    mOnScrollListener.onScrollStateChanged(view, scrollState);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mOnScrollListener != null) {
                    mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }

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
        });
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mOnScrollListener = l;
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    LGridView.this.fling(velocityY);
                }
            }

            @Override
            public void scrollContentBy(View target, int y) {
                LGridView.this.scrollListBy(y);
            }

            @Override
            public void stopContentScroll(View target) {
                LGridView.this.smoothScrollBy(0, 0);
            }
        };
    }
}
