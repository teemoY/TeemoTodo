package com.teemo.teemo.util

import com.teemo.network.ApiSubscriber
import com.teemo.network.BaseResponse
import com.teemo.network.config.Codes
import java.lang.ref.WeakReference

/**
 * Created by Teemo on 2018/4/16.
 * @author teemo
 */
open class ApiSubscriberImpl<T>(callback: ApiCallback<T>) : ApiSubscriber<BaseResponse<T>>() {

    private var reference: WeakReference<ApiCallback<T>> = WeakReference(callback)

    override fun onNext(t: BaseResponse<T>) {
        super.onNext(t)
        if (reference.get() != null) {
            if(t.code!! == Codes.CODE_SUCCESS) {
                reference.get()!!.onSuccess(t.code!!, t.message, t.data)
            } else{
                reference.get()!!.onFailed(t.code!!, t.message, null)
            }
        }
    }

    override fun onError(t: Throwable?) {
        super.onError(t)
        if(reference.get() != null) {
            reference.get()!!.onFailed(0, null, t)
        }
    }

}