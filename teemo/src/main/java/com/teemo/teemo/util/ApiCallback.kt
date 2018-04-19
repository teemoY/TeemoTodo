package com.teemo.teemo.util

/**
 * Created by Teemo on 2018/4/16.
 * @author teemo
 */
interface ApiCallback<in T> {

    fun onSuccess(code: Int, message: String?, t: T?) {

    }

    fun onFailed(code: Int, message: String?, throwable: Throwable?) {

    }

}