package com.perdev.pithy.memo.utils

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo.utils
 * Date       2019/07/03 - 20:13
 * Author     Payne.
 * About      类描述：
 */

const val DEF_TAG = "payne_tag"
const val SHOW = true


var inited = false

fun logD(s: String) {
    if (!SHOW) {
        return
    }
    initLogger()
//    Log.d(DEF_TAG, s)
    Logger.t(DEF_TAG).d(s)
}


fun initLogger() {
    if (inited) {
        return
    }
    inited = true
    val formatStrategy = PrettyFormatStrategy.newBuilder()
        .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
        .methodCount(2)         // (Optional) How many method line to show. Default 2
        .methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
//            .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
        .tag(DEF_TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
        .build()
    Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
}