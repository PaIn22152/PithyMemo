package com.perdev.pithy.memo.activities

import android.content.Intent
import android.os.Bundle
import com.perdev.pithy.memo.R

class SplashActivity : BaseActivity() {

    private val pkg: String = "com.perdev.pithy.memo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (pkg == this.packageName) {
            delayJump()
        }

    }

    private fun delayJump() {
        mHandler.postDelayed({
            startActivity(Intent(this, DetailActivity::class.java))
            finish()
        }, 2000L)
    }
}
