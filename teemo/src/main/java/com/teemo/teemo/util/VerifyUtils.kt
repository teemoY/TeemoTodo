package com.teemo.teemo.util

/**
 * Created by Teemo on 2018/4/16.
 * @author teemo
 */
object VerifyUtils {

    fun verifyEmail(email:String): Boolean {
        return true
    }

    fun verifyVCode(vcode: String): Boolean {
        return vcode.length == 6
    }

    fun verifyPassword(password: String): Boolean {
        return password.length in 6..16
    }

}