package com.teemo.teemo.ui.launcher

import com.teemo.service.ui.IBaseConstruct

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
interface ILauncherConstruct {

    interface View : IBaseConstruct.IBaseFragment

    interface Action : IBaseConstruct.IBaseAction<View>

}