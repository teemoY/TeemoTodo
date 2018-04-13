package com.teemo.teemo.core.di

import com.teemo.service.di.ActivityScoped
import com.teemo.service.ui.BaseActionImpl
import com.teemo.service.ui.IBaseConstruct
import com.teemo.teemo.MainActivity
import com.teemo.teemo.ui.launcher.ILauncherConstruct
import com.teemo.teemo.ui.launcher.LauncherAction
import com.teemo.teemo.ui.launcher.LauncherFragment

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @Binds
    abstract fun baseAction(action: BaseActionImpl): IBaseConstruct.IBaseAction<*>

    //    @ActivityScoped
    //    @ContributesAndroidInjector
    //    abstract TestFragment testFragment();
    //
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun launcherFragment(): LauncherFragment

    @ActivityScoped
    @Binds
    abstract fun launcherAction(action: LauncherAction): ILauncherConstruct.Action

    //    @ActivityScoped
    //    @ContributesAndroidInjector
    //    abstract LoginFragment loginFragment();
    //
    //    @ActivityScoped
    //    @Binds
    //    abstract ILoginConstruct.Action loginAction(LoginAction action);
    //
    //    @ActivityScoped
    //    @ContributesAndroidInjector
    //    abstract HomeFragment homeFragment();
    //
    //    @ActivityScoped
    //    @Binds
    //    abstract IHomeConstruct.Action homeAction(HomeAction action);

}