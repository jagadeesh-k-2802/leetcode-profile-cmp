package com.jackappsdev.leetcode

import android.app.Application
import com.jackappsdev.leetcode.di.initKoin
import org.koin.android.ext.koin.androidContext

class LeetCodeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@LeetCodeApplication)
        }
    }
}
