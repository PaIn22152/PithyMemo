package com.perdev.pithy.memo.activities

import android.content.Intent
import android.os.Bundle
import com.perdev.pithy.memo.R

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        delayJump()
    }

    private fun delayJump() {
        mHandler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1300L)
    }
}
