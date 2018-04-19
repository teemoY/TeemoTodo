package com.teemo.teemo.core.data

import com.teemo.network.entity.User
import com.teemo.teemo.util.ApiCallback


/**
 * Created by Teemo on 2018/4/13.
 * @author teemo
 */
interface IUserTask {

    fun fetchSignUpVCode(email: String, callback: ApiCallback<Void>){}
    fun signUp(user: User, vcode: String, apiCallback: ApiCallback<User>){}
}