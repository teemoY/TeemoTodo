package com.teemo.teemo

import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.SPUtils
import com.teemo.network.HeaderInterceptor
import com.teemo.network.TeemoHttp
import com.teemo.teemo.core.config.TeemoConfigs
import com.teemo.teemo.core.di.DaggerAppComponent
import com.xiaomi.mimc.MIMCClient
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class App : com.teemo.service.app.App() {

    @Inject
    lateinit var spUtils: SPUtils
    private var isLogin:Boolean? = null

    override fun debugable(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun isLogin(): Boolean {
        if(isLogin == null) {
            isLogin = spUtils.getBoolean(TeemoConfigs.is_login, false)
        }
        return isLogin!!
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        ARouter.init(this)
        TeemoHttp.init(this)

        super.onCreate()

        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }

        TeemoHttp.debugable(isDebug())
        TeemoHttp.onAuthenticationChangedListener(object : HeaderInterceptor.OnAuthenticationChangedListener {
            override fun onAuthenticationChanged(authentication: String) {
                spUtils.put(TeemoConfigs.share_authen, authentication)
            }

            override fun getCacheAuthentication(): String? {
                return spUtils.getString(TeemoConfigs.share_authen)
            }
        })

        MIMCClient.initialize(this)
//        /**
//         * @param[appId]: 开发者在小米开放平台申请的appId
//         * @param[appAccount]: 用户在APP帐号系统内的唯一帐号ID
//         **/
//        val user = MIMCUser(2882303761517676579, "teemo")
//        /**
//         * @note: fetchToken()访问APP应用方自行实现的AppProxyService服务，该服务实现以下功能：
//        1. 存储appId/appKey/appSecret(appKey/appSecret不可存储在APP客户端，以防泄漏)
//        2. 用户在APP系统内的合法鉴权
//        3. 调用小米TokenService服务，并将小米TokenService服务返回结果通过fetchToken()原样返回
//         * @return: 小米TokenService服务下发的原始数据
//         **/
//        user.registerTokenFetcher {
//            "teemo"
//        }
//        /**
//         * @note: 用户登录接口，除在APP初始化时调用，APP从后台切换到前台时也建议调用一次
//         **/
//        user.login()
//        user.registerOnlineStatusListener { status, code, message ->
//            run {
//                LogUtils.i("---------status:$status-code:$code--message:$message-------")
//            }
//        }
//
//        /**
//         * @param[toAppAccount]: 消息接收者在APP帐号系统内的唯一帐号ID
//         * @param[payload]: 开发者自定义消息体
//         * @param[isStore]: 消息是否存储在mimc服务端，true存储，false不存储，默认存储
//         * @return: 客户端生成的消息ID
//         **/
////        String packetId = user.sendMessage(String toAppAccount, byte[] payload, boolean isStore);
//        /**
//         * @param[groupId]: 群ID，也称为topicId
//         * @param[payload]: 开发者自定义消息体
//         * @param[isStore]: 消息是否存储在mimc服务端，true存储，false不存储，默认存储
//         * @return: 客户端生成的消息ID
//         **/
//        String packetId = user.sendGroupMessage(long groupID, byte[] payload, boolean isStore);
        //接收消息回调
//        user.registerMessageHandler(MIMCMessageHandler handler);
//        interface MIMCMessageHandler {
//            public void handleMessage(List<MIMCMessage> packets);
//            public void handleGroupMessage(List<MIMCGroupMessage> packets);
//
//            /**
//             * @param[serverAck]: 服务器返回的serverAck对象
//             *        serverAck.packetId: 客户端生成的消息ID
//             *        serverAck.timestamp: 消息发送到服务器的时间(单位:ms)
//             *        serverAck.sequence: 服务器为消息分配的递增ID，单用户空间内递增唯一，可用于去重/排序
//             **/
//            public void handleServerAck(MIMCServerAck serverAck);
//
//            public void handleSendMessageTimeout(MIMCMessage message);
//            public void handleSendGroupMessageTimeout(MIMCGroupMessage groupMessage);
//        }
        //注销
//        user.logout();
    }

}