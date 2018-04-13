package com.teemo.network

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class ApiException constructor(private val code:Int?, private val errorMessage:String?):Exception() {

    fun getCode():Int?{
        return code
    }

    fun getErrorMessage(): String? {
        return errorMessage
    }

}