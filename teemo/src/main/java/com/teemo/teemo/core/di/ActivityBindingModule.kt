package com.teemo.teemo.core.di

import com.teemo.service.di.ActivityScoped
import com.teemo.service.ui.BaseActionImpl
import com.teemo.service.ui.IBaseConstruct
import com.teemo.teemo.MainActivity
import com.teemo.teemo.ui.home.HomeAction
import com.teemo.teemo.ui.home.HomeFragment
import com.teemo.teemo.ui.home.IHomeConstruct
import com.teemo.teemo.ui.launcher.ILauncherConstruct
import com.teemo.teemo.ui.launcher.LauncherAction
import com.teemo.teemo.ui.launcher.LauncherFragment
import com.teemo.teemo.ui.signup.ISignUpConstruct
import com.teemo.teemo.ui.signup.SignUpAction
import com.teemo.teemo.ui.signup.SignUpFragment

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
    internal abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @Binds
    abstract fun baseAction(action: BaseActionImpl): IBaseConstruct.IBaseAction<*>

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun launcherFragment(): LauncherFragment

    @ActivityScoped
    @Binds
    abstract fun launcherAction(action: LauncherAction): ILauncherConstruct.Action

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun signUpFragment(): SignUpFragment

    @ActivityScoped
    @Binds
    abstract fun signUpAction(action: SignUpAction): ISignUpConstruct.Action

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun HomeFragment(): HomeFragment

    @ActivityScoped
    @Binds
    abstract fun HomeAction(action: HomeAction): IHomeConstruct.Action


//    @Singleton
//    @Binds
//    abstract fun getUserRepository(userTask: UserRepository): UserRepository


}