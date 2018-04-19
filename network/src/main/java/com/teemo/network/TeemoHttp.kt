package com.teemo.network

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import com.google.gson.GsonBuilder
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class TeemoHttp private constructor() {


    private var isShowWaitingDialog = false
    private var observable: Flowable<*>? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        private val sInstance = TeemoHttp()
        private var debugable: Boolean = false

        private var sBaseUrl: TeemoApi.Companion.BaseUrl = TeemoApi.Companion.BaseUrl.TEEMO

        @SuppressLint("StaticFieldLeak")
        private var sContextReference: Context? = null

        private var listener: HeaderInterceptor.OnAuthenticationChangedListener? = null

        val TAG = "TeemoHttp"

        fun init(context: Context): TeemoHttp {
            if (sContextReference != null) {
                throw RuntimeException("---------------不能重复调用init---------------")
            }
            sContextReference = context.applicationContext
            return sInstance
        }

        fun baseUrl(baseUrl: TeemoApi.Companion.BaseUrl): TeemoHttp {
            sBaseUrl = baseUrl
            return sInstance
        }

        fun getInstance(): TeemoHttp {
            return sInstance
        }
//        fun with(context: Context): TeemoHttp {
////            this.sContextReference = WeakReference(context)
//            return sInstance
//        }

        fun debugable(debugable: Boolean): TeemoHttp {
            this.debugable = debugable
            return sInstance
        }

        fun onAuthenticationChangedListener(listener: HeaderInterceptor.OnAuthenticationChangedListener?): TeemoHttp {
            this.listener = listener
            return sInstance
        }
    }

    fun setShowWaitingDialog(isShowWaitingDialog: Boolean): TeemoHttp {
        this.isShowWaitingDialog = isShowWaitingDialog
        return this
    }

    fun <T> setObservable(observable: Flowable<T>): TeemoHttp {
        this.observable = observable
        return this
    }

    fun <T> asObservable(): Flowable<T> {
        return this.observable as Flowable<T>
    }

    fun <T> subscriber(subscriber: ApiSubscriber<T>): TeemoHttp {
        val obs = observable as Flowable<T>
        obs.subscribe(subscriber)
        return this
    }

    class NetWorkApiBuilder {
        private var baseUrl: String? = null
        private var isNeedAuthentication: Boolean = true
        private var dynamicParameter: HashMap<String, String>? = null
        private var isAddParameter: Boolean = false
        private var retrofitBuilder: Retrofit.Builder? = null
        private var okHttpBuilder: OkHttpClient.Builder? = null
        private var convertFactory: Converter.Factory? = null

        fun baseUrl(baseUrl: String): NetWorkApiBuilder {
            this.baseUrl = baseUrl
            return this
        }

        fun needAuthentication(needAuthentication: Boolean): NetWorkApiBuilder {
            this.isNeedAuthentication = needAuthentication
            return this
        }

        fun dynamicParameter(map: HashMap<String, String>): NetWorkApiBuilder {
            this.dynamicParameter = map
            return this
        }

        fun addParameter(addParameter: Boolean): NetWorkApiBuilder {
            this.isAddParameter = addParameter
            return this
        }

        fun convertFactory(convertFactory: Converter.Factory): NetWorkApiBuilder {
            this.convertFactory = convertFactory
            return this
        }

        fun build(): TeemoApi {
            retrofitBuilder = Retrofit.Builder()
            okHttpBuilder = OkHttpClient.Builder()
            if (TextUtils.isEmpty(baseUrl)) {
                retrofitBuilder!!.baseUrl(sBaseUrl.baseUrl())
            } else {
                retrofitBuilder!!.baseUrl(baseUrl!!)
            }
            if (isNeedAuthentication) {
                okHttpBuilder!!.addInterceptor(HeaderInterceptor(sContextReference!!, listener))
            }
            if (isAddParameter) {
                okHttpBuilder!!.addInterceptor(ParameterInterceptor())
            }
            if (dynamicParameter != null) {
                okHttpBuilder!!.addInterceptor(DynamicParameterInterceptor(dynamicParameter!!))
            }
            if (debugable) {
                okHttpBuilder!!.addInterceptor(HttpLoggingInterceptor())
            }
            if (convertFactory == null) {
                retrofitBuilder!!.addConverterFactory(GsonConverterFactory.create(
                        GsonBuilder()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                                .create()
                ))
            } else {
                retrofitBuilder!!.addConverterFactory(convertFactory!!)
            }
            retrofitBuilder!!.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpBuilder!!.build())
            return retrofitBuilder!!.build().create(TeemoApi::class.java)
        }
    }

}