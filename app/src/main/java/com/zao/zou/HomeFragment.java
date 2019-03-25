package com.zao.zou;

import android.os.Bundle;
import android.view.View;

import com.zao.base.BaseFragment;
import com.zao.utils.DateUtil;
import com.zao.utils.LogZ;

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
}
