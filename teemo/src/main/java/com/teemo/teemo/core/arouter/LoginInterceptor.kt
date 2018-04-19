package com.teemo.teemo.core.arouter

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.blankj.utilcode.util.LogUtils
import com.teemo.service.ui.IBaseConstruct
import com.teemo.teemo.core.config.RouteConfig
import com.teemo.teemo.util.LoginException

/**
 * Created by Teemo on 2018/4/18.
 * @author teemo
 */
@Interceptor(priority = 5)
class LoginInterceptor:IInterceptor {

    lateinit var app:IBaseConstruct.IApp

    override fun init(context: Context?) {
        app = context!!.applicationContext as IBaseConstruct.IApp
    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        if(postcard != null && RouteConfig.GROUP_LAUNCHER == postcard.group) {
            if(!app.isLogin) {
                LogUtils.i("------------未登录------------")
                callback?.onInterrupt(LoginException())
                return
            }
        }
        callback?.onContinue(postcard)
    }

}