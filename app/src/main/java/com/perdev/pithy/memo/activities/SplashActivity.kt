package com.perdev.pithy.memo.activities

import android.content.Intent
import android.os.Bundle
import com.perdev.pithy.memo.R
import com.perdev.pithy.memo.utils.RXCallback
import com.perdev.pithy.memo.utils.rxTimer
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {

    private val pkg = "com.perdev.pithy.memo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (pkg == this.packageName) {
            delayJump()
        }

    }

    private fun delayJump() {


        rxTimer(2000L, true, object : RXCallback {
            override fun call() {
                this@SplashActivity.startActivity<MainActivity>()
                finish()
            }
        })
    }
}
