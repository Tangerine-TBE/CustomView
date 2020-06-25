package com.tangerine.customview.util;

import android.content.Context;

/**
 * Created by Tangerine on 2020-6-25.
 */
public class DensityUtil {

    public static int dip2Px(Context context ,float dpValue){
        final float scale =  context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int px2Dip(Context context ,float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale +0.5f);
    }
}
