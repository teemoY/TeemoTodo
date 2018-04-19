package com.teemo.network

import com.teemo.network.entity.User
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
interface TeemoApi {

    companion object {
        enum class BaseUrl constructor(private val baseUrl:String){
            LOCAL("http://192.168.1.43:8081"),
            TEEMO("http://119.23.59.85:8081"),
            ;

            fun baseUrl():String {
                return baseUrl
            }
        }
    }


    @FormUrlEncoded
    @POST("/login")
    fun signIn(@Field("account") account: String, @Field("password") password: String): Flowable<String>

    @FormUrlEncoded
    @POST("/user/test1")
    fun userTest(): Flowable<BaseResponse<User>>

    @POST("/test/test1")
    fun test(): Flowable<String>

    @FormUrlEncoded
    @POST("/email/sendVCode")
    fun sendVCode(@Field("email")email:String, @Field("type")type:Int = 0): Flowable<BaseResponse<Void>>

    @FormUrlEncoded
    @POST("/user/signUp")
    fun signUp(@Field("account")account: String, @Field("password")password: String, @Field("vcode")vcode:String): Flowable<BaseResponse<User>>

}