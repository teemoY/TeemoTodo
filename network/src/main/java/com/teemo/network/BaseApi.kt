package com.teemo.network

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import org.json.JSONObject
import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.functions.Function


/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
abstract class BaseApi {

    companion object {
        fun <K, V> toBody(map: HashMap<K, V>): RequestBody {
            val gson = Gson()
            val requestBean = ImiRequestBean()
            requestBean.setRequeststamp(ProtocolUtils.getTimestamp())
            requestBean.setData(map)
            return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), gson.toJson(requestBean))
        }

        fun toBody(jsonObject: JSONObject): RequestBody {
            return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString())
        }
    }

    class ImiRequestBean {
        private var map: HashMap<*, *>? = null
        private var timestamp: Long? = null
        fun setData(map: HashMap<*, *>) {
            this.map = map
        }

        fun setRequeststamp(stamp: Long) {
            this.timestamp = stamp
        }
    }

    object ProtocolUtils {
        fun getTimestamp(): Long {
            return System.currentTimeMillis()
        }
    }

    class ObservableBuilder<T> constructor(private var observable: Flowable<T>) {
        private var apiException: Boolean = false
        private var toJSONJbject: Boolean = false
        private var isWeb: Boolean = false
        private var subscribeScheduler: Scheduler? = null
        private var obscerveScheduler: Scheduler? = null

        fun setObscerveScheduler(obscerveScheduler: Scheduler) {
            this.obscerveScheduler = obscerveScheduler
        }

        fun setSubscribeScheduler(subscribeScheduler: Scheduler) {
            this.subscribeScheduler = subscribeScheduler
        }

        fun addApiException(): ObservableBuilder<T> {
            apiException = true
            return this
        }

//        fun addToJSONObject(): ObserableBuilder<T> {
//            toJSONJbject = true
//            return this
//        }
//
//        fun isWeb(): ObservableBuilder<T> {
//            isWeb = true
//            return this
//        }

        fun build(): Flowable<T> {
//            if (isWeb) {
////                val obs = observable as Flowable<>
////                observable = obs.map(StringToJSONObjectFunction())
//            }
            if (apiException) {
                val obs = observable as Flowable<BaseResponse<T>>
                observable = obs.flatMap(ApiThrowExceptionFunction())
            }
//            if (toJSONJbject) {
//                val obs = observable as Flowable<Object>
//                observable = observable.map(ObjectToJSONObjectFunction())
//            }
            if (subscribeScheduler != null) {
                observable = observable.subscribeOn(subscribeScheduler!!)
            } else {
                observable = observable.subscribeOn(Schedulers.io())
            }
            if (obscerveScheduler != null) {
                observable = observable.observeOn(obscerveScheduler)
            } else {
                observable = observable.observeOn(AndroidSchedulers.mainThread())
            }
            return observable
        }
    }
}