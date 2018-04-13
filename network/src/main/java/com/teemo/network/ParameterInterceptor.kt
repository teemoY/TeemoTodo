package com.teemo.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class ParameterInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        return chain!!.proceed(chain.request())
    }
}