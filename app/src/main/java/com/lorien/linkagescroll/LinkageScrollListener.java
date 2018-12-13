package com.lorien.linkagescroll;

/**
 * Interface definition for a callback to be invoked when the position of layout container
 * has been changed.
 *
 * @author zhanghao43
 * @since 2017/12/14.
 */
public interface LinkageScrollListener {

    /**
     * top view just move into parent
     *
     * @param posIndicator position indicator
     */
    void onTopJustIn(PosIndicator posIndicator);

    /**
     * top view just move out of parent
     *
     * @param posIndicator position indicator
     */
    void onTopJustOut(PosIndicator posIndicator);

    /**
     * bottom view just move into parent
     *
     * @param posIndicator position indicator
     */
    void onBottomJustIn(PosIndicator posIndicator);

    /**
     * bottom view just move out of parent
     *
     * @param posIndicator position indicator
     */
    void onBottomJustOut(PosIndicator posIndicator);
}
