package com.lorien.linkagescroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.OverScroller;

import static android.support.v4.widget.ViewDragHelper.INVALID_POINTER;

/**
 * Layout container for two view(TopView and BottomView) that can be scrolled by user,
 * allowing the total height of two child to be larger than the physical display.
 * LinkageScrollLayout is a {@link ViewGroup}, which place two View in it,
 * <P>Children in the container must implement ILinkageScrollHandler{@link ILinkageScrollHandler}, or
 * the layout container will not work properly.</P>
 *
 * @author lorienzhang
 */
public class LinkageScrollLayout extends ViewGroup {
    public static final String TAG = "LinkageScrollLayout";
    /** multi finger touch */
    private int mActivePointerId = INVALID_POINTER;
    /** top view，first child view */
    private View mTopView;
    /** bottom view，second child view */
    private View mBottomView;
    /** top view，scroll handler */
    private LinkageScrollHandler mTopHandler;
    /** bottom view，scroll handler */
    private LinkageScrollHandler mBottomHandler;
    private int mLastScrollY;
    private PosIndicator mPosIndicator;
    private int mTopViewHeight;
    private int mBottomViewHeight;
    private int mVisualHeight;
    private int mHeight;
    /** Last MotionEvent */
    private MotionEvent mLastMotionEvent;
    private boolean mHasSendCancelEvent;
    private OverScroller mScroller;
    /** to track child view's velocity */
    private OverScroller mTrackScroller;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private int mMaximumVelocity, mMinimumVelocity;
    private boolean isControl;
    /** scroll listener */
    private LinkageScrollListenerHolder mLinkageScrollListener = LinkageScrollListenerHolder.create();

    private LinkageScrollEvent mBottomViewScrollEvent = new LinkageScrollEvent() {
        @Override
        public void onContentScrollToTop() {
            if (!mPosIndicator.isInStartPos()) {
                return;
            }
            if (mTrackScroller.computeScrollOffset()) {
                int curVelocity = (int) mTrackScroller.getCurrVelocity();
                fling(curVelocity);
                mTrackScroller.abortAnimation();
            }
        }

        @Override
        public void onContentScrollToBottom() {

        }
    };

    private LinkageScrollEvent mTopViewScrollEvent = new LinkageScrollEvent() {
        @Override
        public void onContentScrollToTop() {

        }

        @Override
        public void onContentScrollToBottom() {
            if (!mPosIndicator.isInEndPos()) {
                return;
            }
            if (mTrackScroller.computeScrollOffset()) {
                int curVelocity = (int) mTrackScroller.getCurrVelocity();
                fling(- curVelocity);
                mTrackScroller.abortAnimation();
            }
        }
    };

    public LinkageScrollLayout(Context context) {
        this(context, null);
    }

    public LinkageScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinkageScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mPosIndicator = new PosIndicator();
        mScroller = new OverScroller(context);
        mTrackScroller = new OverScroller(context);
        mVelocityTracker = VelocityTracker.obtain();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mPosIndicator.setTouchSlop(mTouchSlop);
        mMaximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final int childCount = getChildCount();
        if (childCount != 2) {
            throw new RuntimeException("child count in LinkageScrollLayout must no more 2");
        }
        mTopView = getChildAt(0);
        mBottomView = getChildAt(1);
        if (!(mBottomView instanceof ILinkageScrollHandler)
                || !(mTopView instanceof ILinkageScrollHandler)) {
            throw new RuntimeException("child in LinkageScrollLayout must implement IContentHandler");
        }
        mBottomHandler = ((ILinkageScrollHandler) mBottomView).provideScrollHandler();
        mTopHandler = ((ILinkageScrollHandler) mTopView).provideScrollHandler();
        if (mTopHandler == null || mBottomHandler == null) {
            throw new RuntimeException("LinkageScrollHandler provided by child must not be null");
        }

        ((ILinkageScrollHandler) mBottomView).setOnContentViewScrollEvent(mBottomViewScrollEvent);
        ((ILinkageScrollHandler) mTopView).setOnContentViewScrollEvent(mTopViewScrollEvent);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mTopView != null) {
            mTopView.measure(widthMeasureSpec, heightMeasureSpec);
            mTopViewHeight = mTopView.getMeasuredHeight();
        }
        if (mBottomView != null) {
            mBottomView.measure(widthMeasureSpec, heightMeasureSpec);
            mBottomViewHeight = mBottomView.getMeasuredHeight();
        }
        // height of visual height
        mVisualHeight = getMeasuredHeight();
        mHeight = mTopViewHeight + mBottomViewHeight;
        int widthSize = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        setMeasuredDimension(widthSize, mHeight);

        Log.d(TAG, "#onMeasure# topHeight: " + mTopViewHeight + ", bottomHeight: " + mBottomViewHeight + ", layoutHeight: " + mHeight);
        // init position indicator
        mPosIndicator.initStartAndEndPos(0, mVisualHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // set current position
        mPosIndicator.setCurrentPos(mTopViewHeight);
        Log.d(TAG, "#onSizeChanged# current position: " + mTopViewHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int curPos = mPosIndicator.getCurrentPos();
            int left = l;
            int top = curPos - mTopViewHeight;
            int right = r;
            int bottom = curPos;
            if (mTopView != null) {
                mTopView.layout(left, top, right, bottom);
                Log.d(TAG, "#onLayout# layout top: top: " + top + ", bottom: " + bottom);
            }
            top = curPos;
            bottom = top + mBottomViewHeight;
            if (mBottomView != null) {
                mBottomView.layout(left, top, right, bottom);
                Log.d(TAG, "#onLayout# layout bottom: top: " + top + ", bottom: " + bottom);
            }
        }
    }

    /**
     * motion event pass to children
     *
     * @param event
     * @return
     */
    private boolean dispatchTouchEventSupper(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mScroller.computeScrollOffset()) {
            int curScrollY = mScroller.getCurrY();
            int offsetY = curScrollY - mLastScrollY;
            mLastScrollY = curScrollY;
            if (offsetY != 0) {
                moveChildrenToNewPos(offsetY);
                int velocity = (int) mScroller.getCurrVelocity();
                if (mPosIndicator.isInStartPos()) {
                    // bad case：不同机型表现效果不一样。
                    // oneplus效果OK， 华为mate 9 webview的velocity不是很匹配
                    // 这里需要优化，包括判断条件
                    if (mBottomView instanceof WebView) {
                        velocity /= 2;
                    }
                    mBottomHandler.flingContentVertically(mBottomView, velocity);
                    mScroller.abortAnimation();
                }
                if (mPosIndicator.isInEndPos()) {
                    // bad case：不同机型表现效果不一样。
                    // oneplus的效果OK， 华为mate9webview的velocity不是很匹配
                    if (mTopView instanceof WebView) {
                        velocity /= 2;
                    }
                    mTopHandler.flingContentVertically(mTopView, - velocity);
                    mScroller.abortAnimation();
                }
            }
            invalidate();
        }
    }

    /**
     * fling children
     *
     * @param velocityY
     */
    private void fling(int velocityY) {
//        Log.d(TAG, "#fling# velocity]Y: " + velocityY);
        mScroller.fling(0, 0,
                0, velocityY,
                0, 0,
                -Integer.MAX_VALUE, Integer.MAX_VALUE);
        mLastScrollY = 0;
        invalidate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        int actionIndex = ev.getActionIndex();
        mVelocityTracker.addMovement(ev);

        switch (ev.getAction()
                & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = ev.getPointerId(actionIndex);
                mPosIndicator.onDown(x, y);
                mHasSendCancelEvent = false;
                mVelocityTracker.clear();
                mVelocityTracker.addMovement(ev);
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                if (!mTrackScroller.isFinished()) {
                    mTrackScroller.abortAnimation();
                }
                // down 停止content view scroll
                mTopHandler.stopContentScroll(mTopView);
                mBottomHandler.stopContentScroll(mBottomView);
                return dispatchTouchEventSupper(ev);
            case MotionEvent.ACTION_POINTER_DOWN:
                mActivePointerId = ev.getPointerId(actionIndex);
                x = ev.getX(actionIndex);
                y = ev.getY(actionIndex);
                mPosIndicator.onPointerDown(x, y);
                return dispatchTouchEventSupper(ev);
            case MotionEvent.ACTION_POINTER_UP:
                final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final int pointerId = ev.getPointerId(pointerIndex);
                if (pointerId == mActivePointerId) {
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mActivePointerId = ev.getPointerId(newPointerIndex);
                    x = (int) ev.getX(newPointerIndex);
                    y = (int) ev.getY(newPointerIndex);
                    mPosIndicator.onPointerUp(x, y);
                }
                return dispatchTouchEventSupper(ev);
            case MotionEvent.ACTION_MOVE:
                mLastMotionEvent = ev;
                int activePointerIndex = ev.findPointerIndex(mActivePointerId);
                if (activePointerIndex < 0) {
                    return false;
                }
                x = ev.getX(activePointerIndex);
                y = ev.getY(activePointerIndex);
                mPosIndicator.onMove(x, y);
                if (mPosIndicator.isDragging()) {
                    if (mPosIndicator.isMoveUp()) {
                        // move up
                        if (mPosIndicator.isInStartPos()) {
                            if (isControl) {
                                mBottomHandler.scrollContentBy(mBottomView, (int) -mPosIndicator.getOffsetY());
                            } else {
                                return dispatchTouchEventSupper(ev);
                            }
                        } else if (mPosIndicator.isInEndPos()) {
                            if (mTopView.canScrollVertically(1)) {
                                if (isControl) {
                                    mTopHandler.scrollContentBy(mTopView, (int) -mPosIndicator.getOffsetY());
                                } else {
                                    return dispatchTouchEventSupper(ev);
                                }
                            } else {
                                moveChildrenToNewPos(mPosIndicator.getOffsetY());
                                isControl = true;
                            }
                        } else {
                            moveChildrenToNewPos(mPosIndicator.getOffsetY());
                            isControl = true;
                        }
                    } else {
                        // move down
                        if (mPosIndicator.isInStartPos()) {
                            if (mBottomView.canScrollVertically(-1)) {
                                if (isControl) {
                                    mBottomHandler.scrollContentBy(mBottomView, (int) -mPosIndicator.getOffsetY());
                                } else {
                                    return dispatchTouchEventSupper(ev);
                                }
                            } else {
                                moveChildrenToNewPos(mPosIndicator.getOffsetY());
                                isControl = true;
                            }
                        } else if (mPosIndicator.isInEndPos()) {
                            if (isControl) {
                                mTopHandler.scrollContentBy(mBottomView, (int) -mPosIndicator.getOffsetY());
                            } else {
                                return dispatchTouchEventSupper(ev);
                            }
                        } else {
                            moveChildrenToNewPos(mPosIndicator.getOffsetY());
                            isControl = true;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mPosIndicator.onRelease(x, y);
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int velocityY = (int) mVelocityTracker.getYVelocity();
                if (isControl) {
                    isControl = false;
                    if (Math.abs(velocityY) > mMinimumVelocity) {
                        if (mPosIndicator.isInStartPos()) {
                            mBottomHandler.flingContentVertically(mBottomView, -velocityY);
                        } else {
                            fling(velocityY);
                        }
                    }
                } else {
                    if (Math.abs(velocityY) > mMinimumVelocity) {
                        // 跟踪子view velocity
                        trackChildVelocity(velocityY);
                    }
                    return dispatchTouchEventSupper(ev);
                }
                break;
        }

        return true;
    }

    /**
     * 跟踪子view的velocity
     */
    private void trackChildVelocity(int velocityY) {
        mTrackScroller.fling(0, 0,
                0, Math.abs(velocityY),
                0, 0,
                0, Integer.MAX_VALUE);
    }

    /**
     * send cancel event to children
     */
    private void sendCancelEvent() {
        Log.d(TAG, "#sendCancelEvent#");
        MotionEvent event = mLastMotionEvent;
        MotionEvent e = MotionEvent.obtain(event.getDownTime(),
                event.getEventTime() + ViewConfiguration.getLongPressTimeout(),
                MotionEvent.ACTION_CANCEL,
                event.getX(),
                event.getY(),
                event.getMetaState());
        dispatchTouchEventSupper(e);
    }

    /**
     * 将子view移到新的position
     *
     * @param offsetY
     */
    private void moveChildrenToNewPos(float offsetY) {
        // send cancel event to children
        if (!mHasSendCancelEvent
                && mPosIndicator.isUnderTouch()
                && mPosIndicator.hasMovedAfterPressedDown()) {
            mHasSendCancelEvent = true;
            sendCancelEvent();
        }
        if (Math.abs(offsetY) <= 0) {
            return;
        }
        int toPos = mPosIndicator.getCurrentPos() + (int) offsetY;
        // 边界检查：startPos <= toPos <= endPos
        toPos = mPosIndicator.checkPosBoundary(toPos);
        mPosIndicator.setCurrentPos(toPos);
        int distanceY = toPos - mPosIndicator.getLastPos();
        offsetChildren(distanceY);

        if (mPosIndicator.hasJustLeftEndPos()) {
            if (mLinkageScrollListener.hasHandler()) {
                mLinkageScrollListener.onBottomJustIn(mPosIndicator);
            }
        }
        if (mPosIndicator.hasJustLeftStartPos()) {
            if (mLinkageScrollListener.hasHandler()) {
                mLinkageScrollListener.onTopJustIn(mPosIndicator);
            }
        }
        if (mLinkageScrollListener.hasHandler()) {
            mLinkageScrollListener.onPositionChanged(mPosIndicator);
        }
        if (mPosIndicator.hasJustBackStartPos()) {
            if (mLinkageScrollListener.hasHandler()) {
                mLinkageScrollListener.onTopJustOut(mPosIndicator);
            }
        }
        if (mPosIndicator.hasJustBackEndPos()) {
            if (mLinkageScrollListener.hasHandler()) {
                mLinkageScrollListener.onBottomJustOut(mPosIndicator);
            }
        }
    }

    /**
     * move容器中子view的位置
     *
     * @param deltaY
     */
    private void offsetChildren(int deltaY) {
        mTopView.offsetTopAndBottom(deltaY);
        mBottomView.offsetTopAndBottom(deltaY);
    }

    /**
     * 注册容器位置信息更新的接口
     */
    public void addLinkageScrollListener(LinkageScrollListener handler) {
        mLinkageScrollListener.addHandler(mLinkageScrollListener, handler);
    }

}
