package com.lorien.linkagescroll.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * UIUtils
 *
 * @author zhanghao43
 * @since 2018/7/16.
 */

public class UIUtils {

    private static boolean get = false;
    private static int statusBarHeight = 0;
    private static final String TAG = "StatusBarUtil";

    public static synchronized int getStatusBarHeight(final Context context) {
        if (!get) {
            int resourceId = context.getResources()
                    .getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
                get = true;
            }
        }

        return statusBarHeight;
    }


    public static float dp2px(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

}
