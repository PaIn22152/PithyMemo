package com.perdev.pithy.memo.widget.views

import android.content.Context
import android.os.Build
import android.util.AttributeSet

/**
 * Project    PithyMemo-git
 * Path       com.perdev.pithy.memo.widget.views
 * Date       2019/07/03 - 10:24
 * Author     Payne.
 * About      类描述：
 */
class MyEditText @JvmOverloads constructor(context: Context,
                                           attrs: AttributeSet? = null,
                                           defStyleAttr: Int = 0)
    : android.support.v7.widget.AppCompatEditText(context, attrs, defStyleAttr) {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.letterSpacing = 0.2f
        }
    }
}