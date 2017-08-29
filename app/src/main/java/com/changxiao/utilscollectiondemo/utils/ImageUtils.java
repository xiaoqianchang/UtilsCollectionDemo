package com.changxiao.utilscollectiondemo.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2017/8/29.
 *
 * @version 1.0
 */

public class ImageUtils {

    /**
     * 等比例压缩图片
     * @param bitmap
     * @param screenWidth
     * @param screenHight
     * @return
     */
    public static Bitmap getBitmap(Bitmap bitmap, int screenWidth,
                                   int screenHight) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();
        float scale = (float) screenWidth / w;
        float scale2 = (float) screenHight / h;

        //取比例小的值 可以把图片完全缩放在屏幕内
        scale = scale < scale2 ? scale : scale2;

        // 都按照宽度scale 保证图片不变形.根据宽度来确定高度
        matrix.postScale(scale, scale);
        // w,h是原图的属性.
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }
}
