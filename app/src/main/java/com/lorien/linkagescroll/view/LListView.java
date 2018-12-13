package com.lorien.linkagescroll.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.lorien.linkagescroll.ILinkageScrollHandler;
import com.lorien.linkagescroll.LinkageScrollEvent;
import com.lorien.linkagescroll.LinkageScrollHandler;

public class LListView extends ListView implements ILinkageScrollHandler {

    private LinkageScrollEvent mLinkageScrollEvent;
    private OnScrollListener mOnScrollListener;

    public LListView(Context context) {
        this(context, null);
    }

    public LListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LListView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        this.mLinkageScrollEvent = event;
    }

    @Override
    public LinkageScrollHandler provideScrollHandler() {
        return new LinkageScrollHandler() {
            @Override
            public void flingContentVertically(View target, int velocityY) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    LListView.this.fling(velocityY);
                }
            }

            @Override
            public void scrollContentBy(View target, int y) {
                LListView.this.scrollListBy(y);
            }

            @Override
            public void stopContentScroll(View target) {
                LListView.this.smoothScrollBy(0, 0);
            }
        };
    }
}
