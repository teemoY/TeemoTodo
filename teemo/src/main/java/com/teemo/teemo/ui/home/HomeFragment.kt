package com.teemo.teemo.ui.home

import com.alibaba.android.arouter.facade.annotation.Route
import com.teemo.service.ui.BaseFragment
import com.teemo.teemo.core.config.RouteConfig
import javax.inject.Inject

/**
 * Created by Teemo on 2018/4/18.
 * @author teemo
 */
@Route(path = RouteConfig.HOME)
class HomeFragment @Inject constructor():BaseFragment<IHomeConstruct.Action>(), IHomeConstruct.View {



}