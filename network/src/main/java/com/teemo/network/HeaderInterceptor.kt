package com.teemo.network

import android.content.Context
import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class HeaderInterceptor(private val context: Context, private val listener:OnAuthenticationChangedListener?) : Interceptor {

    companion object {
        var sAuthorization:String? = null
    }

    interface OnAuthenticationChangedListener{
        fun onAuthenticationChanged(authentication:String)
        fun getCacheAuthentication():String?
    }


    override fun intercept(chain: Interceptor.Chain?): Response {

        var auth:String? = sAuthorization
        if(TextUtils.isEmpty(auth)) {
            if(listener != null) {
                auth = listener.getCacheAuthentication()
            }
        }

        val builder = chain!!.request().newBuilder()

        if(!TextUtils.isEmpty(auth)) {
            builder.addHeader("Authorization", auth!!)
        }

//        val request = chain.request()
//                .newBuilder()
//                .addHeader("Authorization", TeemoHttp.)
//                .build()
        val response = chain.proceed(
                builder.build()
        )
        val authorization = response.header("Authorization")
        if(!TextUtils.isEmpty(authorization)) {
            sAuthorization = authorization
            listener?.onAuthenticationChanged(authorization!!)
        }
        return response
    }


}