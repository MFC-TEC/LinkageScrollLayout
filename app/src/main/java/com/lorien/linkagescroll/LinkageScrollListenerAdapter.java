package com.lorien.linkagescroll;

/**
 * This adapter class provides empty implementations of the methods from {@link LinkageScrollListener}.
 * Any custom listener that cares only about a subset of the methods of this listener can
 * simply subclass this adapter class instead of implementing the interface directly.
 *
 * @author lorienzhang
 */
public class LinkageScrollListenerAdapter implements LinkageScrollListener {

    @Override
    public void onTopJustIn(PosIndicator posIndicator) {

    }

    @Override
    public void onTopJustOut(PosIndicator posIndicator) {

    }

    @Override
    public void onBottomJustIn(PosIndicator posIndicator) {

    }

    @Override
    public void onBottomJustOut(PosIndicator posIndicator) {

    }

    @Override
    public void onPositionChanged(PosIndicator posIndicator) {

    }

}
