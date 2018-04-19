package com.teemo.teemo.ui.home

import com.teemo.service.ui.IBaseConstruct

/**
 * Created by Teemo on 2018/4/18.
 * @author teemo
 */
interface IHomeConstruct {

    interface View : IBaseConstruct.IBaseFragment {

    }

    interface Action : IBaseConstruct.IBaseAction<View> {

    }

}