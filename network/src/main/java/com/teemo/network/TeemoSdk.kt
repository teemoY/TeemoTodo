package com.teemo.network

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class TeemoSdk private constructor() {

    companion object {
        val sInstance = TeemoSdk()

        fun getInstance(): TeemoSdk {
            return sInstance
        }
    }



}