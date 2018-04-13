package com.teemo.network

import com.teemo.network.entity.User
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class MobileApi : BaseApi() {

    companion object {

        private var api: TeemoApi? = null
        private var apiStr: TeemoApi? = null
//        private var observable: Observable<*>? = null

        private fun getNetworkApi(): TeemoApi {
            if (api == null) {
                api = TeemoHttp.NetWorkApiBuilder()
                        .needAuthentication(true)
                        .addParameter(true)
                        .build()
            }
            return api!!
        }

        private fun getNetworkApiapiStr(): TeemoApi {
            if (apiStr == null) {
                apiStr = TeemoHttp.NetWorkApiBuilder()
                        .needAuthentication(true)
                        .addParameter(true)
                        .convertFactory(object : Converter.Factory() {
                            override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
                                return Converter<ResponseBody, String> { value -> value.string() }
                            }
                        })
                        .build()
            }
            return apiStr!!
        }

        private fun <T> getObservable(flowable: Flowable<T>, convert:Boolean = false): Flowable<T> {
            val builder = ObservableBuilder(flowable)
            if(convert) {
                builder.addApiException()
            }
            return builder
//                    .addApiException()
                    .build()
        }

        fun signIn(account: String, password: String): Flowable<String> {
            return getObservable(getNetworkApiapiStr().signIn(account, password))
        }

        fun test(): Flowable<String> {
            return getObservable(getNetworkApiapiStr().test())
        }

        fun userTest(account: String): Flowable<BaseResponse<User>> {
            return getObservable(getNetworkApi().userTest(), true)
        }
    }

}