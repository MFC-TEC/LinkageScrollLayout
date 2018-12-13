package com.lorien.linkagescroll.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.lorien.linkagescroll.ILinkageScrollHandler;
import com.lorien.linkagescroll.LinkageScrollEvent;
import com.lorien.linkagescroll.LinkageScrollHandler;

public class LRecyclerView extends RecyclerView implements ILinkageScrollHandler {

    private LinkageScrollEvent mLinkageScrollEvent;

    public LRecyclerView(Context context) {
        this(context, null);
    }

    public LRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

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
    public void setOnContentViewScrollEvent(LinkageScrollEvent event) {
        mLinkageScrollEvent = event;
    }

    @Override
    public LinkageScrollHandler provideScrollHandler() {
        return new LinkageScrollHandler() {
            @Override
            public void flingContentVertically(View target, int velocityY) {
                LRecyclerView.this.fling(0, velocityY);
            }

            @Override
            public void scrollContentBy(View target, int y) {
                LRecyclerView.this.scrollBy(0, y);
            }

            @Override
            public void stopContentScroll(View target) {
                LRecyclerView.this.stopScroll();
            }
        };
    }
}
