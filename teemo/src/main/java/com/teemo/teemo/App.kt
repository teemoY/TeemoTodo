package com.teemo.teemo

import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.SPUtils
import com.teemo.network.HeaderInterceptor
import com.teemo.network.TeemoHttp
import com.teemo.teemo.core.config.TeemoConfigs
import com.teemo.teemo.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class App : com.teemo.service.app.App() {

    @Inject
    lateinit var spUtils: SPUtils

    override fun debugable(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()

        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)

        TeemoHttp.debugable(isDebug())
        TeemoHttp.onAuthenticationChangedListener(object : HeaderInterceptor.OnAuthenticationChangedListener {
            override fun onAuthenticationChanged(authentication: String) {
                spUtils.put(TeemoConfigs.share_authen, authentication)
            }

            override fun getCacheAuthentication(): String? {
                return spUtils.getString(TeemoConfigs.share_authen)
            }
        })
    }

}