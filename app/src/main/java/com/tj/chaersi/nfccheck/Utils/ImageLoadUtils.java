package com.tj.chaersi.nfccheck.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Created by Chaersi on 17/1/17.
 */
public class ImageLoadUtils {

    public static Bitmap pathToBitmap(String path) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            return myBitmap;
        }else{
            return null;
        }
    }

}
