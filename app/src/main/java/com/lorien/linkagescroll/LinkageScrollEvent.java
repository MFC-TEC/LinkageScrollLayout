package com.lorien.linkagescroll;

/**
 * Interface definition for a callback to be invoked
 * when the children in LinkageScrollLayout {@link LinkageScrollLayout} has been scrolled.
 *
 * @author lirienzhang
 */
public interface LinkageScrollEvent {
    /**
     * Callback method to be invoked when the children in LinkageScrollLayout {@link LinkageScrollLayout}
     * has been scrolled to top.</p>
     */
    void onContentScrollToTop();
    /**
     * Callback method to be invoked when the children in LinkageScrollLayout {@link LinkageScrollLayout}
     * has been scrolled to bottom.</p>
     */
    void onContentScrollToBottom();
}
