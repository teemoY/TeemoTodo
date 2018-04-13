package com.teemo.service.app;

import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.teemo.service.ui.IBaseConstruct;

import dagger.android.support.DaggerApplication;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * Created by Teemo on 2018/3/27.
 *
 * @author teemo
 */

public abstract class App extends DaggerApplication implements IBaseConstruct.IApp {

    private static IBaseConstruct.IApp sInstance;

    private static boolean debug;

    public static void setDebug(boolean debug) {
        App.debug = debug;
    }

    public static boolean isDebug() {
        return debug;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        Utils.init(this);
        setDebug(debugable());
//        SecuritySDK.init(this);
//        SecuritySDK.setConfigs(Configs.builder().debug(debug).build());

        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(debug ? Fragmentation.SHAKE : Fragmentation.NONE)
                .debug(debug)
                // 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(@NonNull Exception e) {
                        // 建议在该回调处上传至我们的Crash监测服务器
                        // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }

    public static IBaseConstruct.IApp getInstance() {
        return sInstance;
    }

}
