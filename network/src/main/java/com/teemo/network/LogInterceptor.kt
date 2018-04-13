package com.teemo.network

import android.text.TextUtils
import android.util.Log
import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset


/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class LogInterceptor:Interceptor {

    private val TAG = "LogInterceptor"
    private val UTF8 = Charset.forName("UTF-8")

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(TAG, "before chain,request()")
        val request = chain.request()
        val response: Response
        try {
            val t1 = System.nanoTime()
            response = chain.proceed(request)
            val t2 = System.nanoTime()
            val time = (t2 - t1) / 1e6
            val acid = request.url().queryParameter("ACID")     //本项目log特定参数项目接口acid
            val userId = request.url().queryParameter("userId") //本项目log特定参数用户id
            var type = ""
            if (request.method().equals("GET")) {
                type = "GET"
            } else if (request.method().equals("POST")) {
                type = "POST"
            } else if (request.method().equals("PUT")) {
                type = "PUT"
            } else if (request.method().equals("DELETE")) {
                type = "DELETE"
            }
            val source = response.body()!!.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer()
            val logStr = "\n--------------------${if (TextUtils.isEmpty(acid)) "" else acid}  begin--------------------\n$type\nacid->${if (TextUtils.isEmpty(acid)) "" else acid}\nuserId->${if (TextUtils.isEmpty(userId)) "" else userId}\nnetwork code->${response.code()}\nurl->${request.url()}\ntime->${time.toString() + ""}\nrequest headers->${request.headers()}request->${bodyToString(request.body()!!)}\nbody->${buffer.clone().readString(UTF8)}"
            Log.i(TeemoHttp.TAG, logStr)
        } catch (e: Exception) {
            throw e
        }

        return response
    }

    private fun bodyToString(request: RequestBody): String {
        try {
            val buffer = Buffer()
            request.writeTo(buffer)
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }

    }

}