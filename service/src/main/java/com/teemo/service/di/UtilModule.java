package com.teemo.service.di;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Teemo on 2018/2/28.
 *
 * @author teemo
 */
@Module
public abstract class UtilModule {

    @Singleton
    @Provides
    static SPUtils provideSpUtils() {
        return SPUtils.getInstance(Utils.getApp().getPackageName());
    }

}
