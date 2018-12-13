package com.lorien.linkagescroll;

import android.view.View;

/**
 * Interface definition that Children in LinkageScrollLayout {@link LinkageScrollLayout} have to implement
 * or the LinkageScrollLayout will not work properly.
 *
 * @author lorienzhang
 */
public interface LinkageScrollHandler {

    void flingContentVertically(View target, int velocityY);

    void scrollContentBy(View target, int y);

    void stopContentScroll(View target);
}
