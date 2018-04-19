package com.teemo.teemo.util

import android.support.annotation.StringRes
import com.blankj.utilcode.util.ToastUtils

/**
 * Created by Teemo on 2018/4/16.
 * @author teemo
 */
object ToastMaker {

    fun show(message: CharSequence?) {
        val mess: CharSequence = message ?: "null"
        ToastUtils.showShort(mess)
    }

    fun show(@StringRes text: Int) {
        ToastUtils.showShort(text)
    }

    fun show(message: String?, vararg params: Array<out Any>) {
        ToastUtils.showShort(message, params)
    }

    fun show(@StringRes text: Int, vararg params: Array<out Any>) {
        ToastUtils.showShort(text, params)
    }

}