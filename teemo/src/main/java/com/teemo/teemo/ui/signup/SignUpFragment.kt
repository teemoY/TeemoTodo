package com.teemo.teemo.ui.signup

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.teemo.service.ui.BaseFragment
import com.teemo.teemo.R
import com.teemo.teemo.core.config.RouteConfig
import com.teemo.teemo.util.ToastMaker
import com.teemo.teemo.util.VerifyUtils
import kotlinx.android.synthetic.main.frag_sign_up.*
import javax.inject.Inject

/**
 * Created by Teemo on 2018/4/16.
 * @author teemo
 */
@Route(path = RouteConfig.SIGN_UP, group = RouteConfig.GROUP_LAUNCHER)
class SignUpFragment @Inject constructor() : BaseFragment<ISignUpConstruct.Action>(), ISignUpConstruct.View {

    override fun getLayoutId(): Int {
        return R.layout.frag_sign_up
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)

        tv_prompt.setOnClickListener {
            val email = et_email.text.toString()
            if (!VerifyUtils.verifyEmail(email)) {
                ToastMaker.show(R.string.signup_account_format_error)
            } else {
                mAction.fetchVCode(email)
            }
        }

        btnSignUp.setOnClickListener {
            val email = et_email.text.toString().trim()
            val vcode = et_vcode.text.toString().trim()
            val password = et_password.text.toString().trim()
            if (!VerifyUtils.verifyEmail(email)) {
                ToastMaker.show(R.string.signup_account_format_error)
            } else if (!VerifyUtils.verifyVCode(vcode)) {
                ToastMaker.show(R.string.signup_vcode_format_error)
            } else if (!VerifyUtils.verifyPassword(password)) {
                ToastMaker.show(R.string.signup_password_format_error)
            } else {
                mAction.signUp(email, password, vcode)
            }

        }
    }

}