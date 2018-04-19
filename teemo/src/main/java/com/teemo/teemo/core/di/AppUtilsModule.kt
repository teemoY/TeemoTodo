package com.teemo.teemo.core.di

import com.teemo.network.TeemoHttp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Teemo on 2018/4/16.
 * @author teemo
 */
@Module
class AppUtilsModule {


        @Singleton
        @Provides
        fun provideTeemoHttp(): TeemoHttp {
            return TeemoHttp.getInstance()
        }

}