package com.example.juliosalddana.productdatabase.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageUtils {

    /**
     * Ajusta la imagen a las dimensiones que le indiquemos
     *
     * @param path
     * @param targetWidth
     * @param targeHeight
     * @return
     */

    public static Bitmap scaleBitmap(String path, int targetWidth, int targeHeight) {
        if (path == null || path.isEmpty()) {
           return null;
        }
        Log.d("IMAGE", path + ", " + targetWidth + " - " + targeHeight);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        Bitmap bitmap = BitmapFactory.decodeFile(path, options);

        float originalWidth = options.outWidth;
        float originalHeight = options.outHeight;

        //Calcular que tanto debemos escalar la imagen
        int inSampleSize = 1;
        if (originalWidth > targetWidth || originalHeight > targeHeight) {
            if (originalWidth > originalHeight) {
                inSampleSize = Math.round(originalHeight / targeHeight);
            } else {
                inSampleSize = Math.round(originalWidth / targetWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        return BitmapFactory.decodeFile(path, options);

    }
}
