package com.hussain.assignment.utils;

import android.content.res.Configuration;
import android.content.res.Resources;

public class CommonUtils {

//    public static int homeCardMovieWidth() {
//        return deviceMovieCardWidth() - 30;
//    }


//    public static int deviceMovieCardWidth() {
//
//        switch (screenSize()) {
//
//            case Configuration.SCREENLAYOUT_SIZE_SMALL:
//            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
//                return lessPxDevices();
//
//            case Configuration.SCREENLAYOUT_SIZE_LARGE:
//            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
//                return highPixelDevices();
//        }
//
//        return 0;
//    }

    public static int screenSize() {
        return Resources.getSystem().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;
    }


//    private static int lessPxDevices() {
//        int removePixel = (int) (32 * pxDensity());
//        return (getScreenWidthPx() - removePixel) / 3;
//    }
//
//    private static int highPixelDevices() {
//        int removePixel = (int) (40 * pxDensity());
//        return (getScreenWidthPx() - removePixel) / 4;
//    }
}
