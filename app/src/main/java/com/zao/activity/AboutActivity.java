package com.zao.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.zao.http.DownloadIntentService;
import com.zao.utils.NotificationUtil;
import com.zao.utils.ServiceUtil;
import com.zao.utils.ToastUtil;
import com.zao.zou.R;


import java.util.List;

import static com.zao.base.MyApp.getContext;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/25 10:55
 */
public class AboutActivity extends AppCompatActivity {
    private static final int DOWNLOADAPK_ID = 10;
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_view);
        mContext = getContext();
        initView();
        initPermission();
        initNotification();
    }

    /**
     * 通知消息
     * 需要先创建渠道
     */
    private void initNotification() {
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationUtil.createNotificationChannel(mContext,"chat","聊天消息",importance);
        NotificationUtil.createNotificationChannel(mContext,"subscribe","订阅消息",importance);
    }

    /**
     * 初始化权限
     */
    private void initPermission() {
        AndPermission.with(mContext)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted(new Action<List<String>>() {
                               @Override
                               public void onAction(List<String> data) {
                                   ToastUtil.showT(mContext, "权限已获取");
                               }
                             })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        initPermission();
                    }
                })
                .start();
    }

    /**
     * 初始化界面
     */
    private void initView() {

        TextView tvDownload = findViewById(R.id.tv_download);
        tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ServiceUtil.isServiceRunning(mContext,DownloadIntentService.class.getName())) {
                    Toast.makeText(AboutActivity.this, "正在下载", Toast.LENGTH_SHORT).show();
                    return;
                }
                //String downloadUrl = http://sqdd.myapp.com/myapp/qqteam/tim/down/tim.apk;
                String downloadUrl = "/qqmi/aphone_p2p/TencentVideo_V6.0.0.14297_848.apk";
                Intent intent = new Intent(AboutActivity.this, DownloadIntentService.class);
                Bundle bundle = new Bundle();
                bundle.putString("download_url", downloadUrl);
                bundle.putInt("download_id", DOWNLOADAPK_ID);
                bundle.putString("download_file", downloadUrl.substring(downloadUrl.lastIndexOf('/') + 1));
                intent.putExtras(bundle);
                startService(intent);
            }
        });
    }
}
