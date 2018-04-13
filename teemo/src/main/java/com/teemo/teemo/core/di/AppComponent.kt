package com.teemo.teemo.core.di

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
import android.app.Application

import com.blankj.utilcode.util.SPUtils
import com.teemo.service.di.ApplicationModule
import com.teemo.service.di.UtilModule
import com.teemo.teemo.App

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = arrayOf(UtilModule::class, ApplicationModule::class, ActivityBindingModule::class, AndroidSupportInjectionModule::class))
interface AppComponent : AndroidInjector<App> {

    val spUtils: SPUtils

    val isDebugMode: Boolean

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}