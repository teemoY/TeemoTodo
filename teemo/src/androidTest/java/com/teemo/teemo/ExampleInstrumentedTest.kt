package com.teemo.teemo

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.teemo.network.ApiSubscriber
import com.teemo.network.MobileApi
import com.teemo.network.TeemoHttp
import okhttp3.Response
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

//        TeemoHttp.with(appContext)
//                .setObservable(
//                        MobileApi.signIn(
//                                "teemo", "123456"
//                        )
//                )
//                .setShowWaitingDialog(true)
//                .subscriber(
//                        object : ApiSubscriber<Response>() {
//                            override fun onNext(t: Response) {
//                                super.onNext(t)
//                                println(t)
//                            }
//
//                            override fun onError(t: Throwable?) {
//                                super.onError(t)
//                                t!!.printStackTrace()
//                            }
//                        }
//                )
    }
}