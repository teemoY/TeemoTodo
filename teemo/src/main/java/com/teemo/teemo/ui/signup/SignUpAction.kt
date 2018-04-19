package com.teemo.teemo.ui.signup

import com.blankj.utilcode.util.LogUtils
import com.teemo.network.entity.User
import com.teemo.service.ui.BaseAction
import com.teemo.teemo.R
import com.teemo.teemo.core.data.UserRepository
import com.teemo.teemo.util.ApiCallback
import com.teemo.teemo.util.ToastMaker
import javax.inject.Inject

/**
 * Created by Teemo on 2018/4/13.
 * @author teemo
 */
class SignUpAction @Inject constructor() : BaseAction<ISignUpConstruct.View>(), ISignUpConstruct.Action {

    @Inject
    lateinit var userTask: UserRepository

    override fun fetchVCode(email: String) {
        userTask.fetchSignUpVCode(email, object : ApiCallback<Void> {
            override fun onSuccess(code: Int, message: String?, t: Void?) {
                super.onSuccess(code, message, t)
                LogUtils.i("------------发送验证码成功： code: $code  message: $message----------")
            }

            override fun onFailed(code: Int, message: String?, throwable: Throwable?) {
                super.onFailed(code, message, throwable)
                LogUtils.i("------------发送验证码失败： code: $code  message: $message----------")
                if (message != null) {
                    ToastMaker.show(message)
                } else {
                    ToastMaker.show(R.string.signup_send_vcode_failed)
                }
            }
        })
    }

    override fun signUp(email: String, password: String, vcode: String) {
        val user = User()
        user.setAccount(email)
        user.setPassword(password)
        userTask.signUp(user, vcode, object : ApiCallback<User>{
            override fun onSuccess(code: Int, message: String?, t: User?) {
                super.onSuccess(code, message, t)
                ToastMaker.show(message)
            }

            override fun onFailed(code: Int, message: String?, throwable: Throwable?) {
                super.onFailed(code, message, throwable)
                ToastMaker.show(message)
            }
        })
    }

}