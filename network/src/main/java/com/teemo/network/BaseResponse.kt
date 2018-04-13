package com.teemo.network

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class BaseResponse<T> {

    var code: Int? = 0
        get
        set
    var message: String? = null
        get
        set
    var data: T? = null
        get
        set


}