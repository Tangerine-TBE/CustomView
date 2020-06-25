package com.tangerine.customview.util;

import android.content.Context;
import android.graphics.Paint;
import android.util.TypedValue;

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

    /**
     * Author:Administrator
     * Date:2020-6-25
     * Direction: sp to px
     */
    public static float sp2Px(Context context,int spValue){
        final float scale = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spValue,context.getResources().getDisplayMetrics());
        return scale;
    }
}
