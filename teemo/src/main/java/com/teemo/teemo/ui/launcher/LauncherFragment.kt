package com.teemo.teemo.ui.launcher

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.SPUtils
import com.teemo.network.ApiSubscriber
import com.teemo.network.BaseResponse
import com.teemo.network.MobileApi
import com.teemo.network.TeemoHttp
import com.teemo.network.entity.User
import com.teemo.service.di.ActivityScoped
import com.teemo.service.ui.BaseFragment
import com.teemo.teemo.R
import com.teemo.teemo.core.config.RouteConfig
import io.reactivex.Flowable
import kotlinx.android.synthetic.main.frag_launcher.*
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
@ActivityScoped
@Route(path = RouteConfig.LAUNCHER, group = RouteConfig.GROUP_LAUNCHER)
class LauncherFragment @Inject constructor() : BaseFragment<ILauncherConstruct.Action>(), ILauncherConstruct.View {

    @Inject
    lateinit var spUtils: SPUtils

    override fun useDefaultTitleBar(): Boolean {
        return false
    }

    override fun getLayoutId(): Int {
        return R.layout.frag_launcher
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)

        button.setOnClickListener {
            TeemoHttp.with(activity!!)
                    .setObservable(MobileApi.test())
                    .setShowWaitingDialog(true)
                    .subscriber(object : ApiSubscriber<String>() {
                        override fun onNext(t: String) {
                            super.onNext(t)
                            println(t)
                        }
                    })
        }


        button2.setOnClickListener {
            TeemoHttp.with(activity!!)
                    .setObservable(MobileApi.userTest("teemo"))
                    .setShowWaitingDialog(true)
                    .subscriber(object : ApiSubscriber<User>() {
                        override fun onNext(t: User) {
                            super.onNext(t)
                            println(t)
                        }
                    })
        }

        icon.setOnClickListener {
            TeemoHttp.with(activity!!)
                    .setObservable(
                            MobileApi.signIn(
                                    "teemo", "123456"
                            )
                    )
                    .setShowWaitingDialog(true)
                    .subscriber(
                            object : ApiSubscriber<String>() {
                                override fun onNext(t: String) {
                                    super.onNext(t)
                                    println(t)
                                }
                            }
                    )
        }
    }

}