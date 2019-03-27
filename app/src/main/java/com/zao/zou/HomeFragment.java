package com.zao.zou;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zao.base.BaseFragment;
import com.zao.event.MessageEvent;
import com.zao.utils.DateUtil;
import com.zao.utils.LogZ;
import com.zao.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/23 15:43
 */
public class HomeFragment extends BaseFragment {
    @Override
    protected void doOnCreate(View baseView, Bundle savedInstanceState) {
        LogZ.e(DateUtil.getCurrentTime_Today());
    }

    @Override
    protected void doOnViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_onezao_home0306;
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //这个必须存在,不然程序会崩溃
    public void onEventZou(MessageEvent event) {
            String strings = event.toString();
            LogZ.e(strings);
            ToastUtil.showT(mContext,"接收到EventBus从其他控件post的消息：" + strings);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 注册EventBus
         * 需要先注册，然后其他控件发送消息，最后当前控件才能接收到消息。
         */
        EventBus.getDefault().register(this);
        ToastUtil.showT(mContext,"注册EventBus");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
