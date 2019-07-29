package com.perdev.pithy.memo.utils

import android.util.Log
import java.util.logging.Logger

/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo.utils
 * Date       2019/07/03 - 20:13
 * Author     Payne.
 * About      类描述：
 */

const val TAG = "payne_tag"
const val SHOW = true


fun logD(s: String) {
    if (SHOW) {

        Log.d(TAG, s)

    }

}