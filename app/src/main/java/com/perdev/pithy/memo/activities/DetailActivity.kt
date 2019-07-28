package com.perdev.pithy.memo.activities

import android.app.ListActivity
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.KeyEvent
import android.widget.TextView

import com.perdev.pithy.memo.R
import com.perdev.pithy.memo.utils.log
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onLongClick
import kotlin.math.log

class DetailActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailUI().setContentView(this@DetailActivity)


    }

    var content: Editable? = null


    private fun save() {

        log(content.toString())
    }

    inner class DetailUI : AnkoComponent<DetailActivity> {

        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
            relativeLayout {
                lparams(matchParent, matchParent)
                editText {
                    hint = "input your memo"
                    gravity = Gravity.START
                    textSize = 25f
                    textColor = R.color.grey_text
                    backgroundColorResource = R.color.colorAccent
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        background = null
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        letterSpacing = 0.1F
                    }
                    // 监听输入框输入情况
                    addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                            content = s
                        }

                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        }
                    })
                }.lparams(width = matchParent, height = matchParent) {
                    setMargins(
                        dimen(R.dimen.dp_5), dimen(R.dimen.dp_20),
                        dimen(R.dimen.dp_5), 0
                    )

                }

            }


        }
    }


    override fun onDestroy() {
        super.onDestroy()
        save()
    }
}
