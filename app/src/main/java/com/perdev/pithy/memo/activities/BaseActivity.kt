package com.perdev.pithy.memo.activities

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity


/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo.activities
 * Date       2019/07/02 - 22:41
 * Author     Payne.
 * About      类描述：
 */
abstract class BaseActivity : AppCompatActivity() {
    var mHandler: Handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}