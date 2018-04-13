package com.teemo.service.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.classic.common.MultipleStatusView;
import com.gyf.barlibrary.ImmersionBar;
import com.teemo.service.R;
import com.teemo.service.app.App;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;


public abstract class BaseFragment<T extends IBaseConstruct.IBaseAction> extends SwipeBackFragment
        implements IBaseConstruct.IBaseFragment, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    @Inject
    protected T mAction;

    @Inject
    protected boolean debugMode;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }

    public IBaseConstruct.IApp getApp() {
        return App.getInstance();
    }

    @LayoutRes
    public int getLayoutId() {
        return -1;
    }

    public String getTitle() {
        return null;
    }

    protected void initView(View view, @Nullable Bundle savedInstanceState) {

        mLibTitleBarStatus = view.findViewById(R.id.mLibTitleBarStatus);
        mStatusLayout = view.findViewById(R.id.status_layout);
        if(mStatusLayout != null) {
            mStatusLayout.showContent();
        }
    }

    protected void initTitle(View view, @Nullable Toolbar toolbar) {
        if(toolbar != null) {
            toolbar.setTitle(getTitle());
            toolbar.setNavigationIcon(R.drawable.base_ic_chevron_left_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop();
                }
            });
        }
    }

    @IdRes
    protected final int getContainerId() {
        return android.R.id.content;
    }

    protected final BaseFragment getFragmentByPath(String path) {
        return (BaseFragment) ARouter.getInstance().build(path).navigation();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getLayoutId() > 0) {
            if (useDefaultTitleBar()) {
                View view = inflater.inflate(R.layout.frag_base_title, container, false);

                ViewGroup titleContainer = view.findViewById(R.id.title_container);
                titleContainer.removeAllViews();
                View titleView = inflater.inflate(getTitleLayoutId(), container, false);
                titleContainer.addView(titleView);

                Toolbar toolBar = view.findViewById(R.id.toolBar);
                initTitle(view, toolBar);

                ViewGroup fragmentContainer = view.findViewById(R.id.fragment_container);
                View realView = inflater.inflate(getLayoutId(), fragmentContainer, false);
                fragmentContainer.removeAllViews();
                fragmentContainer.addView(realView);
                return view;
            }
            return inflater.inflate(getLayoutId(), container, false);
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view, savedInstanceState);

        if (mAction != null) {
            mAction.takeView(this);
        }

        if (immersionEnabled()) {
            immersionInit();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mAction != null) {
            mAction.dropView();
        }

        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }


    protected boolean useDefaultTitleBar() {
        return true;
    }

    @LayoutRes
    protected int getTitleLayoutId() {
        return R.layout.base_layout_title;
    }

    //--------------------------------------------------------------------------------------------//
    //------------------------------------沉浸式状态栏---开始----------------------------------------//
    //--------------------------------------------------------------------------------------------//

    /**
     * 当前页面Fragment支持沉浸式初始化。子类可以重写返回false，设置不支持沉浸式初始化
     * Immersion bar enabled boolean.
     * 在onVisible中调用
     *
     * @return the boolean
     */
    @Nullable
    View mLibTitleBarStatus;

    protected boolean immersionEnabled() {
        return true;
    }

    protected ImmersionBar mImmersionBar;

    protected ImmersionBar createImmersionBar() {
        ImmersionBar immersionBar = ImmersionBar.with(this);
        immersionBar
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .statusBarDarkFont(true, 0.4f)   //状态栏字体是深色，不写默认为亮色
                .flymeOSStatusBarFontColor(R.color.flymeOSStatusBarFontColor)  //修改flyme OS状态栏字体颜色
                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false
        ;
        if (mLibTitleBarStatus != null) {
            immersionBar.statusBarView(mLibTitleBarStatus);  //解决状态栏和布局重叠问题，任选其一
        }

        return immersionBar;
    }

    protected ImmersionBar getImmersionBar() {
        if (mImmersionBar == null) {
            mImmersionBar = createImmersionBar();
        }
        return mImmersionBar;
    }

    protected void immersionInit() {
        getImmersionBar().init();  //必须调用方可沉浸式
    }
    //--------------------------------------------------------------------------------------------//
    //------------------------------------沉浸式状态栏---结束----------------------------------------//
    //--------------------------------------------------------------------------------------------//


    ////////////////////////////////////////////////StatusLayout////////////////////////////////////////////////
    private MultipleStatusView mStatusLayout;

    protected MultipleStatusView getStatusLayout() {
        return mStatusLayout;
    }
}
