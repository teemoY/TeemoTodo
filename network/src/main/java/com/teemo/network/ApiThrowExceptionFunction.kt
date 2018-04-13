package com.teemo.network

import com.teemo.network.config.Codes
import io.reactivex.Flowable
import io.reactivex.functions.Function

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class ApiThrowExceptionFunction<T> : Function<BaseResponse<T>, Flowable<T>> {
    override fun apply(response: BaseResponse<T>): Flowable<T> {
        if (response.code != Codes.CODE_SUCCESS) {
            return Flowable.error(ApiException(response.code, response.message))
        }
        return Flowable.just(response.data)
    }
}