package com.teemo.teemo

import android.os.Bundle
import com.teemo.service.ui.BaseActivity
import com.teemo.teemo.ui.launcher.LauncherFragment
import javax.inject.Inject

/**
 * Created by Teemo on 2018/4/11.
 * @author teemo
 */
class MainActivity: BaseActivity() {


    @Inject
    lateinit var launcherFragment: LauncherFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadRootFragment(android.R.id.content, launcherFragment)

    }

}