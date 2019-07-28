package com.perdev.pithy.memo.utils

import android.util.Log

/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo.utils
 * Date       2019/07/03 - 20:13
 * Author     Payne.
 * About      类描述：
 */

val TAG = "payne_tag"
val SHOW = true


fun log(s: String) {
    if (SHOW) {

        Log.d(TAG, s)
    }

}