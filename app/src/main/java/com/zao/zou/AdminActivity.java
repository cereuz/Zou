package com.zao.zou;

import android.annotation.SuppressLint;
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
import com.zao.utils.LogZ;
import com.zao.utils.ToastUtil;


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

    private boolean mIsEditStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initDrawer();
        initTab();
    }

    /**
     * 在fragment所在的FragmentActivity中，重写onSaveInstanceState方法，但是不做实现，将super.onSaveInstanceState(outState)注释掉。
     *
     * 原理分析：
     *
     * 当前APP崩溃后首页重启或者从后台再次回到这个app的时候，通过onCreate中的参数savedInstanceState恢复了之前的fragment。
     * 此时的FragmentTransaction中的相当于又再次add了fragment进去的，之前保存的fragment也还在。
     * hide()和show()方法对之前保存的fragment已经失效了。所以出现了重叠的现象。
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
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
        getSupportActionBar().setTitle(R.string.app_snail);  //设置顶部ActionBar标题

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initNavHeader(navigationView);
    }

    /**
     * 侧滑栏顶部的头像和文字控件
     */
    private void initNavHeader(NavigationView navigationView) {
        View headerView = navigationView.getHeaderView(0);
        ImageView iv_nav_header = headerView.findViewById(R.id.iv_nav_header);
        TextView  tv_nav_header_title = headerView.findViewById(R.id.tv_nav_header_title);
        TextView  tv_nav_header_content = headerView.findViewById(R.id.tv_nav_header_content);

        iv_nav_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showT(getApplicationContext(),"IV_NAV_HEADER");
                closeDrawer();
            }
        });
        tv_nav_header_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showT(getApplicationContext(),"TV_NAV_HEADER_TITLE");
                closeDrawer();
            }
        });
        tv_nav_header_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showT(getApplicationContext(),"TV_NAV_HEADER_CONTENT");
                closeDrawer();
            }
        });
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

        closeDrawer();
        return true;
    }

    /**
     * 隐藏抽屉栏
     */
    private void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        LogZ.e("Fragment切换了，Menu菜单切换");
        if (mIsEditStatus) {
            menu.findItem(R.id.action_start).setVisible(false);
            menu.findItem(R.id.action_pressure).setVisible(true);
        } else {
            menu.findItem(R.id.action_start).setVisible(true);
            menu.findItem(R.id.action_pressure).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
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
                /**
                 * 随着Fragment的切换，重新绘制Toolbar的menu
                 */
                mIsEditStatus = false;
                invalidateOptionsMenu();

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
                /**
                 * 随着Fragment的切换，重新绘制Toolbar的menu
                 */
                mIsEditStatus = true;
                invalidateOptionsMenu();

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
