package com.lorien.linkagescroll;

/**
 * A single linked list to wrap LinkageScrollListener.
 *
 * @author zhanghao43
 * @since 2017/12/14
 */

public class LinkageScrollListenerHolder implements LinkageScrollListener {

    private LinkageScrollListener mHandler;
    private LinkageScrollListenerHolder mNext;

    private boolean contains(LinkageScrollListener handler) {
        return mHandler != null && mHandler == handler;
    }

    public static LinkageScrollListenerHolder create() {
        LinkageScrollListenerHolder holder = new LinkageScrollListenerHolder();
        holder.mHandler = null;
        holder.mNext = null;
        return holder;
    }

    private LinkageScrollListener getHandler() {
        return mHandler;
    }

    public boolean hasHandler() {
        return mHandler != null;
    }

    /**
     * 获取列表长度
     *
     * @param header
     * @return
     */
    public static int getHandlerSize(LinkageScrollListenerHolder header) {
        int size = 0;
        if (header == null || header.mHandler == null) {
            return size;
        }

        size ++;
        LinkageScrollListenerHolder current = header;
        while (current.mNext != null) {
            size ++;
            current = current.mNext;
        }

        return size;
    }

    public static void addHandler(LinkageScrollListenerHolder header, LinkageScrollListener handler) {
        if (header == null || handler == null) {
            return;
        }

        if (header.mHandler == null) {
            header.mHandler = handler;
            return;
        }

        LinkageScrollListenerHolder current = header;
        for (; ; current = current.mNext) {
            if (current.contains(handler)) {
                return;
            }
            if (current.mNext == null) {
                break;
            }
        }

        LinkageScrollListenerHolder newHolder = new LinkageScrollListenerHolder();
        newHolder.mHandler = handler;
        newHolder.mNext = null;
        current.mNext = newHolder;

    }

    public static LinkageScrollListenerHolder removeHandler(LinkageScrollListenerHolder header, LinkageScrollListener handler) {
        if (header == null || handler == null || header.mHandler == null) {
            return header;
        }

        LinkageScrollListenerHolder current = header;
        LinkageScrollListenerHolder pre = null;
        do {
            if (current.contains(handler)) {
                // current is header
                if (pre == null) {
                    header = current.mNext;
                    current.mNext = null;

                    current = header;
                } else {
                    pre.mNext = current.mNext;
                    current.mNext = null;
                    current = pre.mNext;
                }
            } else {
                pre = current;
                current = current.mNext;
            }
        } while (current != null);

        if (header == null) {
            header = create();
        }

        return header;
    }

    @Override
    public void onTopJustIn(PosIndicator posIndicator) {
        LinkageScrollListenerHolder current = this;
        do {
            final LinkageScrollListener handler = current.getHandler();
            if (handler != null) {
                handler.onTopJustIn(posIndicator);
            }
        } while ((current = current.mNext) != null);
    }

    @Override
    public void onTopJustOut(PosIndicator posIndicator) {
        LinkageScrollListenerHolder current = this;
        do {
            final LinkageScrollListener handler = current.getHandler();
            if (handler != null) {
                handler.onTopJustOut(posIndicator);
            }
        } while ((current = current.mNext) != null);
    }

    @Override
    public void onBottomJustIn(PosIndicator posIndicator) {
        LinkageScrollListenerHolder current = this;
        do {
            final LinkageScrollListener handler = current.getHandler();
            if (handler != null) {
                handler.onBottomJustIn(posIndicator);
            }
        } while ((current = current.mNext) != null);
    }

    @Override
    public void onBottomJustOut(PosIndicator posIndicator) {
        LinkageScrollListenerHolder current = this;
        do {
            final LinkageScrollListener handler = current.getHandler();
            if (handler != null) {
                handler.onBottomJustOut(posIndicator);
            }
        } while ((current = current.mNext) != null);
    }
}
