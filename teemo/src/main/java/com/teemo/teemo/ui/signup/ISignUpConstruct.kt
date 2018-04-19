package com.teemo.teemo.ui.signup

import com.teemo.service.ui.IBaseConstruct

/**
 * Created by Teemo on 2018/4/13.
 * @author teemo
 */
interface ISignUpConstruct {

    interface View : IBaseConstruct.IBaseFragment {



    }

    interface Action : IBaseConstruct.IBaseAction<View> {
        fun fetchVCode(email: String)

        fun signUp(email: String, password: String, vcode: String)
    }
}