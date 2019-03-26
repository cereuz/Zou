package com.zao.zou;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zao.base.BaseActivity;
import com.zao.base.TitleBar;
import com.zao.utils.AdminUtils;
import com.zao.utils.LogZ;


/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/23 10:08
 */
public class AdminActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    RelativeLayout rl_home;
    RelativeLayout rl_status;
    RelativeLayout rl_group;
    RelativeLayout rl_profile;

    //设置修改图片
    ImageView iv_home;
    ImageView iv_status;
    ImageView iv_group;
    ImageView iv_profile;

    //设置修改文字
    TextView  tv_onezao_home;
    TextView  tv_onezao_status;
    TextView  tv_onezao_group;
    TextView  tv_onezao_profile;

    //初始化四个Fragment
    private HomeFragment   homeFragment;
    private StatusFragment   statusFragment;
    private GroupFragment   groupFragment;
    private ProfileFragment   profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initDrawer();
        initTab();
    }

    /**
     * 初始化  底部的四个按钮的切换
     */
    private void initTab() {
        rl_home = (RelativeLayout) findViewById(R.id.rl_home);
        rl_home.setOnClickListener(this);
        rl_status = (RelativeLayout) findViewById(R.id.rl_status);
        rl_status.setOnClickListener(this);
        rl_group = (RelativeLayout) findViewById(R.id.rl_group);
        rl_group.setOnClickListener(this);
        rl_profile = (RelativeLayout) findViewById(R.id.rl_profile);
        rl_profile.setOnClickListener(this);

        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_status = (ImageView) findViewById(R.id.iv_status);
        iv_group = (ImageView) findViewById(R.id.iv_group);
        iv_profile = (ImageView) findViewById(R.id.iv_profile);

        tv_onezao_home = (TextView) findViewById(R.id.tv_onezao_home);
        tv_onezao_status = (TextView) findViewById(R.id.tv_onezao_status);
        tv_onezao_group = (TextView) findViewById(R.id.tv_onezao_group);
        tv_onezao_profile = (TextView) findViewById(R.id.tv_onezao_profile);

        initHomeFragment();
    }

    /**
     * 初始化左侧抽屉界面的显示
     */
    private void initDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return null;
    }

    @Override
    protected TitleBar getTitleBar() {
        return null;
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_admin;
    }

    /**
     * 控制侧边栏
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this,"相册",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Toast.makeText(this,"manage",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 如果侧边栏出来了，先隐藏侧边栏
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     *  加载顶部右侧菜单，添加菜单的点击事件。
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
/*        //设置左上角的图标的点击事件  ActionBar
        ActionBar actionBar = this.getActionBar();
        actionBar.setHomeButtonEnabled(true);*/
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AdminUtils.AdminMenu(AdminActivity.this, item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (view.getId()){
            case R.id.rl_home :
                initHomeFragment();
                break;

            case R.id.rl_status :
                //将四个的图片全部设置为未点击状态。
                hideFragment(transaction);
                clearIcon();
                iv_status.setImageResource(R.mipmap.ic_tab_status_active);
                tv_onezao_status.setTextColor(getResources().getColor(R.color.colorGreen));
                if(statusFragment  ==  null){
                    statusFragment = new  StatusFragment();
                    transaction.add(R.id.fragment_onezao_0403, statusFragment);
                    LogZ.e("Status壹");
                }   else {
                    transaction.show(statusFragment);
                    LogZ.e("Status贰");
                     }
                break;

            case R.id.rl_group :
                hideFragment(transaction);
                clearIcon();
                //将四个的图片全部设置为未点击状态。
                iv_group.setImageResource(R.mipmap.ic_tab_group_active);
                tv_onezao_group.setTextColor(getResources().getColor(R.color.colorGreen));
                if(groupFragment  ==  null){
                    groupFragment = new  GroupFragment();
                    transaction.add(R.id.fragment_onezao_0403, groupFragment);
                    LogZ.e("Group壹");
                } else {
                    transaction.show(groupFragment);
                    LogZ.e("Group贰");
                }
                break;

            case R.id.rl_profile :
                //将四个的图片全部设置为未点击状态。
                hideFragment(transaction);
                clearIcon();
                iv_profile.setImageResource(R.mipmap.ic_tab_profile_active);
                tv_onezao_profile.setTextColor(getResources().getColor(R.color.colorGreen));

                if(profileFragment  ==  null){
                    profileFragment = new  ProfileFragment();
                    transaction.add(R.id.fragment_onezao_0403, profileFragment);
                    LogZ.e("Profile壹");
                } else {
                    transaction.show(profileFragment);
                    LogZ.e("Profile贰");
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 初始化第一个Fragment
     */
    private void initHomeFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        hideFragment(transaction);
        clearIcon();
        //将四个的图片全部设置为未点击状态。
        iv_home.setImageResource(R.mipmap.ic_tab_home_active);
        tv_onezao_home.setTextColor(getResources().getColor(R.color.colorGreen));
        if(homeFragment  ==  null){
            homeFragment = new HomeFragment();
            transaction.add(R.id.fragment_onezao_0403, homeFragment);
            LogZ.e("首页壹");
        } else {
            transaction.show(homeFragment);
            LogZ.e("首页贰");
        }
        transaction.commit();
    }

    /**
     * 隐藏所有的fragment
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
            if (homeFragment != null) {
                transaction.hide(homeFragment);
            }
            if (statusFragment != null) {
                transaction.hide(statusFragment);
            }
            if (groupFragment != null) {
                transaction.hide(groupFragment);
            }
            if (profileFragment != null) {
                transaction.hide(profileFragment);
            }
        }

    /**
     * 将底部的图片和文字都归为未点击状态
     */
    private  void  clearIcon(){
        iv_home.setImageResource(R.mipmap.ic_tab_home_normal);
        iv_status.setImageResource(R.mipmap.ic_tab_status_normal);
        iv_group.setImageResource(R.mipmap.ic_tab_group_normal);
        iv_profile.setImageResource(R.mipmap.ic_tab_profile_normal);
        tv_onezao_home.setTextColor(getResources().getColor(R.color.colorText));
        tv_onezao_status.setTextColor(getResources().getColor(R.color.colorText));
        tv_onezao_group.setTextColor(getResources().getColor(R.color.colorText));
        tv_onezao_profile.setTextColor(getResources().getColor(R.color.colorText));
    }

}
