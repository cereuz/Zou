package com.zao.utils;

import android.os.Environment;

import com.zao.base.MyApp;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/25 10:39
 */
public class Constant {
    public final static String APP_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + MyApp.getContext().getPackageName();
    public final static String DOWNLOAD_DIR = "/download/";
    public final static String FILE_AUTHORITY = "com.zao.zou.fileprovider";
}
