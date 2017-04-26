package com.shangeeth.animationtypes.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by user on 25/04/17.
 */

public class UtilityHelper {
    public static int convertToPx(Context pContext, int dp) {
        DisplayMetrics displayMetrics = pContext.getResources().getDisplayMetrics();
        return (int)((dp * displayMetrics.density) + 0.5);
    }
}
