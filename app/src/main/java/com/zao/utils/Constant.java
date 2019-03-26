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
    /**
     * 交通银行  http://download1.bankcomm.com/mobs/download/client/android/jtyh.apk
     * 王者荣耀  https://imtt.dd.qq.com/16891/52D9FF5B0E4F30719F913E09BCE9C3E9.apk?fsname=com.tencent.tmgp.sgame_1.43.1.27_43012701.apk&csr=1bbd
     * 支付宝    https://t.alipayobjects.com/L1/71/100/and/alipay_wap_main.apk
     */
    public static String CHECK_VERSION_JSON_URL = "https://coding.net/api/share/download/ff61ea71-517e-41c4-b688-7b08b832002d";
    public static final String BASE_URL2 = "https://imtt.dd.qq.com";
    public static final String BASE_URL = "http://download1.bankcomm.com";
    public final static String APP_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + MyApp.getContext().getPackageName();
    public final static String DOWNLOAD_DIR = "/download/";
    public final static String FILE_AUTHORITY = "com.zao.zou.fileprovider";
    public final static String APK_HONOR_URL = "https://imtt.dd.qq.com/16891/52D9FF5B0E4F30719F913E09BCE9C3E9.apk?fsname=com.tencent.tmgp.sgame_1.43.1.27_43012701.apk&csr=1bbd";
}
