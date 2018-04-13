package com.teemo.service.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.teemo.service.app.App;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Created by Teemo on 2018/2/28.
 *
 * @author teemo
 */

public abstract class BaseActivity extends SwipeBackActivity implements IBaseConstruct.IBaseActivity,
        HasFragmentInjector, HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector;
    @Inject DispatchingAndroidInjector<Fragment> frameworkFragmentInjector;


    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return frameworkFragmentInjector;
    }

    @Override
    public IBaseConstruct.IApp getApp() {
        return App.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
        setStatusBar();

        //不可滑动退出
        setSwipeBackEnable(false);
//        if (hasBaseTitle) {
//            window.setFeatureInt(Window.FEATURE_CUSTOM_TITLE, getTitleId())
//        }
//        initTitle()

        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {

    }

    private int getLayoutId() {
        return -1;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    protected ImmersionBar mImmersionBar;

    private View mLibTitleBarStatus;

    protected ImmersionBar createImmersionBar() {
        ImmersionBar immersionBar = ImmersionBar.with(this);
        immersionBar
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .statusBarDarkFont(true, 0.4f)   //状态栏字体是深色，不写默认为亮色
//                .flymeOSStatusBarFontColor(R.color.flymeOSStatusBarFontColor)  //修改flyme OS状态栏字体颜色
                .keyboardEnable(true);  //解决软键盘与底部输入框冲突问题，默认为false
        if (mLibTitleBarStatus != null) {
            immersionBar.statusBarView(mLibTitleBarStatus);  //解决状态栏和布局重叠问题，任选其一
        }

        return immersionBar;
    }

    protected ImmersionBar getImmersionBar(){
        if (mImmersionBar == null) {
            mImmersionBar = createImmersionBar();
        }
        return mImmersionBar;
    }

    protected void setStatusBar() {
        getImmersionBar().init();  //必须调用方可沉浸式
    }

}
