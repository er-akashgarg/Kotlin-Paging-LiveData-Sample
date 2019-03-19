package com.akashgarg.pagingdemo.app

import android.app.Application
import android.content.Context
import com.akashgarg.pagingdemo.di.AppComponent
import com.akashgarg.pagingdemo.di.AppUtilsModules
import com.akashgarg.pagingdemo.di.DaggerAppComponent
import com.facebook.drawee.backends.pipeline.Fresco


/**
 * Created by Akash Garg on 16-03-2019.
 */
open class MyApplication : Application() {

    private var appComponent: AppComponent? = null
    private var mContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        mContext = this
        appComponent = DaggerAppComponent.builder().appUtilsModules(AppUtilsModules()).build()
        Fresco.initialize(this)
    }

    fun getAppComponent(): AppComponent {
        return appComponent!!
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}