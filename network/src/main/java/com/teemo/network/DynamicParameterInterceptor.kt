package com.teemo.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class DynamicParameterInterceptor constructor(private val map: HashMap<String, String>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request()
        val urlBuilder = request.url().newBuilder()
        val builder = request.newBuilder()

        map.forEach(action = { entry ->
            urlBuilder.addQueryParameter(entry.key, entry.value)
        })

        return chain.proceed(
                builder.url(urlBuilder.build()).build()
        )
    }
}