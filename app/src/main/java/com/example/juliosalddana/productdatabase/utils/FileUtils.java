package com.example.juliosalddana.productdatabase.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.Date;

public class FileUtils {


    /**
     * Genera el nombre de archivo para una imagen
     * @return
     */
    public static String getProductImageName(){

        return "IMG_" + new Date().getTime() + ".jpg";
    }


    /**
     * Retorna la ubación para  guardar una nueva imagen
     * @param context
     * @return
     */
    public static  File getImageFile(Context context){
        File externalStorage = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if(externalStorage == null){
            return null;
        }

        return new File(externalStorage, getProductImageName());
    }
}
