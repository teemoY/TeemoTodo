package com.teemo.network

import android.content.Context
import android.util.Log
import io.reactivex.FlowableSubscriber
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
open class ApiSubscriber<T> : FlowableSubscriber<T> {

    private val context: Context? = null
    //    private val waitingDialog:
    private var isShowWaitingDialog: Boolean = true

    private val TAG = "ApiSubscriber"

    override fun onError(t: Throwable?) {
        t!!.printStackTrace()
        Log.i(TAG, "-----------onError------------$t")
    }

    override fun onNext(t: T) {
        Log.i(TAG, "-----------onNext------------$t")
    }

    override fun onSubscribe(s: Subscription) {
        Log.i(TAG, "-----------onSubscribe------------$s")
        s.request(java.lang.Long.MAX_VALUE)
        if (isShowWaitingDialog) {

        }
    }
//    override fun onSubscribe(s: Subscription?) {
//        Log.i(TAG, "-----------onSubscribe------------$s")
//        if (isShowWaitingDialog) {
//
//        }
//    }

    override fun onComplete() {
        Log.i(TAG, "-----------onComplete------------")
    }


}