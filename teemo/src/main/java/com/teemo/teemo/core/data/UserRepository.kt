package com.teemo.teemo.core.data

import android.graphics.Bitmap
import com.teemo.network.BaseResponse
import com.teemo.network.MobileApi
import com.teemo.network.TeemoHttp
import com.teemo.network.config.Codes
import com.teemo.network.entity.User
import com.teemo.service.di.qualifier.Local
import com.teemo.service.di.qualifier.Remote
import com.teemo.teemo.util.ApiCallback
import com.teemo.teemo.util.ApiSubscriberImpl
import com.teemo.teemo.util.ImageUtils
import com.teemo.teemo.util.TeemoException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Teemo on 2018/4/13.
 * @author teemo
 */
@Singleton
class UserRepository @Inject constructor() : IUserTask {

    @Inject
    lateinit var teemoHttp: TeemoHttp

    override fun fetchSignUpVCode(email: String, callback: ApiCallback<Void>) {
        teemoHttp.setObservable(MobileApi.sendSignUpVCode(email))
                .setShowWaitingDialog(true)
                .subscriber(
                        object : ApiSubscriberImpl<Void>(callback) {}
                )
    }

    override fun signUp(user: User, vcode: String, apiCallback: ApiCallback<User>) {
        teemoHttp.setObservable(MobileApi.signUp(user, vcode))
                .setShowWaitingDialog(true)
                .subscriber(
                        object : ApiSubscriberImpl<User>(apiCallback) {}
                )
    }

}