package com.zao.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.zao.checkversion.Version;
import com.zao.doubantop250.ApiMethods;
import com.zao.doubantop250.Movie;
import com.zao.doubantop250.Subjects;
import com.zao.httpdownload.DownloadIntentService;
import com.zao.doubantop250.MyObserver;
import com.zao.doubantop250.ObserverOnNextListener;
import com.zao.utils.LogZ;
import com.zao.utils.NotificationUtil;
import com.zao.utils.ServiceUtil;
import com.zao.utils.ToastUtil;
import com.zao.zou.R;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.zao.base.MyApp.getContext;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/25 10:55
 */
public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int DOWNLOADAPK_ID = 10;
    Context mContext;
    Button btn_douban_top250;
    Button btn_download;
    Button btn_check;

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
        btn_douban_top250 = findViewById(R.id.btn_douban_top250);
        btn_douban_top250.setOnClickListener(this);
        btn_download = findViewById(R.id.btn_download);
        btn_download.setOnClickListener(this);
        btn_check = findViewById(R.id.btn_download_check);
        btn_check.setOnClickListener(this);
    }

    /**
     * 点击事件的处理
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_douban_top250:
                getDouBan250();
                break;

            case R.id.btn_download :
                startDownloadService();
                break;

            case R.id.btn_download_check :
                startCheckVersion();
                break;
        }

    }

    /**
     * 请求接口，查看是否有版本更新
     */
    private void startCheckVersion() {
        ObserverOnNextListener<Version> listener = new ObserverOnNextListener<Version>() {
            @Override
            public void onNext(Version version) {
                LogZ.e("VersionName: " + version.getVersionName());
                LogZ.e("VersionDes: " + version.getVersionDes());
                LogZ.e("VersionCode: " + version.getVersionCode());
                LogZ.e("DownloadUrl: " + version.getDownloadUrl());
                showUpdateDialog(version.toString());
            }
        };
        ApiMethods.getCheckVersion(new MyObserver<Version>(this,listener),"/api/share/download/ff61ea71-517e-41c4-b688-7b08b832002d",2);
    }

    /**
     * 显示是否下载更新选择框
     */
    private void showUpdateDialog(String message) {
            // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // 设置提示框的标题
            builder.setTitle("版本升级").
                    setIcon(R.mipmap.ic_launcher). // 设置提示框的图标
                    setMessage(message).// 设置要显示的信息
                    setPositiveButton("确定", new DialogInterface.OnClickListener() {// 设置确定按钮
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startDownloadService();
                }
            }).setNegativeButton("取消", null);//设置取消按钮,null是什么都不做，并关闭对话框
            AlertDialog alertDialog = builder.create();
            // 显示对话框
            alertDialog.show();
        }


    /**
     * 获取豆瓣电影排名前250名
     */
    private  void getDouBan250(){
        ObserverOnNextListener<Movie> listener = new ObserverOnNextListener<Movie>() {
            @Override
            public void onNext(Movie movie) {
                LogZ.e("onNext: " + movie.getTitle());
                List<Subjects> list = movie.getSubjects();
                for (Subjects sub : list) {
                    LogZ.e("onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }
        };
        ApiMethods.getTopMovie(new MyObserver<Movie>(this, listener),"top250", 0, 250);
    }

    /**
     * 未经封装  Observer
     */
    private void getDouBan250_01() {
        Observer<Movie> observer = new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogZ.e("onSubscribe: ");
            }

            @Override
            public void onNext(Movie movie) {
                LogZ.e( "onNext: " + movie.getTitle());
                List<Subjects> list = movie.getSubjects();
                for (Subjects sub : list) {
                    LogZ.e( "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }

            @Override
            public void onError(Throwable e) {
                LogZ.e( "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                LogZ.e( "onComplete: Over!");
            }
        };
        ApiMethods.getTopMovie(observer,"top250", 0, 250);
    }

    /**
     * 开启下载的服务，进行下载操作
     */
    private void startDownloadService() {
        if (ServiceUtil.isServiceRunning(mContext,DownloadIntentService.class.getName())) {
            Toast.makeText(AboutActivity.this, "正在下载", Toast.LENGTH_SHORT).show();
            return;
        }
        // String downloadUrl = http://sqdd.myapp.com/myapp/qqteam/tim/down/tim.apk;
        // String downloadUrl = "/qqmi/aphone_p2p/TencentVideo_V6.0.0.14297_848.apk";
        // String downloadUrl = "/16891/52D9FF5B0E4F30719F913E09BCE9C3E9.apk?fsname=com.tencent.tmgp.sgame_1.43.1.27_43012701.apk&csr=1bbd";
        String downloadUrl = "/mobs/download/client/android/jtyh.apk";
        Intent intent = new Intent(AboutActivity.this, DownloadIntentService.class);
        Bundle bundle = new Bundle();
        bundle.putString("download_url", downloadUrl);
        bundle.putInt("download_id", DOWNLOADAPK_ID);
        bundle.putString("download_file", downloadUrl.substring(downloadUrl.lastIndexOf('/') + 1));
        intent.putExtras(bundle);
        startService(intent);
    }
}
