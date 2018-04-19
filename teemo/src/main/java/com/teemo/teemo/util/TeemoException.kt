package com.teemo.teemo.util

/**
 * Created by Teemo on 2018/4/16.
 * @author teemo
 */
class TeemoException constructor(private val errCode: Int = 0
                                 , private val errMessage: String?
                                 , private val throwable: Throwable?) : RuntimeException() {

//    var errCode: Int? = 0
//    var errMessage: String? = null
//    var throwable: Throwable? = null
//
//    constructor() : super()
//
//    constructor(errCode: Int?, errMessage: String?, throwable: Throwable?) {
//        this.errCode = errCode
//        this.errMessage = errMessage
//        this.throwable = throwable
//    }

//    constructor() : super()
//    constructor(message: String?) : super(message)
//    constructor(message: String?, cause: Throwable?) : super(message, cause)
//    constructor(cause: Throwable?) : super(cause)
//    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace)
}