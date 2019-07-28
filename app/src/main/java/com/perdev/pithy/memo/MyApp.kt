package com.perdev.pithy.memo

import android.app.Application
import android.content.Context

/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo
 * Date       2019/07/07 - 15:13
 * Author     Payne.
 * About      类描述：
 */
class MyApp : Application() {
    companion object {
        // 伴生对象
        lateinit var instance: MyApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}