package com.akashgarg.pagingdemo.di

import com.akashgarg.pagingdemo.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * @author Akash Garg on 16-03-2019.
 */
@Component(modules = [AppUtilsModules::class])
@Singleton
interface AppComponent {
    fun doInjectInApp(activity: MainActivity)
}