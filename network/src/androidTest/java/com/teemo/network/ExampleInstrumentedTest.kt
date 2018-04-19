package com.teemo.network

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
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
        Assert.assertEquals("com.teemo.todo", appContext.packageName)

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
//                        }
//                )
    }
}