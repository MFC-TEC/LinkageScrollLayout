package com.lorien.linkagescroll;

/**
 * Interface definition which LinkageScrollLayout {@link LinkageScrollLayout}'s children
 * has to implements. Make sure the layout container work properly.
 *
 * @author lorienzhang
 */
public interface ILinkageScrollHandler {
    /**
     * <p>children in LinkageScrollLayout {@link LinkageScrollLayout} must implements this method
     * to hold LinkageScrollEvent interface.</p>
     *
     * @param event LinkageScrollEvent that the bottom view holds
     */
    void setOnContentViewScrollEvent(LinkageScrollEvent event);

    /**
     * <p>children in LinkageScrollLayout {@link LinkageScrollLayout} must implements this method
     * to offer LinkageScrollHandler to LinkageScrollLayout.</p>
     */
    LinkageScrollHandler provideScrollHandler();
}
