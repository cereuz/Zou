package com.zao.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/25 14:04
 */
public class FileUtil {
    public static Uri get7Uri(Context mContext,String authority,String path){
        File file = new File(path);
        Uri data;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            data = FileProvider.getUriForFile(mContext, authority, file);
        } else {
            data = Uri.fromFile(file);
        }
        return data;
    }
}
